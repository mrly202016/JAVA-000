DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `goods_name` varchar(500) DEFAULT NULL COMMENT '商品名称',
  `price` bigint(20) unsigned NOT NULL COMMENT '商品价格',
  `sale_status` tinyint(1) DEFAULT NULL COMMENT '上架状态：0->下架；1->上架',
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `unit` varchar(16) DEFAULT NULL COMMENT '单位',
  `lock_stock` int(11) DEFAULT '0' COMMENT '锁定库存',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品表';