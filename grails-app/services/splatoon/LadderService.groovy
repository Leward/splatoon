package splatoon

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import splatoon.elo.PerTOAllTeamsEloRankings

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

    PerTOAllTeamsEloRankings calculateEloRankings() {
        def events = TournamentEvent.list()
        def rankings = new PerTOAllTeamsEloRankings()
        rankings.add(events)
        return rankings
    }

    void invalidCache() {
        cachedRankings.invalid()
    }
}
