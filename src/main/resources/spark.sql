
DROP TABLE IF EXISTS task;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `batch_id` int(20) NOT NULL COMMENT 'livy batch id',
  `name` varchar(30) NOT NULL COMMENT '任务名',
  `app_id` varchar(128) NOT NULL COMMENT 'yarn任务id',
  `yarn_url` varchar(300) NOT NULL COMMENT 'yatn 任务 url',
  `type` varchar(20) NOT NULL COMMENT '语音类型',
  `state` varchar(20) NOT NULL COMMENT '状态',
  `active` BOOLEAN NOT NULL COMMENT '是否激活',
  `start_time` varchar(100) NOT NULL COMMENT '开始时间',
  `stop_time` varchar(100) NOT NULL COMMENT '结束时间',
  `last_chg_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '本条记录生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='任务表'