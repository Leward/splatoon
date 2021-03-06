package splatoon

import com.google.common.collect.ImmutableList
import groovy.transform.CompileStatic

import java.time.Instant
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

@CompileStatic
class RankedTeams {

    private List<RankedTeam> list = []

    RankedTeams(Collection<Ladder> ladderList, boolean computeEvolution = true) {
        ladderList.each { ladder ->
            getRankedTeam(ladder.team).add(ladder)
        }
        list = list.sort()
        list.eachWithIndex { RankedTeam rankedTeam, int i ->
            rankedTeam.rank = i + 1
        }

        if(computeEvolution) {
            computeEvolutionSinceLastMonth(ladderList)
        }
    }

    private void computeEvolutionSinceLastMonth(Collection<Ladder> ladderList) {
        Instant oneWeekAgo = Instant.now().minus(7, ChronoUnit.DAYS)
        Collection<Ladder> laddersFromLastMonthEvicted = ladderList.findAll {
            it.event.date.atTime(it.event.endTime).toInstant(ZoneOffset.UTC).isBefore(oneWeekAgo)
        }
        RankedTeams rankingFromOneWeek = new RankedTeams(laddersFromLastMonthEvicted, false)
        computeEvolutionFromPreviousRankings(rankingFromOneWeek)
    }

    private void computeEvolutionFromPreviousRankings(RankedTeams previousRankings) {
        list.each {
            RankedTeam previousTeam = previousRankings.getRankedTeam(it.team)
            it.compareWithPreviousRanking(previousTeam)
        }
    }

    RankedTeam getRankedTeam(Team team) {
        RankedTeam rankedTeam = list.find { it.team == team }
        if(rankedTeam == null) {
            rankedTeam = new RankedTeam(team: team)
            list.add(rankedTeam)
        }
        return rankedTeam
    }

    ImmutableList<RankedTeam> getList() {
        def builder = ImmutableList.builder()
        list.each { builder.add(it) }
        return builder.build()
    }

}
