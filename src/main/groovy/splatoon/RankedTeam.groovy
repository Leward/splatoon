package splatoon

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes = "team")
@ToString
class RankedTeam implements Comparable<RankedTeam> {

    static final int NOT_COMPUTED = -1

    Team team
    Integer rank = NOT_COMPUTED
    Integer wins = 0
    Integer loses = 0
    Integer points = 0
    Set<Tournament> participations = []
    Evolution evolution

    @Override
    int compareTo(RankedTeam rankedTeam) {
        if(points == rankedTeam.points) {
            return 0
        } else if(points > rankedTeam.points) {
            return -1
        } else {
            return 1
        }
    }

    static enum Evolution {
        BETTER, SAME, WORSE
    }

    Evolution compareWithPreviousRanking(RankedTeam previousTeamRanking) {
        if(previousTeamRanking == null) {
            evolution = Evolution.BETTER
        } else if(rank == previousTeamRanking.rank) {
            evolution = Evolution.SAME
        } else if(rank > previousTeamRanking.rank) {
            evolution = Evolution.BETTER
        } else {
            evolution = Evolution.WORSE
        }
    }

    int getNbTournaments() {
        return participations.size()
    }

}
