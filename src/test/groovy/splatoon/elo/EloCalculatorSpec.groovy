package splatoon.elo

import spock.lang.Specification

class EloCalculatorSpec extends Specification {

    static calculator = new EloCalculator()

    def "a win has a score of 1"() {
        expect:
        calculator.score(true) == 1
    }

    def "a loses has a score of 0.1"() {
        expect:
        calculator.score(false) == 0.1
    }

    def "team elo 1000 vs team elo 2000"() {
        expect:
        calculator.newElo(1000, 2000, hasWon) == newElo
        where:
        hasWon | newElo
        true   | 1032 // The small team win vs a big team and get a lot of points (the maximum)
        false  | 1003 // The small team win, however vs a big team (here the game is very disproportionate) the small still experience a small ranking increase
    }

    def "two teams of same level clash"() {
        expect:
        calculator.newElo(1800, 1800, hasWon) == newElo
        where:
        hasWon | newElo
        true   | 1816 // When two teams of same rank fight, the increase/decrease of rank is quite symmetric
        false  | 1787 // It is not completely symetric has a lose brings a score of 0.1 (and not 0)
    }

    def "team elo 1200 vs team elo 1300"() {
        expect:
        calculator.newElo(1200, 1300, hasWon) == newElo
        where:
        hasWon | newElo
        true   | 1220
        false  | 1192
    }

    def "score for event is the average score of all games"() {
        expect:
        calculator.scoreForEvent(wins, loses) == score
        where:
        wins | loses | score
        0    | 0     | 0
        1    | 0     | 1
        0    | 1     | 0.1
        1    | 1     | 0.55
        3    | 0     | 1
        0    | 3     | 0.1
        2    | 1     | 0.7
        1    | 2     | 0.4
    }

    def "team elo 1500 participate to tournament with average elo 1325"() {
        expect:
        calculator.newEloAfterEvent(1500, 1325, wins, loses) == newElo
        where:
        wins | loses | newElo
        3    | 1     | 1505 // Team score is around 0.77 with expectation around 0.73. The teams meets the expectation, the score barely moves
        4    | 0     | 1534
        0    | 4     | 1419 // The team has a good ranking vs the average level and loses all its games. The team ranking suffers a lot
        2    | 2     | 1477
    }

}
