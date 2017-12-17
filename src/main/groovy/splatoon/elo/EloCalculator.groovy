package splatoon.elo

class EloCalculator {

    static final int MAXIMUM_SKILL_CHANGE = 32

    def expectation = { int ownElo, int enemyElo ->
        1 / (1 + 10.power((enemyElo - ownElo) / 400))
    }

    def score = { boolean isWin ->
        isWin ? 1 : 0.1
    }

    def newElo = { int elo, int enemyElo, boolean hasWon ->
        Math.round(elo + MAXIMUM_SKILL_CHANGE * (score(hasWon) - expectation(elo, enemyElo)))
    }

    def expectationForEvent = { int ownElo, int avgEventElo ->
        expectation(ownElo, avgEventElo)
    }

    def scoreForEvent = { int wins, int loses ->
        if(wins == 0 && loses == 0) {
            return 0
        } else {
            return (wins * score(true) + loses * score(false)) / (wins + loses)
        }
    }

    def maximumSkillChangeForEvent = { int wins, int loses ->
        (wins + loses) * MAXIMUM_SKILL_CHANGE
    }

    def newEloAfterEvent = { int elo, int avgEloForEvent, int wins, int loses ->
        Math.round(elo + maximumSkillChangeForEvent(wins, loses) * (scoreForEvent(wins, loses) - expectationForEvent(elo, avgEloForEvent)))
    }



    static void main(String[] args) {

        // All user start with 1500 rating
        // Should we use the existing rankings?

        // a great player gains little from beating an average player,
        // but an average player gains a lot from beating a great player.

        // For ELO ranking to work we need to register who fight against who and the outcome of the game
        // Before doing the game, the system try to predict the outcome of the game based of their rankings

        // Today inkzone does not record game per game result, but rather tournament per tournament
        // So during a transition period the ELO ranking will be based on tournament average results and levels to compute the rankings

        /* References:
         - https://forums.alliedmods.net/showthread.php?t=221523
         - https://metinmediamath.wordpress.com/2013/11/27/how-to-calculate-the-elo-rating-including-example/
         */
        def calculator = new EloCalculator()

        def simulate = { int eloA, int eloB, String title ->
            def expectationA = calculator.expectation(eloA, eloB)
            def expectationB = calculator.expectation(eloB, eloA)

            println(title)
            printf("%-20s %-10s %-10s%n", "", "Team A", "Team B")
            printf("%-20s %-10d %-10d%n", "ELO", eloA, eloB)
            printf("%-20s %-10f %-10f%n", "Expectation", expectationA, expectationB)
            printf("%-20s %-10d %-10d%n", "New ELO (A wins)", calculator.newElo(eloA, eloB, true), calculator.newElo(eloB, eloA, false))
            printf("%-20s %-10d %-10d%n", "New ELO (B wins)", calculator.newElo(eloA, eloB, false), calculator.newElo(eloB, eloA, true))
            printf("-----------------------------------------------%n%n%n")
        }

        def simulateEvent = { int elo, int avgEventElo, int wins, int loses, String title ->
            println(title)
            def teamEventExpectation = calculator.expectationForEvent(elo, avgEventElo)
            printf("%-20s %-10s %-10s%n", "Results", "${wins} win(s)", "${loses} lose(s)")
            printf("%-20s %-10d%n", "Team ELO", elo)
            printf("%-20s %-10d%n", "Event ELO", avgEventElo)
            printf("%-20s %-10f%n", "Expectation", teamEventExpectation)
            printf("%-20s %-10f%n", "Score for event", calculator.scoreForEvent(wins, loses))
            printf("%-20s %-10d%n", "Max skill change", calculator.maximumSkillChangeForEvent(wins, loses))
            printf("%-20s %-10d%n", "New ELO", calculator.newEloAfterEvent(elo, avgEventElo, wins, loses))
            printf("-----------------------------------------------%n%n%n")
        }

        simulate(1000, 2000, "Small vs big team")
        simulate(2000, 2030, "Big vs big team")
        simulate(1200, 1300, "Small vs small team")
        simulate(1300, 1800, "Small vs quite good team")
        simulate(2100, 2100, "Two good team with same scoring")

        simulateEvent(1500, 1325, 3, 1, "Event Elo for team of rank 1500")
        simulateEvent(1400, 1325, 3, 1, "Event Elo for team of rank 1400")
        simulateEvent(1300, 1325, 3, 1, "Event Elo for team of rank 1300")
        simulateEvent(1200, 1325, 3, 1, "Event Elo for team of rank 1200")

    }

}
