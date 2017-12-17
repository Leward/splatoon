package splatoon.elo

import splatoon.Team
import splatoon.TournamentEvent

import java.time.Instant

class AllTeamsEloRankings {

    String rankingName = "General"
    Map<Team, TeamEloRankings> allTeamsRankings = [:]

    void add(Collection<TournamentEvent> events) {
        def eventsWithLadderEntries = events.findAll { !it.ladderEntries.isEmpty()}
        def sortedEvents = eventsWithLadderEntries.sort { it.date }
        sortedEvents.each { event ->
            def avgElo = getAverageEloFor(event)
            event.ladderEntries.each { ladder ->
                getRankingsForTeam(ladder.team).add(ladder, avgElo)
            }
        }
    }

    int getAverageEloFor(TournamentEvent event) {
        if(event.ladderEntries.isEmpty()) {
            throw new IllegalArgumentException('Cannot calculate average ELO of an event without ladder entry')
        }
        def elos = event.ladderEntries.collect { ladder -> getRankingsForTeam(ladder.team).getEloAt(event.startInstant) }
        return Math.round(elos.sum() / event.ladderEntries.size())
    }

    TeamEloRankings getRankingsForTeam(Team team) {
        if(!allTeamsRankings.containsKey(team)) {
            allTeamsRankings.put(team, new TeamEloRankings(team))
        }
        return allTeamsRankings.get(team)
    }


    TreeMap<Integer, Team> getRanking() {
        return getRanking(Instant.now())
    }

    TreeMap<Integer, Team> getRanking(Instant at) {
        def result = new TreeMap<Integer, Team>(Collections.reverseOrder())
        allTeamsRankings.each { result.put(it.value.getEloAt(at), it.key) }
        return result
    }

}