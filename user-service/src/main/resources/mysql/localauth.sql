CREATE TABLE `t_localauth` (
	`user_id` INT(12) UNSIGNED ZEROFILL NOT NULL,
	`login_name` VARCHAR(180) NOT NULL,
	`password` CHAR(50) NOT NULL,
	`auth_status` TINYINT(4) NOT NULL DEFAULT '0'
)
COMMENT='本地认证信息表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
