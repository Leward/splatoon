package splatoon

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Performance {

    int wins
    int loses
    int points

    void add(Ladder ladder) {
        wins += ladder.wins
        loses += ladder.loses
        points += ladder.points
    }

    BigDecimal getRatio() {
        if(wins == 0 && loses == 0) {
            return 0
        }
        return wins / (wins + loses)
    }

    int getNbGames() {
        return wins + loses
    }

    String getRatioAsString() {
        RankedTeam.DECIMAL_FORMAT.format(ratio)
    }


}
