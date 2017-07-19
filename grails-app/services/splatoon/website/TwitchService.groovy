package splatoon.website

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest
import groovy.transform.CompileStatic
import org.json.JSONObject
import org.springframework.stereotype.Service
import splatoon.CachedValue
import splatoon.TwitchStream

import java.time.Duration
import java.time.Instant

@Service
@CompileStatic
class TwitchService {

    private final CachedValue<List<TwitchStream>> cachedLiveChannels = new CachedValue<>(Duration.ofMinutes(5))


    List<TwitchStream> getLiveChannels() {
        if(cachedLiveChannels.cacheValid) {
            return cachedLiveChannels.value
        }
        def response = Unirest.get('https://api.twitch.tv/kraken/streams/')
                .header('Client-ID', '6iml9gxvz80elk7qxjve5ofezklffw')
                .queryString('game', 'Splatoon')
                .queryString('stream_type', 'live')
                .queryString('limit', 8)
                .asJson()
        def streams = getJsonObjectsForStreamResponse(response).collect { new TwitchStream(it) }
        cachedLiveChannels.cache(streams)
        return streams
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
