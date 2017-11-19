CREATE TABLE team_member (
  id      BIGINT,
  version BIGINT       NOT NULL,
  team_id BIGINT       NOT NULL REFERENCES team(id),
  name    VARCHAR(255) NOT NULL,
  type    VARCHAR(255) NOT NULL,
  avatar  VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE team_member_roles (
  team_member_id BIGINT       NOT NULL REFERENCES team_member(id),
  player_role    VARCHAR(255) NOT NULL,
  PRIMARY KEY (team_member_id, player_role)
);
