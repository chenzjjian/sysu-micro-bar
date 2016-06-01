/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/*==============================================================*/


drop table if exists account;

drop table if exists floor;

drop table if exists floor_file;

drop table if exists history_message;

drop table if exists post;

/*==============================================================*/
/* Table: account                                               */
/*==============================================================*/
create table account
(
   id                   int not null auto_increment,
   stu_no               varchar(100),
   password             varchar(200),
   nickname             varchar(100),
   head_image_url       varchar(500),
   authority            smallint default 1,
   register_time        timestamp default now() on update now(),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table: floor                                                 */
/*==============================================================*/
create table floor
(
   id                   int not null auto_increment,
   post_id              int,
   account_id           int not null,
   reply_floor_id       int,
   is_reply             bool,
   detail               text,
   level_num            int default 0,
   create_time          timestamp default now() on update now(),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table: floor_file                                            */
/*==============================================================*/
create table floor_file
(
   id                   int not null auto_increment,
   floor_id             int not null,
   file_url             varchar(500),
   file_type            smallint default 1,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table: history_message                                       */
/*==============================================================*/
create table history_message
(
   id                   int not null auto_increment,
   account_id           int,
   floor_id             int,
   is_checked           bool default false,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: post                                                  */
/*==============================================================*/
create table post
(
   id                   int not null auto_increment,
   creator_id           int not null,
   title                varchar(50),
   tag                  smallint default 0,
   create_time          timestamp default now() on update now(),
   modify_time          timestamp default now() on update now(),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


alter table floor add constraint FK_Reference_2 foreign key (post_id)
      references post (id) on delete restrict on update restrict;

alter table floor add constraint FK_Reference_4 foreign key (account_id)
      references account (id) on delete restrict on update restrict;

alter table floor add constraint FK_Reference_5 foreign key (reply_floor_id)
      references floor (id) on delete restrict on update restrict;

alter table floor_file add constraint FK_Reference_3 foreign key (floor_id)
      references floor (id) on delete restrict on update restrict;

alter table history_message add constraint FK_Reference_6 foreign key (account_id)
      references account (id) on delete restrict on update restrict;

alter table history_message add constraint FK_Reference_7 foreign key (floor_id)
      references floor (id) on delete restrict on update restrict;

