/*
 Navicat Premium Data Transfer

 Source Server         : x1_project
 Source Server Type    : MySQL
 Source Server Version : 50736 (5.7.36)
 Source Host           : 192.168.179.135:3306
 Source Schema         : living_commodity

 Target Server Type    : MySQL
 Target Server Version : 50736 (5.7.36)
 File Encoding         : 65001

 Date: 26/03/2023 15:38:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commodity_attr
-- ----------------------------
DROP TABLE IF EXISTS `commodity_attr`;
CREATE TABLE `commodity_attr`  (
  `attr_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '属性 id',
  `attr_name` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性名',
  `search_type` tinyint(4) NULL DEFAULT NULL COMMENT '是否需要检索[0-不需要，1-需要]',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `value_select` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '可选值列表[用逗号分隔]',
  `attr_type` tinyint(4) NULL DEFAULT NULL COMMENT '属性类型[0-销售属性，1-基本属性]',
  `ENABLE` bigint(20) NULL DEFAULT NULL COMMENT '启用状态[0 - 禁用，1 - 启用]',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分类',
  `show_desc` tinyint(4) NULL DEFAULT NULL COMMENT '快速展示【是否展示在介绍上；0-否 1-是】\r\n',
  PRIMARY KEY (`attr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_attr
-- ----------------------------
INSERT INTO `commodity_attr` VALUES (6, '材料', 1, 'logo', '普通塑料', 1, 1, 301, 1);
INSERT INTO `commodity_attr` VALUES (7, '净重', 1, 'logo', '1kg', 1, 1, 301, 1);
INSERT INTO `commodity_attr` VALUES (8, '亮度', 1, 'logo', '高亮', 1, 1, 301, 1);
INSERT INTO `commodity_attr` VALUES (9, '面积', NULL, 'logo', '100*100,300*300', 0, 1, 301, NULL);
INSERT INTO `commodity_attr` VALUES (10, '颜色', NULL, 'logo', '红色,绿色,黄色', 0, 1, 301, NULL);

-- ----------------------------
-- Table structure for commodity_attr_attrgroup_relation
-- ----------------------------
DROP TABLE IF EXISTS `commodity_attr_attrgroup_relation`;
CREATE TABLE `commodity_attr_attrgroup_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attr_id` bigint(20) NULL DEFAULT NULL COMMENT '属性 id',
  `attr_group_id` bigint(20) NULL DEFAULT NULL COMMENT '属性分组 id',
  `attr_sort` int(11) NULL DEFAULT NULL COMMENT '属性组内排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品属性和商品属性组的关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_attr_attrgroup_relation
-- ----------------------------
INSERT INTO `commodity_attr_attrgroup_relation` VALUES (8, 6, 1, NULL);
INSERT INTO `commodity_attr_attrgroup_relation` VALUES (9, 7, 2, NULL);
INSERT INTO `commodity_attr_attrgroup_relation` VALUES (10, 8, 3, NULL);

-- ----------------------------
-- Table structure for commodity_attrgroup
-- ----------------------------
DROP TABLE IF EXISTS `commodity_attrgroup`;
CREATE TABLE `commodity_attrgroup`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组名',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组图标',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分类 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家居商品属性分组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_attrgroup
-- ----------------------------
INSERT INTO `commodity_attrgroup` VALUES (1, '主体', 0, '主体说明', '1', 301);
INSERT INTO `commodity_attrgroup` VALUES (2, '规格', 0, '规格说明', '1', 301);
INSERT INTO `commodity_attrgroup` VALUES (3, '功能', 0, '功能说明', '1', 301);
INSERT INTO `commodity_attrgroup` VALUES (4, '档次', 1, '1', '1', 202);

-- ----------------------------
-- Table structure for commodity_brand
-- ----------------------------
DROP TABLE IF EXISTS `commodity_brand`;
CREATE TABLE `commodity_brand`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌名',
  `logo` varchar(1200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'logo',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '说明',
  `isShow` tinyint(4) NULL DEFAULT NULL COMMENT '显示',
  `first_letter` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '检索首字母',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家居品牌' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_brand
-- ----------------------------
INSERT INTO `commodity_brand` VALUES (1, '海信', 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-15/37a9333a-f9d2-41ff-8bb6-69128dae2eec_ttpfx.jpg', '1', 1, 'a', 1);
INSERT INTO `commodity_brand` VALUES (5, '美的1', 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-13/363c430b-cf4e-4d6b-84b9-d7df9cf2ee52_ysn.jpg', '1', 1, 'a', 1);
INSERT INTO `commodity_brand` VALUES (6, '格力x', 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-13/3d245add-7548-42b5-82ae-d3a8ae53ac5f_lp2.png', '3', 1, 'a', 3);
INSERT INTO `commodity_brand` VALUES (7, '小米1', 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-13/e3359102-9e22-42d9-8ce8-f27db9e12b6a_千束.jpg', '5', 1, 'i', 5);

-- ----------------------------
-- Table structure for commodity_category
-- ----------------------------
DROP TABLE IF EXISTS `commodity_category`;
CREATE TABLE `commodity_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父分类 id',
  `cat_level` int(11) NOT NULL COMMENT '层级',
  `is_show` tinyint(4) NOT NULL COMMENT '0 不显示，1 显示]',
  `sort` int(11) NOT NULL COMMENT '排序',
  `icon` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `pro_unit` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '统计单位',
  `pro_count` int(11) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 656 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_category
-- ----------------------------
INSERT INTO `commodity_category` VALUES (1, '家用电器', 0, 1, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (2, '家居家装', 0, 1, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (21, '大 家 电', 1, 2, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (22, '厨卫大电', 1, 2, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (41, '家纺', 2, 2, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (42, '灯具', 2, 2, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (201, '燃气灶', 22, 3, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (202, '油烟机', 22, 3, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (301, '平板电视', 21, 3, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (601, '桌布/罩件', 41, 3, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (602, '地毯地垫', 41, 3, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (651, '台灯', 42, 3, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (652, '节能灯', 42, 3, 0, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (653, '手电筒12', 42, 3, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (654, '123', 42, 3, 1, 0, '', '', 0);
INSERT INTO `commodity_category` VALUES (655, '333', 42, 3, 1, 0, '', '', 0);

-- ----------------------------
-- Table structure for commodity_category_brand_relation
-- ----------------------------
DROP TABLE IF EXISTS `commodity_category_brand_relation`;
CREATE TABLE `commodity_category_brand_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌 id',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类 id',
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '品牌分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_category_brand_relation
-- ----------------------------
INSERT INTO `commodity_category_brand_relation` VALUES (1, 1, 301, '海信', '平板电视');
INSERT INTO `commodity_category_brand_relation` VALUES (3, 5, 202, '美的1', '油烟机');
INSERT INTO `commodity_category_brand_relation` VALUES (4, 5, 201, '美的1', '燃气灶');
INSERT INTO `commodity_category_brand_relation` VALUES (5, 6, 652, '格力x', '节能灯');

-- ----------------------------
-- Table structure for commodity_product_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `commodity_product_attr_value`;
CREATE TABLE `commodity_product_attr_value`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `spu_id` bigint(20) NULL DEFAULT NULL COMMENT '商品 id',
  `attr_id` bigint(20) NULL DEFAULT NULL COMMENT '属性 id',
  `attr_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性名',
  `attr_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性值',
  `attr_sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `quick_show` tinyint(4) NULL DEFAULT NULL COMMENT '快速展示【是否展示在介绍上；0-否 1-是】\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'spu 基本属性值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_product_attr_value
-- ----------------------------
INSERT INTO `commodity_product_attr_value` VALUES (16, 13, 6, '材料', '普通塑料', NULL, 1);
INSERT INTO `commodity_product_attr_value` VALUES (17, 13, 7, '净重', '1kg', NULL, 1);
INSERT INTO `commodity_product_attr_value` VALUES (18, 13, 8, '亮度', '高亮', NULL, 1);
INSERT INTO `commodity_product_attr_value` VALUES (19, 14, 6, '材料', '普通塑料', NULL, 1);
INSERT INTO `commodity_product_attr_value` VALUES (20, 14, 7, '净重', '1kg', NULL, 1);
INSERT INTO `commodity_product_attr_value` VALUES (21, 14, 8, '亮度', '高亮', NULL, 1);

-- ----------------------------
-- Table structure for commodity_sku_images
-- ----------------------------
DROP TABLE IF EXISTS `commodity_sku_images`;
CREATE TABLE `commodity_sku_images`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT 'sku_id',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `img_sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `default_img` int(11) NULL DEFAULT NULL COMMENT '默认图[0 - 不是默认图，1 - 是默认图]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku 图片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_sku_images
-- ----------------------------
INSERT INTO `commodity_sku_images` VALUES (13, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/ae219513-f6c5-4454-8c60-9984a0403abf_springmvc.jpg', NULL, 1);
INSERT INTO `commodity_sku_images` VALUES (14, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/a8229323-07b0-497d-8a8d-e49e13c0a3d1_idea.jpg', NULL, 0);
INSERT INTO `commodity_sku_images` VALUES (15, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/31c800bd-285f-4424-8845-280ba0d2e0f0_java核心卷1.png', NULL, 1);
INSERT INTO `commodity_sku_images` VALUES (16, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/a8229323-07b0-497d-8a8d-e49e13c0a3d1_idea.jpg', NULL, 0);
INSERT INTO `commodity_sku_images` VALUES (17, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/ae219513-f6c5-4454-8c60-9984a0403abf_springmvc.jpg', NULL, 0);
INSERT INTO `commodity_sku_images` VALUES (18, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/647573cd-90ef-4f02-89e2-7c70d10b59ee_spring.jpg', NULL, 1);
INSERT INTO `commodity_sku_images` VALUES (19, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/31c800bd-285f-4424-8845-280ba0d2e0f0_java核心卷1.png', NULL, 0);
INSERT INTO `commodity_sku_images` VALUES (20, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/a8229323-07b0-497d-8a8d-e49e13c0a3d1_idea.jpg', NULL, 0);
INSERT INTO `commodity_sku_images` VALUES (21, 13, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/11d4a036-97dc-4d99-8be6-6ebe53eddc3a_springboot.jpg', NULL, 1);
INSERT INTO `commodity_sku_images` VALUES (22, 14, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/11d4a036-97dc-4d99-8be6-6ebe53eddc3a_springboot.jpg', NULL, 0);
INSERT INTO `commodity_sku_images` VALUES (23, 14, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/5b3c50a0-60a5-4e4e-84ab-5e7fa4aea466_write-spring.jpg', NULL, 1);
INSERT INTO `commodity_sku_images` VALUES (24, 15, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/11d4a036-97dc-4d99-8be6-6ebe53eddc3a_springboot.jpg', NULL, 0);
INSERT INTO `commodity_sku_images` VALUES (25, 16, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/11d4a036-97dc-4d99-8be6-6ebe53eddc3a_springboot.jpg', NULL, 0);

-- ----------------------------
-- Table structure for commodity_sku_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_sku_info`;
CREATE TABLE `commodity_sku_info`  (
  `sku_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'skuId',
  `spu_id` bigint(20) NULL DEFAULT NULL COMMENT 'spuId',
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku 名称',
  `sku_desc` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku 介绍描述',
  `catalog_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分类 id',
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌 id',
  `sku_default_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认图片',
  `sku_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `sku_subtitle` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `price` decimal(18, 4) NULL DEFAULT NULL COMMENT '价格',
  `sale_count` bigint(20) NULL DEFAULT NULL COMMENT '销量',
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku 信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_sku_info
-- ----------------------------
INSERT INTO `commodity_sku_info` VALUES (9, 13, '小米平板 300*300 红色', NULL, 301, 1, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/ae219513-f6c5-4454-8c60-9984a0403abf_springmvc.jpg', '小米平板 300*300 红色', '副标题1', 120.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (10, 13, '小米平板 300*300 绿色', NULL, 301, 1, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/31c800bd-285f-4424-8845-280ba0d2e0f0_java核心卷1.png', '小米平板 300*300 绿色', '副标题2', 230.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (11, 13, '小米平板 100*100 红色', NULL, 301, 1, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/647573cd-90ef-4f02-89e2-7c70d10b59ee_spring.jpg', '小米平板 100*100 红色', '副标题3', 340.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (12, 13, '小米平板 100*100 绿色', NULL, 301, 1, 'default2.png', '小米平板 100*100 绿色', '副标题4', 450.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (13, 14, 'ipad 100*100 红色', NULL, 301, 1, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/11d4a036-97dc-4d99-8be6-6ebe53eddc3a_springboot.jpg', 'ipad 100*100 红色', '安抚擦擦', 20.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (14, 14, 'ipad 100*100 绿色', NULL, 301, 1, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/5b3c50a0-60a5-4e4e-84ab-5e7fa4aea466_write-spring.jpg', 'ipad 100*100 绿色', '安抚擦擦', 1000.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (15, 14, 'ipad 300*300 红色', NULL, 301, 1, 'default2.png', 'ipad 300*300 红色', '安抚擦擦', 50.0000, 0);
INSERT INTO `commodity_sku_info` VALUES (16, 14, 'ipad 300*300 绿色', NULL, 301, 1, 'default2.png', 'ipad 300*300 绿色', '安抚擦擦', 30.0000, 0);

-- ----------------------------
-- Table structure for commodity_sku_sale_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `commodity_sku_sale_attr_value`;
CREATE TABLE `commodity_sku_sale_attr_value`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT 'sku_id',
  `attr_id` bigint(20) NULL DEFAULT NULL COMMENT 'attr_id',
  `attr_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '销售属性名',
  `attr_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '销售属性值',
  `attr_sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku 的销售属性/值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_sku_sale_attr_value
-- ----------------------------
INSERT INTO `commodity_sku_sale_attr_value` VALUES (5, 9, 9, '面积', '300*300', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (6, 9, 10, '颜色', '红色', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (7, 10, 9, '面积', '300*300', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (8, 10, 10, '颜色', '绿色', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (9, 11, 9, '面积', '100*100', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (10, 11, 10, '颜色', '红色', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (11, 12, 9, '面积', '100*100', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (12, 12, 10, '颜色', '绿色', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (13, 13, 9, '面积', '100*100', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (14, 13, 10, '颜色', '红色', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (15, 14, 9, '面积', '100*100', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (16, 14, 10, '颜色', '绿色', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (17, 15, 9, '面积', '300*300', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (18, 15, 10, '颜色', '红色', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (19, 16, 9, '面积', '300*300', NULL);
INSERT INTO `commodity_sku_sale_attr_value` VALUES (20, 16, 10, '颜色', '绿色', NULL);

-- ----------------------------
-- Table structure for commodity_spu_images
-- ----------------------------
DROP TABLE IF EXISTS `commodity_spu_images`;
CREATE TABLE `commodity_spu_images`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `spu_id` bigint(20) NULL DEFAULT NULL COMMENT 'spu_id',
  `img_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片名',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `img_sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `default_img` tinyint(4) NULL DEFAULT NULL COMMENT '是否默认图',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'spu 图片集' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_spu_images
-- ----------------------------
INSERT INTO `commodity_spu_images` VALUES (13, 13, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/a8229323-07b0-497d-8a8d-e49e13c0a3d1_idea.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (14, 13, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/ae219513-f6c5-4454-8c60-9984a0403abf_springmvc.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (15, 13, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/647573cd-90ef-4f02-89e2-7c70d10b59ee_spring.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (16, 13, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/59725202-244e-4fa2-81db-6a7b72f6c57c_springboot.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (17, 13, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/31c800bd-285f-4424-8845-280ba0d2e0f0_java核心卷1.png', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (18, 14, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/11d4a036-97dc-4d99-8be6-6ebe53eddc3a_springboot.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (19, 14, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/5b3c50a0-60a5-4e4e-84ab-5e7fa4aea466_write-spring.jpg', NULL, NULL);
INSERT INTO `commodity_spu_images` VALUES (20, 14, NULL, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/ef5b2dea-31e8-46bd-8e2b-55c43236ff86_python.png', NULL, NULL);

-- ----------------------------
-- Table structure for commodity_spu_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_spu_info`;
CREATE TABLE `commodity_spu_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品 id',
  `spu_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `spu_description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `catalog_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分类 id',
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌 id',
  `weight` decimal(18, 4) NULL DEFAULT NULL,
  `publish_status` tinyint(4) NULL DEFAULT NULL COMMENT '上架状态[0 - 下架，1 - 上架]',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品 spu 信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_spu_info
-- ----------------------------
INSERT INTO `commodity_spu_info` VALUES (13, '小米平板', '遥遥领先', 301, 1, 0.3000, 1, '2023-02-28 13:09:21', '2023-02-28 13:09:21');
INSERT INTO `commodity_spu_info` VALUES (14, 'ipad', '好用', 301, 1, 0.2000, 1, '2023-03-01 19:36:59', '2023-03-01 19:36:59');

-- ----------------------------
-- Table structure for commodity_spu_info_desc
-- ----------------------------
DROP TABLE IF EXISTS `commodity_spu_info_desc`;
CREATE TABLE `commodity_spu_info_desc`  (
  `spu_id` bigint(20) NOT NULL COMMENT '商品 id',
  `decript` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品介绍图片',
  PRIMARY KEY (`spu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品 spu 信息介绍' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_spu_info_desc
-- ----------------------------
INSERT INTO `commodity_spu_info_desc` VALUES (13, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-02-28/fa60d6e7-7586-422a-80c0-77a4e3dcb197_java.png');
INSERT INTO `commodity_spu_info_desc` VALUES (14, 'https://ttpfx-study-1.oss-cn-chengdu.aliyuncs.com/2023-03-01/8d02375f-097f-4afe-82c5-c7ab14382583_java.png');

SET FOREIGN_KEY_CHECKS = 1;
