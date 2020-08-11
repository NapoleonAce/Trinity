/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/8/24 9:28:18                            */
/*==============================================================*/


drop table if exists apply_info;

drop table if exists college;

drop table if exists domain;

drop table if exists enroll;

drop table if exists general_grade;

drop table if exists major_grade;

drop table if exists managers;

drop table if exists recommand;

drop table if exists speciality;

drop table if exists student;

drop table if exists subject_info;

/*==============================================================*/
/* Table: apply_info                                            */
/*==============================================================*/
create table apply_info
(
   college_id           smallint not null,
   apply_condition      varchar(1000) not null,
   apply_begin          timestamp not null,
   apply_finish         timestamp not null,
   apply_way            varchar(1000) not null,
   apply_info_id        mediumint not null auto_increment,
   primary key (apply_info_id)
);

/*==============================================================*/
/* Table: college                                               */
/*==============================================================*/
create table college
(
   college_id           smallint not null auto_increment,
   college_name         varchar(40) not null,
   province             varchar(20) not null,
   city                 varchar(20) not null,
   content              varchar(400),
   primary key (college_id)
);

/*==============================================================*/
/* Table: domain                                                */
/*==============================================================*/
create table domain
(
   domain_id            int not null auto_increment,
   domain_name          varchar(40) not null,
   domain_type          varchar(20) not null,
   college_id           smallint not null,
   content              varchar(400),
   primary key (domain_id)
);

/*==============================================================*/
/* Table: enroll                                                */
/*==============================================================*/
create table enroll
(
   year                 year(2) not null,
   domain_id            int not null,
   subject_req          varchar(20) not null,
   number               tinyint not null,
   price                float not null,
   primary key (year, domain_id)
);

/*==============================================================*/
/* Table: general_grade                                         */
/*==============================================================*/
create table general_grade
(
   student_id           varchar(18) not null,
   subject_id           tinyint not null,
   grade                varchar(2) not null,
   primary key (student_id, subject_id)
);

/*==============================================================*/
/* Table: major_grade                                           */
/*==============================================================*/
create table major_grade
(
   student_id           varchar(18) not null,
   subject_id           tinyint not null,
   score                tinyint not null,
   primary key (student_id, subject_id)
);

/*==============================================================*/
/* Table: managers                                              */
/*==============================================================*/
create table managers
(
   man_code             varchar(6) not null,
   man_name             varchar(8) not null,
   man_level            varchar(8) not null,
   man_password         vachar(6) not null,
   primary key (man_code)
);

/*==============================================================*/
/* Table: recommand                                             */
/*==============================================================*/
create table recommand
(
   student_id           varchar(18) not null,
   domain_id            int not null,
   reason               varchar(400) not null,
   primary key (student_id, domain_id)
);

/*==============================================================*/
/* Table: speciality                                            */
/*==============================================================*/
create table speciality
(
   student_id           varchar(18) not null,
   spe_code             int not null auto_increment,
   spe_type             varchar(10) not null,
   spe_level            varchar(5) not null,
   content              varchar(400) not null,
   evidence             varchar(400) not null,
   primary key (spe_code)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   student_id           varchar(18) not null,
   student_name         varchar(10),
   exam_id              varchar(10),
   gender               varchar(2),
   phone                varchar(11),
   school               varchar(30),
   province             varchar(15),
   primary key (student_id)
);

/*==============================================================*/
/* Table: subject_info                                          */
/*==============================================================*/
create table subject_info
(
   subject_id           tinyint not null auto_increment,
   subject_name         varchar(4) not null,
   primary key (subject_id)
);

alter table apply_info add constraint FK_Reference_1 foreign key (college_id)
      references college (college_id) on delete restrict on update restrict;

alter table domain add constraint FK_Reference_2 foreign key (college_id)
      references college (college_id) on delete restrict on update restrict;

alter table enroll add constraint FK_Reference_3 foreign key (domain_id)
      references domain (domain_id) on delete restrict on update restrict;

alter table general_grade add constraint FK_Reference_6 foreign key (student_id)
      references student (student_id) on delete restrict on update restrict;

alter table general_grade add constraint FK_Reference_7 foreign key (subject_id)
      references subject_info (subject_id) on delete restrict on update restrict;

alter table major_grade add constraint FK_Reference_5 foreign key (student_id)
      references student (student_id) on delete restrict on update restrict;

alter table recommand add constraint FK_Reference_4 foreign key (domain_id)
      references domain (domain_id) on delete restrict on update restrict;

alter table recommand add constraint FK_Reference_9 foreign key (student_id)
      references student (student_id) on delete restrict on update restrict;

alter table speciality add constraint FK_Reference_8 foreign key (student_id)
      references student (student_id) on delete restrict on update restrict;

