create table if not exists admins(
    id int primary key not null,
    first_name varchar(225) not null,
    last_name varchar(225) not null,
    email varchar(225) not null,
    mobile_no varchar(225) not null,
    dob timestamp not null,
    role varchar(225) not null,
    date_created timestamp not null
);

create table if not exists faculties(
    id int primary key not null,
    first_name varchar(225) not null,
    last_name varchar(225) not null,
    email varchar(225) not null,
    mobile_no varchar(225) not null,
    dob timestamp not null,
    role varchar(225) not null,
    registered_subjects text[] not null,
    date_created timestamp not null
);

create table if not exists acountants(
    id int primary key not null,
    first_name varchar(225) not null,
    last_name varchar(225) not null,
    email varchar(225) not null,
    mobile_no varchar(225) not null,
    dob timestamp not null,
    role varchar(225) not null,
    date_created timestamp not null
);

create table if not exists students(
    id int primary key not null,
    first_name varchar(225) not null,
    last_name varchar(225) not null,
    email varchar(225) not null,
    mobile_no varchar(225) not null,
    dob timestamp not null,
    role varchar(225) not null,
    fees_balance decimal not null,
    registered_subjects text[] not null,
    date_created timestamp not null
);

