ALTER TABLE player_profile
  ADD COLUMN presentation TEXT,
  ADD COLUMN created_at TIMESTAMP DEFAULT NOW(),
  ADD COLUMN updated_at TIMESTAMP DEFAULT NOW();


INSERT INTO player_profile (id, user_id, presentation, rank, looking_for_a_team, created_at, updated_at)
  (SELECT
     DISTINCT ON (recruiting_ad.author_id) -- needed as a user may have published several ads, so we keep only the most recent one for the data migration
     nextval('hibernate_sequence'),
     recruiting_ad.author_id,
     recruiting_ad.message,
     recruiting_ad.rank,
     TRUE,
     recruiting_ad.created_at,
     NOW()
   FROM recruiting_ad
   WHERE recruiting_ad.type = 'LOOKING_FOR_TEAM_AD'
   ORDER BY recruiting_ad.author_id, recruiting_ad.created_at DESC
  )
ON CONFLICT (user_id)
  DO UPDATE
    SET
      presentation       = EXCLUDED.presentation,
      rank               = EXCLUDED.rank,
      updated_at         = EXCLUDED.updated_at,
      looking_for_a_team = EXCLUDED.looking_for_a_team;

