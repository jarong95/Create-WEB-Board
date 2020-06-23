Create table personal_project_member(
id varchar2(50) primary key,
password varchar2(100) not null,
email varchar2(50) not null,
name varchar2(30) not null,
deletedate date);
drop table personal_project_member;

create table personal_project_board(
boardnum number primary key,
title varchar2(100) not null,
inputdate date default sysdate,
id varchar2(50) not null,
foreign key(id) references personal_project_member(id) on delete cascade,
content varchar2(4000) not null,
filename varchar2(100),
filesavename varchar2(200),
likenum number default 0);

create sequence peronal_project_boardnum_seq;
create sequence personal_project_replynum_seq;

create table personal_project_reply(
id varchar2(50) not null,
foreign key(id) references personal_project_member(id) on delete cascade,
content varchar2(1000) not null,
replynum number primary key,
inputdate date default sysdate,
bnum number not null,
foreign key(bnum) references personal_project_board(boardnum) on delete cascade);

create table personal_project_likeid(
id varchar2(50) not null,
foreign key(id) references personal_project_member(id) on delete cascade,
bnum number not null,
foreign key(bnum) references personal_project_board(boardnum) on delete cascade);