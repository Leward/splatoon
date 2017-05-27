CREATE TABLE user_role
(
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL
);

ALTER TABLE ONLY user_role
  ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);

ALTER TABLE ONLY user_role
  ADD CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES "user"(id);

ALTER TABLE ONLY user_role
  ADD CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES role(id);