/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  acer
 * Created: Jan 28, 2019
 */

create table color(
    color_id varchar(64) primary key not null,
    color_name varchar(20) not null,
    color_code varchar(10) not null,
    color_description text
)
