package splatoon

enum TeamType {

    /**
     * A regular primary team
     */
    TEAM,

    /**
     * A team composed of players having different main teams because they want to play
     * together for some time.
     */
    PICKUP,

    /**
     * A secondary team for players already having a main teams.
     * Usually because those players want to play together.
     * It is similar to PICKUP, but a subclan is more stable over time.
     */
    SUBCLAN

}