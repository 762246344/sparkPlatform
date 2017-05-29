create table person(id int comment '编号',gender string comment '性别',height int comment '身高',weight int comment '体重',age int comment '年龄',salary double comment '薪资') row format delimited fields terminated by '\t';
LOAD DATA LOCAL INPATH '/home/hadoop/apache-hive-1.2.1-bin/bin/data/info.txt' OVERWRITE INTO TABLE person;

create table student(id int comment '编号',name string comment '姓名',gender string comment '性别',height int comment '身高',weight int comment '体重',age int comment '年龄') row format delimited fields terminated by '\t';