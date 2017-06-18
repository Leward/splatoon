ALTER TABLE cover
  ADD CONSTRAINT uk_cover_name UNIQUE (name);

INSERT IGNORE INTO cover (version, name, url)
VALUES (1, 'Splatoon 2',
        'https://s3-eu-central-1.amazonaws.com/splatoon/0be69296-2b43-4c19-8d2e-b567df7f5fdb-image.png');

ALTER TABLE news
  ADD COLUMN cover_id BIGINT;

UPDATE news SET cover_id = (SELECT id FROM cover LIMIT 1);

ALTER TABLE news
    MODIFY cover_id BIGINT NOT NULL;

ALTER TABLE news
  ADD CONSTRAINT fk_news_cover FOREIGN KEY (cover_id) REFERENCES cover (id);
