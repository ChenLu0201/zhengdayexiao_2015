# ci-sample-project
This is a sample project for ci build.

### 数据库准备
- 安装mysql数据库
- 使用root登陆数据库`mysql -u root -p`
- 执行以下脚本准备数据库
```
CREATE DATABASE ci_sample;
CREATE USER 'ci_sample'@'localhost' IDENTIFIED BY 'ci_sample';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER,INDEX ON ci_sample.* TO 'ci_sample'@'localhost';
```

### build工程
- 在工程根路径下执行`gradle flywayMigrate`准备数据库环境
- 执行`gradle build`构建工程
- 启动应用执行'gradle bootRun',homepage访问http://localhost:8080

### 初始化karma框架
- 在工程根路径下执行`npm install`
- 执行`node_modules/karma/bin/karma start`运行JS测试
