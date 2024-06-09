CREATE TABLE `user`
(
    `id`       int         primary key,
    `username` varchar(50) comment '用户名',
    `password` varchar(50) comment '密码',
    `email`    varchar(50) comment '邮箱'
) comment '用户表';

create table product
(
    id          int primary key,
    name        varchar(50) comment '商品名称',
    price       decimal(10, 2) comment '商品价格',
    description text comment '商品描述',
    creat_time  datetime comment '创建时间',
    update_time datetime comment '更新时间'
) comment '商品表';
create table cart
(
    id          int primary key,
    user_id     int comment '用户id',
    product_id  int comment '商品id',
    count       int comment '商品数量',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间'
) comment '购物车表';
