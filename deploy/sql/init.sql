-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS devtest DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE devtest;

-- 创建用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `first_name` varchar(50) DEFAULT NULL COMMENT '姓',
  `last_name` varchar(50) DEFAULT NULL COMMENT '名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `user_status` int(11) DEFAULT NULL COMMENT '用户状态',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 插入测试数据
INSERT INTO `user` (`username`, `first_name`, `last_name`, `email`, `password`, `phone`, `user_status`) 
VALUES
('admin', NULL, NULL, 'admin@example.com', '123456', NULL, NULL),
('test', NULL, NULL, 'test@example.com', '123456', "13800138000", NULL),
('testuser1', NULL, NULL, 'user1@example.com', '123456', "13800138001", NULL),
('testuser2', NULL, NULL, 'user2@example.com', '123456', "13800138002", NULL),
('testuser3', NULL, NULL, 'user3@example.com', '123456', "13800138003", NULL);
