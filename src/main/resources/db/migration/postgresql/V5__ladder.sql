CREATE TABLE team (
  id bigint NOT NULL,
  version bigint NOT NULL,
  name character varying(255) NOT NULL
);

ALTER TABLE ONLY team
  ADD CONSTRAINT team_pkey UNIQUE (id);

ALTER TABLE ONLY team
  ADD CONSTRAINT uk_team_name UNIQUE (name);



CREATE TABLE ladder(
  id bigint NOT NULL,
  version bigint NOT NULL,
  team_id BIGINT NOT NULL,
  event_id BIGINT NOT NULL,
  date TIMESTAMP NOT NULL,
  points INTEGER NOT NULL,
  wins INTEGER NOT NULL,
  loses INTEGER NOT NULL
);

ALTER TABLE ONLY ladder
  ADD CONSTRAINT ladder_pkey UNIQUE (id);

ALTER TABLE ONLY ladder
  ADD CONSTRAINT fk_ladder_team FOREIGN KEY (team_id) REFERENCES team(id);

ALTER TABLE ONLY ladder
  ADD CONSTRAINT fk_ladder_event FOREIGN KEY (event_id) REFERENCES tournament_event(id);

