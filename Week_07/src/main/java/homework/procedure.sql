DROP PROCEDURE IF EXISTS insert_order_million;

DELIMITER $$
CREATE PROCEDURE insert_order_million(IN ocnt int)
BEGIN
DECLARE i bigint unsigned DEFAULT 1;
WHILE i<ocnt DO
INSERT INTO `order`(user_id,goods_id,total_amount,status,create_time) VALUES(i,i,i,1,i);
SET i=i+1;
END WHILE;
END $$

DELIMITER;

CALL insert_order_million(1000000);