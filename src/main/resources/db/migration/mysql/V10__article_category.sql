CREATE TABLE article_category (
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  version BIGINT       NOT NULL,
  name    VARCHAR(255) NOT NULL
);

INSERT INTO article_category (id, version, name) VALUES (1, 1, 'Change me');

ALTER TABLE article
  ADD COLUMN category_id BIGINT;

UPDATE article SET category_id = 1;

ALTER TABLE article
  MODIFY category_id BIGINT NOT NULL;

ALTER TABLE article
  ADD CONSTRAINT fk_article_category FOREIGN KEY (category_id) REFERENCES article_category (id);