package splatoon.elo

import splatoon.Ladder
import splatoon.Team
import splatoon.TournamentEvent

import java.time.Instant

/**
 * The ELO rankings of a team over time
 */
class TeamEloRankings {

    Team team
    TreeMap<Instant, Integer> eloRanksOverTime = new TreeMap<>()

    TeamEloRankings(Team team) {
        this.team = team
        eloRanksOverTime.put(Instant.MIN, 1200)
    }

    /**
     * Add a new ladder entry to compute a new Elo score.
     * The ladder entries MUST be added in chronological order
     */
    void add(Ladder ladder, int avgEloForEvent) {
        def eloCalculator = new EloCalculator()
        def elo = eloRanksOverTime.lastEntry().value.toInteger()
        def newElo = eloCalculator.newEloAfterEvent(elo, avgEloForEvent, ladder.wins, ladder.loses)
        eloRanksOverTime[ladder.event.startInstant] = newElo
    }

    int getEloAt(Instant instant) {
        return eloRanksOverTime.floorEntry(instant).value
    }


}
