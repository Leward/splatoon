CREATE SEQUENCE hibernate_sequence
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

SELECT pg_catalog.setval('hibernate_sequence', 1, false);

CREATE TABLE ad_reply (
  id bigint NOT NULL,
  version bigint NOT NULL,
  ad_id bigint NOT NULL,
  author_id bigint NOT NULL,
  created_at timestamp without time zone,
  message character varying(255) NOT NULL
);

CREATE TABLE recruiting_ad (
  id bigint NOT NULL,
  version bigint NOT NULL,
  author_id bigint NOT NULL,
  created_at timestamp without time zone,
  message text NOT NULL,
  profile_url character varying(255),
  title character varying(255) NOT NULL,
  type character varying(255) NOT NULL
);

CREATE TABLE news (
  id bigint NOT NULL,
  version bigint NOT NULL,
  content text NOT NULL,
  cover character varying(255) NOT NULL,
  date timestamp without time zone,
  title character varying(255) NOT NULL
);

CREATE TABLE role (
  id bigint NOT NULL,
  version bigint NOT NULL,
  authority character varying(255) NOT NULL
);

CREATE TABLE tournament (
  id bigint NOT NULL,
  version bigint NOT NULL,
  name character varying(255) NOT NULL,
  organizer_id bigint NOT NULL
);

CREATE TABLE tournament_event (
  id bigint NOT NULL,
  version bigint NOT NULL,
  challonge_url character varying(255),
  date date NOT NULL,
  end_time time without time zone NOT NULL,
  start_time time without time zone NOT NULL,
  stream_url character varying(255),
  tournament_id bigint NOT NULL,
  events_idx integer
);

CREATE TABLE tournament_organizer (
  id bigint NOT NULL,
  version bigint NOT NULL,
  name character varying(255) NOT NULL,
  website character varying(255) NOT NULL
);

CREATE TABLE "user" (
  id bigint NOT NULL,
  version bigint NOT NULL,
  account_expired boolean NOT NULL,
  account_locked boolean NOT NULL,
  email character varying(255) NOT NULL,
  enabled boolean NOT NULL,
  password character varying(255) NOT NULL,
  password_expired boolean NOT NULL,
  username character varying(255) NOT NULL
);

ALTER TABLE ONLY ad_reply
  ADD CONSTRAINT ad_reply_pkey PRIMARY KEY (id);

ALTER TABLE ONLY news
  ADD CONSTRAINT news_pkey PRIMARY KEY (id);

ALTER TABLE ONLY recruiting_ad
  ADD CONSTRAINT recruiting_ad_pkey PRIMARY KEY (id);

ALTER TABLE ONLY role
  ADD CONSTRAINT role_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tournament_event
  ADD CONSTRAINT tournament_event_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tournament_organizer
  ADD CONSTRAINT tournament_organizer_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tournament
  ADD CONSTRAINT tournament_pkey PRIMARY KEY (id);

ALTER TABLE ONLY role
  ADD CONSTRAINT uk_irsamgnera6angm0prq1kemt2 UNIQUE (authority);

ALTER TABLE ONLY "user"
  ADD CONSTRAINT uk_ob8kqyqqgmefl0aco34akdtpe UNIQUE (email);

ALTER TABLE ONLY "user"
  ADD CONSTRAINT uk_sb8bbouer5wak8vyiiy4pf2bx UNIQUE (username);

ALTER TABLE ONLY "user"
  ADD CONSTRAINT user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tournament_event
  ADD CONSTRAINT fk1ds970b5jlrvsi99o9oaofoq9 FOREIGN KEY (tournament_id) REFERENCES tournament(id);

ALTER TABLE ONLY ad_reply
  ADD CONSTRAINT fk1hyf68dktxg0mkmfqdkgysw73 FOREIGN KEY (author_id) REFERENCES "user"(id);

ALTER TABLE ONLY ad_reply
  ADD CONSTRAINT fk8nhficfjv7pa6d77xseoyri4l FOREIGN KEY (ad_id) REFERENCES recruiting_ad(id);

ALTER TABLE ONLY recruiting_ad
  ADD CONSTRAINT fkatyvb33hxukwlsfswhv6wvxnw FOREIGN KEY (author_id) REFERENCES "user"(id);

ALTER TABLE ONLY tournament
  ADD CONSTRAINT fkdh5yesljchlnfk8el7ghxf70m FOREIGN KEY (organizer_id) REFERENCES tournament_organizer(id);
