# 说明

**这是我在大学写的第一个web项目，就是一个股票的推荐系统**

该项目使用了maven，如需在电脑上运行，需要修改里面的数据库配置文件，需要有stock库，包含以下2个表
```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
```
```sql
DROP TABLE IF EXISTS `user_stock`;
CREATE TABLE `user_stock`  (
  `uid` int(11) NULL DEFAULT NULL,
  `stock_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stock_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
```

我在maven中引入了tomcat的插件，运行就是靠这个插件
**项目启动之后访问http://localhost:8080/login.html**

项目的运行截图放在 运行截图目录下，下面为部分截图



![主界面](https://github.com/c-ttpfx/university/blob/main/%E5%A4%A7%E4%B8%80/java/%E8%82%A1%E7%A5%A8%E6%8E%A8%E8%8D%90%E7%B3%BB%E7%BB%9F/%E6%BC%94%E7%A4%BA%E5%9B%BE%E7%89%87/%E4%B8%BB%E9%A1%B5%E9%9D%A2.png)

![股票分析界面](https://github.com/c-ttpfx/university/blob/main/%E5%A4%A7%E4%B8%80/java/%E8%82%A1%E7%A5%A8%E6%8E%A8%E8%8D%90%E7%B3%BB%E7%BB%9F/%E6%BC%94%E7%A4%BA%E5%9B%BE%E7%89%87/%E8%82%A1%E7%A5%A8%E5%88%86%E6%9E%90%E9%A1%B5%E9%9D%A2.png)

![股票查询界面](https://github.com/c-ttpfx/university/blob/main/%E5%A4%A7%E4%B8%80/java/%E8%82%A1%E7%A5%A8%E6%8E%A8%E8%8D%90%E7%B3%BB%E7%BB%9F/%E6%BC%94%E7%A4%BA%E5%9B%BE%E7%89%87/%E8%82%A1%E7%A5%A8%E6%9F%A5%E8%AF%A2%E9%A1%B5%E9%9D%A2.png)

![收藏页面](https://github.com/c-ttpfx/university/blob/main/%E5%A4%A7%E4%B8%80/java/%E8%82%A1%E7%A5%A8%E6%8E%A8%E8%8D%90%E7%B3%BB%E7%BB%9F/%E6%BC%94%E7%A4%BA%E5%9B%BE%E7%89%87/%E6%94%B6%E8%97%8F%E9%A1%B5%E9%9D%A2.png)
