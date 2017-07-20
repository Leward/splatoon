package splatoon

import groovy.transform.ToString
import org.json.JSONObject

@ToString
class TwitchStream {

    String channelName
    String channelLogoUrl
    int viewers
    Game game

    TwitchStream(JSONObject jsonObject) {
        channelName = jsonObject.getJSONObject('channel').getString('name')
        channelLogoUrl = jsonObject.getJSONObject('channel').optString('logo')
        viewers = jsonObject.getInt('viewers')
        this.game = (jsonObject.getString('game') == 'Splatoon 2') ? Game.SPLATOON_2 : Game.SPLATOON_1
    }

}
