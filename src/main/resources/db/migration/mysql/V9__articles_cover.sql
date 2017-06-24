create table article
(
  id bigint auto_increment primary key,
  version bigint not null,
  content longtext not null,
  cover_id BIGINT not null,
  date datetime null,
  title varchar(255) not null
);

ALTER TABLE article
  ADD CONSTRAINT fk_article_cover FOREIGN KEY (cover_id) REFERENCES cover (id);