package splatoon

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import java.text.DecimalFormat

@CompileStatic
@EqualsAndHashCode(includes = "team")
@ToString
class RankedTeam implements Comparable<RankedTeam> {

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat('#.##')

    Team team
    Integer rank = null
    Integer wins = 0
    Integer loses = 0
    Integer points = 0
    Set<Tournament> participations = []
    Evolution evolution
    Integer previousRank = null

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
        BETTER, SAME, WORSE, NEW
    }

    Evolution compareWithPreviousRanking(RankedTeam previousTeamRanking) {
        previousRank = previousTeamRanking.rank
        if(previousTeamRanking?.rank == null) {
            evolution = Evolution.NEW
        } else if(rank == previousTeamRanking.rank) {
            evolution = Evolution.SAME
        } else if(rank < previousTeamRanking.rank) {
            evolution = Evolution.BETTER
        } else {
            evolution = Evolution.WORSE
        }
    }

    int getNbTournaments() {
        return participations.size()
    }

    BigDecimal getRatio() {
        if(wins == 0 && loses == 0) {
            return 0
        }
        return wins / (wins + loses)
    }

    String getRatioAsString() {
        DECIMAL_FORMAT.format(ratio)
    }

}
