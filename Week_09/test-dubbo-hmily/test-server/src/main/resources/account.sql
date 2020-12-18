DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) NOT NULL,
  `cny_balance` decimal(10,0) NOT NULL COMMENT '人民币余额',
  `cny_freeze_amount` decimal(10,0) NOT NULL COMMENT '人民币冻结金额，扣款暂存余额',
  `usd_balance` decimal(10,0) NOT NULL COMMENT '美元余额',
  `usd_freeze_amount` decimal(10,0) NOT NULL COMMENT '美元冻结金额，扣款暂存余额',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;