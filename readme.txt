1、在数据库创建一个新的数据库，取一个名字，执行 sql.sql 文件，创建表以及数据
2、修改  library\src\main\resources\application.properties 里的数据库信息
3、启动项目，在浏览器输入   localhost:8080
   管理员账号   admin/123456  管理员登录用的是数据库  admin表，字段为 name
   学生账号     001/123456   学生登录用的是数据库   student表，字段为 stu_num