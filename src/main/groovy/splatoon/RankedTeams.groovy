package splatoon

import com.google.common.collect.ImmutableList
import groovy.transform.CompileStatic

import java.time.Instant
import java.time.temporal.ChronoUnit

@CompileStatic
class RankedTeams {

    private List<RankedTeam> list = []

    RankedTeams(Collection<Ladder> ladderList, boolean computeEvolution = true) {
        ladderList.each { ladder ->
            RankedTeam rankedTeam = getRankedTeam(ladder.team)
            rankedTeam.points += ladder.points
            rankedTeam.wins += ladder.wins
            rankedTeam.loses += ladder.loses
            rankedTeam.participations.add(ladder.event.tournament)
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
        Instant oneMonthAgo = Instant.now().minus(30, ChronoUnit.DAYS)
        Collection<Ladder> laddersFromLastMonthEvicted = ladderList.findAll { it.date.isBefore(oneMonthAgo) }
        RankedTeams rankingFromOneMonthAgo = new RankedTeams(laddersFromLastMonthEvicted, false)
        computeEvolutionFromPreviousRankings(rankingFromOneMonthAgo)
    }

    private void computeEvolutionFromPreviousRankings(RankedTeams previousRankings) {
        list.each {
            RankedTeam previousTeam = previousRankings.getRankedTeam(it.team)
            it.compareWithPreviousRanking(previousTeam)
        }
    }

    private RankedTeam getRankedTeam(Team team) {
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
