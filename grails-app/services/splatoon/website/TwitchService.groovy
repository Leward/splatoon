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
import java.util.function.ToIntFunction
import java.util.stream.Collectors

@Service
@CompileStatic
class TwitchService {

    private final CachedValue<List<TwitchStream>> cachedLiveChannels = new CachedValue<>(Duration.ofMinutes(5))


    List<TwitchStream> getLiveChannels() {
        if(cachedLiveChannels.cacheValid) {
            return cachedLiveChannels.value
        }

        def responseSplatoonStreams = Unirest.get('https://api.twitch.tv/kraken/streams/')
                .header('Client-ID', '6iml9gxvz80elk7qxjve5ofezklffw')
                .queryString('game', 'Splatoon')
                .queryString('stream_type', 'live')
                .queryString('limit', 30)
                .asJson()
        def splatoonStreams = getJsonObjectsForStreamResponse(responseSplatoonStreams).collect { new TwitchStream(it) }

        def responseSplatoon2Streams = Unirest.get('https://api.twitch.tv/kraken/streams/')
                .header('Client-ID', '6iml9gxvz80elk7qxjve5ofezklffw')
                .queryString('game', 'Splatoon 2')
                .queryString('stream_type', 'live')
                .queryString('limit', 30)
                .asJson()
        def splatoon2Streams = getJsonObjectsForStreamResponse(responseSplatoon2Streams).collect { new TwitchStream(it) }

        List<TwitchStream> allStreams = [] + splatoonStreams + splatoon2Streams
        allStreams.sort(Comparator.comparingInt(new ToIntFunction<TwitchStream>() {
            @Override
            int applyAsInt(TwitchStream o) {
                return o.viewers
            }
        }).reversed())

        cachedLiveChannels.cache(allStreams)
        return allStreams
    }

    List<TwitchStream> getTopLiveChannels(int n) {
        def streams = getLiveChannels()
        return streams.stream().limit(n).collect(Collectors.toList());
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
