CREATE TABLE tournament_organizer_members(
  tournament_organizer_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL
);

ALTER TABLE ONLY tournament_organizer_members
  ADD CONSTRAINT fk_tournament_organizer_members_tournament_organizer FOREIGN KEY (tournament_organizer_id) REFERENCES tournament_organizer(id);

ALTER TABLE ONLY tournament_organizer_members
  ADD CONSTRAINT fk_tournament_organizer_members_user FOREIGN KEY (user_id) REFERENCES "user"(id);
