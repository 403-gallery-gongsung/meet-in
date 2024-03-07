create table if not exists `sample`
(
    `id`          bigint      not null auto_increment,
    value         varchar(30) null,
    db_created_at datetime    not null default current_timestamp,
    db_updated_at datetime    not null default current_timestamp on update current_timestamp,
    primary key (`id`),
    key `idx_value` (`value`)
);