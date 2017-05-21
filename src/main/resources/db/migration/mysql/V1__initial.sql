create table ad_reply
(
	id bigint auto_increment
		primary key,
	version bigint not null,
	ad_id bigint not null,
	author_id bigint not null,
	created_at datetime null,
	message varchar(255) not null
)
;

create index FK8nhficfjv7pa6d77xseoyri4l
	on ad_reply (ad_id)
;

create index FKg7opyxkdet2h8t6192y7lq8fx
	on ad_reply (author_id)
;

create table news
(
	id bigint auto_increment
		primary key,
	version bigint not null,
	content longtext not null,
	cover varchar(255) not null,
	date datetime null,
	title varchar(255) not null
)
;

create table recruiting_ad
(
	id bigint auto_increment
		primary key,
	version bigint not null,
	author_id bigint not null,
	created_at datetime null,
	message longtext not null,
	profile_url varchar(255) null,
	title varchar(255) not null,
	type varchar(255) not null
)
;

create index FKqqpl1y4j74t7ohcoophp9akry
	on recruiting_ad (author_id)
;

alter table ad_reply
	add constraint FK8nhficfjv7pa6d77xseoyri4l
		foreign key (ad_id) references splatoon.recruiting_ad (id)
;

create table role
(
	id bigint auto_increment
		primary key,
	version bigint not null,
	authority varchar(255) not null,
	constraint UK_irsamgnera6angm0prq1kemt2
		unique (authority)
)
;

create table tournament
(
	id bigint auto_increment
		primary key,
	version bigint not null,
	name varchar(255) not null,
	organizer_id bigint not null
)
;

create index FKdh5yesljchlnfk8el7ghxf70m
	on tournament (organizer_id)
;

create table tournament_event
(
	id bigint auto_increment
		primary key,
	version bigint not null,
	challonge_url varchar(255) null,
	date date not null,
	end_time time not null,
	start_time time not null,
	stream_url varchar(255) null,
	tournament_id bigint not null,
	events_idx int null,
	constraint FK1ds970b5jlrvsi99o9oaofoq9
		foreign key (tournament_id) references splatoon.tournament (id)
)
;

create index FK1ds970b5jlrvsi99o9oaofoq9
	on tournament_event (tournament_id)
;

create table tournament_organizer
(
	id bigint auto_increment
		primary key,
	version bigint not null,
	name varchar(255) not null,
	website varchar(255) not null
)
;

alter table tournament
	add constraint FKdh5yesljchlnfk8el7ghxf70m
		foreign key (organizer_id) references splatoon.tournament_organizer (id)
;

create table user
(
	id bigint auto_increment
		primary key,
	version bigint not null,
	account_expired bit not null,
	account_locked bit not null,
	email varchar(255) not null,
	enabled bit not null,
	password varchar(255) not null,
	password_expired bit not null,
	username varchar(255) not null,
	constraint UK_ob8kqyqqgmefl0aco34akdtpe
		unique (email),
	constraint UK_sb8bbouer5wak8vyiiy4pf2bx
		unique (username)
)
;

alter table ad_reply
	add constraint FKg7opyxkdet2h8t6192y7lq8fx
		foreign key (author_id) references splatoon.user (id)
;

alter table recruiting_ad
	add constraint FKqqpl1y4j74t7ohcoophp9akry
		foreign key (author_id) references splatoon.user (id)
;

create table user_role
(
	user_id bigint not null,
	role_id bigint not null,
	primary key (user_id, role_id),
	constraint FK859n2jvi8ivhui0rl0esws6o
		foreign key (user_id) references splatoon.user (id),
	constraint FKa68196081fvovjhkek5m97n3y
		foreign key (role_id) references splatoon.role (id)
)
;

create index FKa68196081fvovjhkek5m97n3y
	on user_role (role_id)
;

