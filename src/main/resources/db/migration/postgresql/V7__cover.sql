CREATE TABLE cover(
  id BIGINT NOT NULL,
  version bigint NOT NULL,
  name VARCHAR NOT NULL,
  url VARCHAR NOT NULL
);

ALTER TABLE ONLY cover
  ADD CONSTRAINT cover_pkey UNIQUE (id);