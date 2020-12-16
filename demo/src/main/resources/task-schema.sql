drop table if exists task CASCADE;
drop table if exists subject CASCADE;

create table if not exists subject(id bigint PRIMARY KEY AUTO_INCREMENT, `name` varchar(255) not null);
create table if not exists task(id bigint PRIMARY KEY AUTO_INCREMENT, `name` varchar(255) not null, subject_id bigint);
