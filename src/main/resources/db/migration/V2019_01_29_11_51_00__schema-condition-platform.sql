/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  acer
 * Created: Jan 28, 2019
 */

create table conditions(
    condition_id varchar(64) not null primary key,
    condition_name varchar(20) not null,
    condition_description text
) 
