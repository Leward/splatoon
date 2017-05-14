package splatoon

import groovy.transform.ToString
import org.json.JSONObject

@ToString
class TwitchStream {

    String channelName
    String channelLogoUrl

    TwitchStream(JSONObject jsonObject) {
        channelName = jsonObject.getJSONObject('channel').getString('name')
        channelLogoUrl = jsonObject.getJSONObject('channel').optString('logo')
    }

}
