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
    is_teaching_lesson boolean not null,
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
    lesson_notes int[] not null,
    is_attending_lesson boolean not null,
    date_created timestamp not null
);

create table if not exists lessons(
    id int primary key not null,
    teacher_id int not null,
    subject varchar(225) not null,
    lesson_time timestamp not null,
    students_attending int[] not null,
    foreign key (teacher_id) references faculties
    );

create table if not exists notes(
    id int primary key not null,
    lesson_id int,
    foreign key (lesson_id) references lessons
);

create table if not exists transacs(
    id int primary key not null,
    transac_type varchar(225) not null,
    transac_amount varchar(225) not null,
    transac_status varchar(225) not null,
    date_created timestamp not null,
    student_id int not null,
    foreign key (student_id) references students
);



