
--# create table customer 
CREATE TABLE IF NOT EXISTS `customer` (
	`id` bigint(10) not null auto_increment,
	`name` varchar(20) not null,
	`no_of_orders` int(5) default '0',
	`tier` varchar(9) default 'regular',
	`wallet` decimal(7,2) not null default '0.00',
	primary key (`id`)
);


--# create table orders
CREATE TABLE IF NOT EXISTS `orders` (
	`id` int(10) not null auto_increment,
	`customer_id` bigint(10) not null references `customer`(`id`),
	`product` varchar(200) not null,
	`quantity` int(5) not null default '1',
	`amount` decimal(7,2) not null default '0.00',
	`discount` decimal(7,2) default '0.00',
	`discount_claimed` bool default 0,
	primary key (`id`)
);