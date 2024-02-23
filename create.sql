create table audit_log_info (audit_log_info_id bigint not null auto_increment, created_at datetime(6), updated_at datetime(6), identification varchar(255), result_of_case varchar(255), type_of_case varchar(255), primary key (audit_log_info_id)) engine=InnoDB;
create table bearer_token (bearer_token_id bigint not null auto_increment, access_token varchar(255), member_id varchar(255), refresh_token varchar(255), primary key (bearer_token_id)) engine=InnoDB;
create table host (created_at datetime(6), host_id bigint not null auto_increment, updated_at datetime(6), ip varchar(255), name varchar(255), primary key (host_id)) engine=InnoDB;
create table host_status_history (alive_time datetime(6), created_at datetime(6), host_id bigint, host_status_history_id bigint not null auto_increment, updated_at datetime(6), alive_status enum ('ALIVE','NOT_ALIVE'), primary key (host_status_history_id)) engine=InnoDB;
create table member (created_at datetime(6), id bigint not null auto_increment, updated_at datetime(6), member_id varchar(255), password varchar(255), primary key (id)) engine=InnoDB;
create table system_log (created_at datetime(6), system_log_id bigint not null auto_increment, updated_at datetime(6), event_type varchar(255), target_id varchar(255), primary key (system_log_id)) engine=InnoDB;
create table token_blacklist_info (created_at datetime(6), id bigint not null auto_increment, updated_at datetime(6), access_token varchar(255), member_id varchar(255), primary key (id)) engine=InnoDB;
alter table host_status_history add constraint FKgtiv21bgm928aubqyl50h5mh5 foreign key (host_id) references host (host_id);
