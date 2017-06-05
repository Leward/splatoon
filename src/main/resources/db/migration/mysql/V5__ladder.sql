CREATE TABLE team (
  id bigint auto_increment primary key,
  version bigint NOT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT uk_team_name UNIQUE (name)
);

CREATE TABLE ladder(
  id bigint auto_increment primary key,
  version bigint NOT NULL,
  team_id BIGINT NOT NULL,
  event_id BIGINT NOT NULL,
  date TIMESTAMP NOT NULL,
  points INTEGER NOT NULL,
  wins INTEGER NOT NULL,
  loses INTEGER NOT NULL
);

CREATE INDEX fk_index_ladder_team ON ladder(team_id);
ALTER TABLE ladder ADD CONSTRAINT fk_ladder_team FOREIGN KEY (team_id) REFERENCES team(id);

CREATE INDEX fk_index_ladder_event ON ladder(event_id);
ALTER TABLE ladder ADD CONSTRAINT fk_ladder_event FOREIGN KEY (event_id) REFERENCES tournament_event(id);

