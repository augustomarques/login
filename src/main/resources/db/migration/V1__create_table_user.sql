create table _user (
    id bigint not null auto_increment,
    email varchar(255),
    firstname varchar(255),
    lastname varchar(255),
    password varchar(255),
    primary key (id)
);