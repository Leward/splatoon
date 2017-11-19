package splatoon

enum PlayerRole {

    SUPPORT('team.player.role.support'),
    SLAYER('team.player.role.slayer'),
    FLEX('team.player.role.flex')

    final String i18nMessage

    PlayerRole(String i18nMessage) {
        this.i18nMessage = i18nMessage
    }
}