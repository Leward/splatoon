package splatoon

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import splatoon.elo.AllTeamsEloRankings

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

    AllTeamsEloRankings calculateEloRankings() {
        def events = TournamentEvent.list()
        def rankings = new AllTeamsEloRankings()
        rankings.add(events)
        return rankings
    }

    void invalidCache() {
        cachedRankings.invalid()
    }
}
