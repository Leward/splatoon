CREATE TABLE article (
  id bigint NOT NULL,
  version bigint NOT NULL,
  content text NOT NULL,
  cover_id BIGINT NOT NULL,
  date timestamp without time zone,
  title character varying(255) NOT NULL
);

ALTER TABLE ONLY article
  ADD CONSTRAINT fk_article_cover FOREIGN KEY (cover_id) REFERENCES cover (id);