CREATE TABLE tournament_registration
(
  id               BIGINT    NOT NULL,
  version          BIGINT    NOT NULL,
  team_id          BIGINT    NOT NULL,
  registered_by_id BIGINT    NOT NULL,
  registered_at    TIMESTAMP NOT NULL,
  event_id         BIGINT    NOT NULL
);


ALTER TABLE ONLY tournament_registration
    ADD CONSTRAINT tournament_registration_pkey PRIMARY KEY(id);

ALTER TABLE ONLY tournament_registration
    ADD CONSTRAINT fk_tournament_registration_user FOREIGN KEY (registered_by_id) REFERENCES "user";

ALTER TABLE ONLY tournament_registration
  ADD CONSTRAINT fk_tournament_registration_tournament_event FOREIGN KEY (event_id) REFERENCES "tournament_event";