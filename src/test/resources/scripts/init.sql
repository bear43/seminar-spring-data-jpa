create table "user" (
    id bigserial primary key,
    nickname text not null unique
);

create table form (
    id bigserial primary key,
    title text not null
);

create table question (
    id bigserial primary key,
    form_id int8 not null references form(id),
    "text" text not null
);

create table answer (
    id bigserial primary key,
    question_id int8 not null references question(id),
    "text" text not null
);