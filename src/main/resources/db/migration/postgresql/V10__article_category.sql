CREATE TABLE article_category (
  id      BIGINT                 NOT NULL,
  version BIGINT                 NOT NULL,
  name    CHARACTER VARYING(255) NOT NULL
);

ALTER TABLE ONLY article_category
  ADD CONSTRAINT article_category_pkey UNIQUE (id);

ALTER TABLE article
  ADD COLUMN category_id BIGINT;

UPDATE article
SET category_id = 1;

ALTER TABLE article
  ALTER COLUMN category_id SET NOT NULL;

ALTER TABLE article
  ADD CONSTRAINT fk_article_category FOREIGN KEY (category_id) REFERENCES article_category (id);