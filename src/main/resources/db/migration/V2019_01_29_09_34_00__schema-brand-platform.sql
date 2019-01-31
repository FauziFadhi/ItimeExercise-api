/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  acer
 * Created: Jan 28, 2019
 */

create table brand(
    brand_id varchar(64) primary key not null,
    brand_name varchar(20) not null,
    brand_description text
)
