ALTER TABLE team
    ADD COLUMN leader_id  BIGINT;

ALTER TABLE team
  ADD CONSTRAINT fk_team_user_leader FOREIGN KEY (leader_id) REFERENCES "user";