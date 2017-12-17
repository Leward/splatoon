package splatoon.elo

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import splatoon.Team

@ToString
@EqualsAndHashCode
class TeamEloRanking {

    Team team
    int elo

    TeamEloRanking(Team team, int elo) {
        this.team = team
        this.elo = elo
    }

    static final Comparator<TeamEloRanking> COMPARATOR = {a, b -> -1 * a.elo.compareTo(b.elo)}

}
