package splatoon

import groovy.transform.CompileStatic

/**
 * Embeds different rankings
 */
@CompileStatic
class RankingsCompilation {

    RankedTeams globalRankings
    Map<TournamentOrganizer, RankedTeams> perTournamentOrganizerRankings = [:]

    RankingsCompilation(Collection<Ladder> ladderList) {
        // Compute Global Rankings
        globalRankings = new RankedTeams(ladderList)

        // Compute Rankings per Tournament Organizer
        ladderList
                .groupBy { it.event.tournament.organizer }
                .each { to, toLadders -> perTournamentOrganizerRankings[to] = new RankedTeams(toLadders) }

    }

    /**
     * @return ordered lists, the TO with the most ladder entries first
     */
    List<TournamentOrganizer> listTournamentOrganizers() {
        return perTournamentOrganizerRankings.keySet()
                .sort { perTournamentOrganizerRankings[it].list.size() }
                .reverse()
    }

}
