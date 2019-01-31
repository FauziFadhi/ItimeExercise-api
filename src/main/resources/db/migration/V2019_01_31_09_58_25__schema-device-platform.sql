create table device (
  device_number          int(8)  not null primary key auto_increment,
  device_name        varchar(50) not null,
  device_description text,
  color_id        varchar(64) not null,
  brand_id	varchar(64) not null,
  unit_capacity_id varchar(64) not null,
  category_device_id varchar(64) not null,
  condition_id varchar(64) not null,
  loan_status_id varchar(64) not null
) engine = InnoDB;
