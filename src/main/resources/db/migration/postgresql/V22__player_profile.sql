CREATE TABLE player_profile (
  id                          BIGINT,
  version                     BIGINT NOT NULL DEFAULT 0,
  user_id                     BIGINT UNIQUE REFERENCES "user" (id),
  nintendo_id                 VARCHAR(100),
  birth_date                  DATE,
  main_weapon_category        VARCHAR(255),
  already_in_a_team           BOOLEAN DEFAULT FALSE,
  looking_for_a_team          BOOLEAN DEFAULT FALSE,
  looking_for_fun_competition BOOLEAN DEFAULT FALSE,
  looking_for_pro_competition BOOLEAN DEFAULT FALSE,
  availability                VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE player_profile_roles (
  player_profile_id BIGINT       NOT NULL REFERENCES player_profile (id),
  player_role       VARCHAR(255) NOT NULL,
  PRIMARY KEY (player_profile_id, player_role)
);

-- Migrate existing data into the new table structure
INSERT INTO player_profile (id, user_id, nintendo_id)
  (SELECT
     nextval('hibernate_sequence'),
     id,
     nintendo_id
   FROM "user"
   WHERE nintendo_id IS NOT NULL);

