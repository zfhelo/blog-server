# 分类表
CREATE TABLE category(
`id` bigint unsigned PRIMARY KEY auto_increment COMMENT '类别id',
`name` VARCHAR (20) NOT NULL COMMENT '类别名称',
CONSTRAINT `uq_tag_name` UNIQUE(`name`),
`create_time` datetime DEFAULT NOW()  COMMENT '创建时间',
`update_time` datetime DEFAULT NOW()  COMMENT '修改时间'
);

INSERT INTO category(`id`, `name`) VALUES(1, '默认分类');

# 标签表
CREATE TABLE tag (
`id` bigint unsigned PRIMARY KEY AUTO_INCREMENT COMMENT '标签id',
`name` VARCHAR (20) NOT NULL COMMENT '标签名称',
CONSTRAINT `uq_tag_name` UNIQUE(`name`),
`create_time` datetime DEFAULT NOW()  COMMENT '创建时间',
`update_time` datetime DEFAULT NOW()  COMMENT '修改时间'
);

# 文章表
CREATE TABLE article (
`id` bigint unsigned PRIMARY KEY auto_increment COMMENT '文章id',
`title` VARCHAR (50) NOT NULL COMMENT '文章标题',
`content` LONGTEXT COMMENT '文章内容',
`description` VARCHAR(200) NOT NULL COMMENT '文章描述',
`is_commented` BOOLEAN COMMENT '是否开启评论',
`category_id` bigint unsigned COMMENT '类别id',
CONSTRAINT `fk_article_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE,
`send_time` datetime DEFAULT NOW()  COMMENT '发布时间',
`create_time` datetime DEFAULT NOW()  COMMENT '创建时间',
`update_time` datetime DEFAULT NOW()  COMMENT '修改时间'
);


# 文章标签关联表
CREATE TABLE bind_article_tag (
`article_id` bigint unsigned COMMENT '文章id',
`tag_id` bigint unsigned COMMENT '标签id',
CONSTRAINT `fk_bind_article_tag_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE,
CONSTRAINT `fk_bind_article_tag_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE CASCADE,
CONSTRAINT `uq_article_id_tag_id` UNIQUE(`article_id`, `tag_id`)
);

# 用户信息表
CREATE TABLE `user`(
`id` bigint unsigned PRIMARY KEY auto_increment COMMENT '用户id',
`username` VARCHAR (200) NOT NULL COMMENT '用户名',
`password` VARCHAR (200) NOT NULL COMMENT '密码',
`email` VARCHAR (200) COMMENT '邮箱',
`phone` VARCHAR(30) COMMENT '电话',
CONSTRAINT `uq_user_name` UNIQUE(`username`),
`is_valid` BOOLEAN DEFAULT 1 COMMENT '是否有效',
`create_time` datetime DEFAULT NOW()  COMMENT '创建时间',
`update_time` datetime DEFAULT NOW()  COMMENT '修改时间'
);

# 权限表
CREATE TABLE `authority`(
`id` bigint unsigned PRIMARY KEY auto_increment COMMENT '权限id',
`authority` VARCHAR (200) NOT NULL COMMENT '权限',
`create_time` datetime DEFAULT NOW()  COMMENT '创建时间',
`update_time` datetime DEFAULT NOW()  COMMENT '修改时间'
);

# 用户权限关联表
CREATE TABLE `bind_user_authority`(
`user_id` bigint unsigned COMMENT '用户id',
`authority_id` bigint unsigned COMMENT '权限id',
CONSTRAINT `fk_bind_user_authority_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
CONSTRAINT `fk_bind_user_authority_authority_id` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`) ON DELETE CASCADE,
CONSTRAINT `uq_user_id_authority_id` UNIQUE(`user_id`, `authority_id`)
);


# 评论表
CREATE TABLE `comment`(
`id` bigint unsigned PRIMARY KEY auto_increment COMMENT '评论id',
`user_id` bigint unsigned COMMENT '用户id',
`article_id` bigint unsigned COMMENT '文章id',
`content` VARCHAR (5000) NOT NULL COMMENT '内容',
`parent` int DEFAULT 0,
`ip` VARCHAR(200) NOT NULL COMMENT'评论用户ip',
CONSTRAINT `fk_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
CONSTRAINT `fk_comment_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE,
`create_time` datetime DEFAULT NOW()  COMMENT '创建时间',
`update_time` datetime DEFAULT NOW()  COMMENT '修改时间'
);
