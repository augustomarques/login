create table token (
    id bigint not null auto_increment,
    revoked bit,
    token varchar(255),
    user_id bigint,
    primary key (id)
);

alter table token
    add constraint UK_token_token unique (token);

alter table token
   add constraint FK_token_user_id
   foreign key (user_id)
   references _user (id);