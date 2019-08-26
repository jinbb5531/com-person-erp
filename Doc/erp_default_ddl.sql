/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 25/08/2019 13:28:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for erp_content
-- ----------------------------
DROP TABLE IF EXISTS `erp_content`;
CREATE TABLE `erp_content`  (
  `ESSAY_ID` bigint(20) NOT NULL COMMENT '文章外键',
  `MAIN_CONTENT` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文本内容',
  PRIMARY KEY (`ESSAY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表的从表，存储大字段文章内容';

-- ----------------------------
-- Table structure for erp_dict
-- ----------------------------
DROP TABLE IF EXISTS `erp_dict`;
CREATE TABLE `erp_dict`  (
  `ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编号',
  `DICT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '意义',
  `STATUS` int(11) NULL DEFAULT NULL COMMENT '1为启用，2为禁用',
  `ICON_PATH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标路径',
  `CREATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `UPDATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `TYPE_ID` bigint(20) NULL DEFAULT NULL COMMENT '类型外键',
  `SYSTEM_TAG` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用于记录系统中所有字典标识的信息；\r\n字典类型 -> 字典表  一对多';

-- ----------------------------
-- Table structure for erp_essay
-- ----------------------------
DROP TABLE IF EXISTS `erp_essay`;
CREATE TABLE `erp_essay`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章编号',
  `ESSAY_TYPE` int(11) NULL DEFAULT NULL COMMENT '1：普通文章；2：视频文章',
  `MAIN_TITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主标题',
  `SECONDARY_TITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `SUMMARY` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `IMG_PATH` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题略缩图路径',
  `ESSAY_SOURCE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章来源',
  `AUTHOR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `PUBLISH_DATE` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `DOWN_DATE` datetime(0) NULL DEFAULT NULL COMMENT '下架时间',
  `STATUS` int(11) NULL DEFAULT NULL COMMENT '0：草稿；1：上架；2：上架；',
  `AUTO_FLAG` int(11) NULL DEFAULT NULL COMMENT '自动定时',
  `QUEUE_NUM` int(11) NULL DEFAULT NULL COMMENT '1：置顶',
  `CREATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `REMARK` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用于存储文章的主体信息数据\r\n文章表 --> 文章内容表    一对一\r\n\r\n\r\n                              -&#&';

-- ----------------------------
-- Table structure for erp_inventory
-- ----------------------------
DROP TABLE IF EXISTS `erp_inventory`;
CREATE TABLE `erp_inventory`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `STANDARD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格',
  `COLOUR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '花色',
  `WEIGHT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重量',
  `AMOUNT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `IMA_PATH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '码单',
  `REACH_AT` datetime(0) NULL DEFAULT NULL COMMENT '到货时间',
  `DELIVERY_AT` datetime(0) NULL DEFAULT NULL COMMENT '交货时间',
  `STATUS` int(11) NULL DEFAULT NULL COMMENT '状态',
  `CREATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `UPDATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `SYSTEM_TAG` bigint(20) NULL DEFAULT NULL COMMENT '系统标识',
  `DELIVERY_STATUS` int(11) NULL DEFAULT NULL,
  `STOCK_PRICE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存表';

-- ----------------------------
-- Table structure for erp_log
-- ----------------------------
DROP TABLE IF EXISTS `erp_log`;
CREATE TABLE `erp_log`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `USER_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户帐号',
  `OPERATE_MODULE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作模块',
  `OPERATE_REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作描述',
  `OPERATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `SYSTEM_TAG` bigint(20) NULL DEFAULT NULL COMMENT '系统标识',
  `USER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '记录每个用户操作的日志信息表';

-- ----------------------------
-- Table structure for erp_menu
-- ----------------------------
DROP TABLE IF EXISTS `erp_menu`;
CREATE TABLE `erp_menu`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单主键',
  `PARENT_ID` bigint(20) NULL DEFAULT NULL COMMENT '父菜单主键',
  `MODULE_FLAG` int(11) NULL DEFAULT NULL COMMENT '0:模块；1：操作',
  `MENU_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `MENU_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `ICON_PATH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `QUEUE_NUM` int(11) NULL DEFAULT NULL COMMENT '排序',
  `USE_FLAG` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `OUTSIDE_FLAG` int(11) NULL DEFAULT NULL COMMENT '是否第三方',
  `WINDOW_MODEL` int(11) NULL DEFAULT NULL COMMENT '窗口模式',
  `REMARK` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `UPDATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `SHOW_FLAG` int(11) NULL DEFAULT NULL COMMENT '0：显示；1：不显示；（菜单的模块和操作不显示出去给非超级管理员）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表';

-- ----------------------------
-- Table structure for erp_order
-- ----------------------------
DROP TABLE IF EXISTS `erp_order`;
CREATE TABLE `erp_order`  (
  `ORDER_CODE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ORDER_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CUSTOMER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CREATE_AT` datetime(0) NULL DEFAULT NULL,
  `CREATE_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `UPDATE_AT` datetime(0) NULL DEFAULT NULL,
  `UPDATE_BY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ORDER_STATUS` int(255) NULL DEFAULT NULL,
  `CUTTER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `HEMMER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PACKER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEADLINE` datetime(0) NULL DEFAULT NULL,
  `SYSTEM_TAG` bigint(255) NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CUT_AT` datetime(0) NULL DEFAULT NULL,
  `HEM_AT` datetime(0) NULL DEFAULT NULL,
  `PACK_AT` datetime(0) NULL DEFAULT NULL,
  `IMAGE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NUMBER` int(11) NULL DEFAULT NULL,
  `COST` double(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`ORDER_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Table structure for erp_order_item
-- ----------------------------
DROP TABLE IF EXISTS `erp_order_item`;
CREATE TABLE `erp_order_item`  (
  `CODE` bigint(255) NOT NULL AUTO_INCREMENT,
  `ORDER_CODE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `COLOR` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SIZE_L` double NULL DEFAULT NULL,
  `SIZE_W` double NULL DEFAULT NULL,
  `PRO_SIZE_L` double NULL DEFAULT NULL,
  `PRO_SIZE_W` double NULL DEFAULT NULL,
  `NUMBER` int(11) NULL DEFAULT NULL,
  `PLANT_NUM` int(11) NULL DEFAULT NULL,
  `SYSTEM_TAG` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Table structure for erp_order_operate
-- ----------------------------
DROP TABLE IF EXISTS `erp_order_operate`;
CREATE TABLE `erp_order_operate`  (
  `CODE` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '操作主键',
  `ORDER_CODE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单主键',
  `OPERATOR` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `TYPE` int(11) NULL DEFAULT NULL COMMENT '操作类型',
  `CUT_NUM` int(11) NULL DEFAULT NULL COMMENT '实际数量',
  `WEIGHT` double NULL DEFAULT NULL COMMENT '裁剪重量',
  `OPERA_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `SYSTEM_TAG` bigint(20) NULL DEFAULT NULL COMMENT '系统标识',
  `COAGENT` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PEOPLE_COUNT` int(10) NULL DEFAULT NULL,
  `OPERA_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单操作表';

-- ----------------------------
-- Table structure for erp_provide_trade
-- ----------------------------
DROP TABLE IF EXISTS `erp_provide_trade`;
CREATE TABLE `erp_provide_trade`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `CONTACT_PHONE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `CONTACT_ADDRESS` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司地址',
  `MAIN_BUSINESS` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主营业务',
  `SYSTEM_TAG` bigint(20) NULL DEFAULT NULL COMMENT '系统标识',
  `CREATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `UPDATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `REMARK` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商';

-- ----------------------------
-- Table structure for erp_role
-- ----------------------------
DROP TABLE IF EXISTS `erp_role`;
CREATE TABLE `erp_role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色主键',
  `ROLE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `SHOW_FLAG` int(11) NULL DEFAULT NULL COMMENT '显示标识（超级管理员设置为不显示）； 0：显示；1：不显示',
  `CREATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `UPDATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `SYSTEM_TAG` bigint(20) NULL DEFAULT NULL COMMENT '标识为0的角色是不能被非超级管理员删除的',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表\r\n角色 -> 菜单 :  n -> n';

-- ----------------------------
-- Table structure for erp_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `erp_role_menu`;
CREATE TABLE `erp_role_menu`  (
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色外键',
  `MENU_ID` bigint(20) NOT NULL COMMENT '菜单外键',
  `CREATE_BY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ROLE_ID`, `MENU_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单中间表（权限表）';

-- ----------------------------
-- Table structure for erp_system
-- ----------------------------
DROP TABLE IF EXISTS `erp_system`;
CREATE TABLE `erp_system`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统名',
  `STATUS` int(11) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '子系统表';

-- ----------------------------
-- Table structure for erp_type
-- ----------------------------
DROP TABLE IF EXISTS `erp_type`;
CREATE TABLE `erp_type`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TYPE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型';

-- ----------------------------
-- Table structure for erp_user
-- ----------------------------
DROP TABLE IF EXISTS `erp_user`;
CREATE TABLE `erp_user`  (
  `USER_CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户帐号',
  `SYSTEM_TAG` bigint(20) NOT NULL COMMENT '系统标识',
  `USER_PWD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `USER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `STATUS` int(11) NULL DEFAULT NULL COMMENT '性质，0：临时工  1：全日制',
  `TEL_PHONE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `MOBILE_PHONE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `QQ_NUM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ号码',
  `USER_MAIL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `CREATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `UPDATE_AT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `BASE_SALARY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基本工资',
  `JOIN_AT` datetime(0) NULL DEFAULT NULL COMMENT '入职时间',
  `SEX` int(11) NULL DEFAULT NULL COMMENT '0:女   1：男；',
  `WORK_KIND` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工种',
  `USER_ADDR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UNIT_COST` double(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`USER_CODE`, `SYSTEM_TAG`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表 -> 角色表： n -> n';

-- ----------------------------
-- Table structure for erp_user_role
-- ----------------------------
DROP TABLE IF EXISTS `erp_user_role`;
CREATE TABLE `erp_user_role`  (
  `USER_CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户帐号外键',
  `SYSTEM_TAG` bigint(20) NOT NULL COMMENT '用户系统标识',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色外键',
  PRIMARY KEY (`USER_CODE`, `SYSTEM_TAG`, `ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色中间表';

SET FOREIGN_KEY_CHECKS = 1;
