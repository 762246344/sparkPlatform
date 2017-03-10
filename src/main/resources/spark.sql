

DROP TABLE IF EXISTS user_info;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `production_line` varchar(128) NOT NULL COMMENT '产品线',
  `contacts` varchar(1024) NOT NULL COMMENT '接口人，多个email用逗号分隔',
  `last_chg_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '本条记录生成时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='集群用户表'

