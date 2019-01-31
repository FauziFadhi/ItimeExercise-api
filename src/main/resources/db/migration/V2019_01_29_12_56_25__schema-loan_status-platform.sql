create table loan_status (
  loan_status_id          varchar(64)  not null primary key,
  loan_status_name        varchar(50) not null,
  loan_status_description text,
  color_id        varchar(64) not null
) engine = InnoDB;
