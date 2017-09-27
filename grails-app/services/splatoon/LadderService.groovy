package splatoon

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic

import java.time.Duration

@Transactional
@CompileStatic
class LadderService {

    private final CachedValue<RankingsCompilation> cachedRankings = new CachedValue<>(Duration.ofMinutes(5))

    RankingsCompilation getRankings() {
        if(cachedRankings.cacheValid) {
            return cachedRankings.value
        }
        def rankingsCompilation = new RankingsCompilation(Ladder.findAll())
        cachedRankings.cache(rankingsCompilation)
        return rankingsCompilation
    }

    void invalidCache() {
        cachedRankings.invalid()
    }
}
