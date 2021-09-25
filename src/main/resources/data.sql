create table partner (
    id bigint not null auto_increment,
    address point not null,
    coverage_area multipolygon not null,
    document varchar(255) not null,
    owner_name varchar(255) not null,
    trading_name varchar(255) not null,
    primary key (id)
) engine=InnoDB;