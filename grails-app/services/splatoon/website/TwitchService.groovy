package splatoon.website

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest
import org.json.JSONObject
import org.springframework.stereotype.Service
import splatoon.TwitchStream

@Service
class TwitchService {

    List<TwitchStream> getLiveChannels() {
        def response = Unirest.get('https://api.twitch.tv/kraken/streams/')
                .header('Client-ID', '6iml9gxvz80elk7qxjve5ofezklffw')
                .queryString('game', 'Splatoon')
                .queryString('stream_type', 'live')
                .queryString('limit', 10)
                .asJson()
        return getJsonObjectsForStreamResponse(response)
                .collect { new TwitchStream(it) }
    }

    private List<JSONObject> getJsonObjectsForStreamResponse(HttpResponse<JsonNode> response) {
        List<JSONObject> streamJsonObjects = []
        def streamsJsonArray = response.body.getObject().getJSONArray("streams")
        for (int i = 0; i < streamsJsonArray.length(); i++) {
            streamJsonObjects.add(streamsJsonArray.getJSONObject(i))
        }
        return streamJsonObjects
    }
}
