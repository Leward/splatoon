package splatoon.elo

import groovy.transform.CompileStatic
import splatoon.TournamentEvent
import splatoon.TournamentOrganizer

@CompileStatic
class PerTOAllTeamsEloRankings {

    Map<TournamentOrganizer, AllTeamsEloRankings> rankings = [:]
    AllTeamsEloRankings general = new AllTeamsEloRankings()

    void add(Collection<TournamentEvent> events) {
        def eventsPerTo = events.groupBy { it.tournament.organizer }
        eventsPerTo.each { tournamentOrganizer, eventsForTO ->
            getRankingsFor(tournamentOrganizer).add(eventsForTO)
        }
        general.add(events)
    }

    AllTeamsEloRankings getRankingsFor(TournamentOrganizer tournamentOrganizer) {
        if(!rankings[tournamentOrganizer]) {
            rankings[tournamentOrganizer] = new AllTeamsEloRankings(rankingName: tournamentOrganizer.name)
        }
        return rankings[tournamentOrganizer]
    }

    List<TournamentOrganizer> listOrganizersSortedByPopularity() {
        return rankings.keySet()
                .sort { rankings[it].popularity }
                .reverse()
    }

}
