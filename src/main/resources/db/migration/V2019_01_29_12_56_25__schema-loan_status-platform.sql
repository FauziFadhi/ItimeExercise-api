create table loan_status (
  id          varchar(64)  not null primary key,
  name        varchar(50) not null,
  description text,
  color_id        varchar(64) not null
) engine = InnoDB;
