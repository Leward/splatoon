package splatoon

enum PlayerRole {

    SUPPORT('team.player.role.support'),
    SLAYER('team.player.role.slayer'),
    FLEX('team.player.role.flex'),
    FLANK('team.player.role.flank'),
    SHOTCALLER('team.player.role.shotcaller'),

    final String i18nMessage

    PlayerRole(String i18nMessage) {
        this.i18nMessage = i18nMessage
    }
}