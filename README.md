# Basis67 DBKits - 数据库脚手架工程

## 项目简介

Basis67 DBKits 是一个基于Java SpringBoot技术栈的快速开发脚手架，提供：

- 📦 多数据库支持（MySQL + MongoDB）
- 🛠️ 标准化RESTful API开发模板
- 📑 自动生成Swagger API文档
- 🔧 MyBatis持久层集成
- 🚀 快速搭建可伸缩的数据服务层

## 技术栈

| 类型         | 技术组件                          |
|--------------|-----------------------------------|
| 核心框架     | Spring Boot 3.4.3                  |
| 持久层       | MyBatis 3.0.4                     |
| 关系型数据库 | MySQL 8.0+ / MariaDB 10.0+        |
| NoSQL数据库  | MongoDB 8.0+                     |
| API文档      | Swagger UI 3.0                   |
| 构建工具     | Maven 3.8+                       |
| 测试框架     | JUnit 5 + Mockito                |

## 项目结构

```text
src/
├── main/                   # 主代码目录
│   ├── java/               # Java源代码根目录
│   │   └── com/basis67/dbkits/ # 项目包路径
│   │       ├── controllers/    # API控制器层（处理HTTP请求）
│   │       ├── dto/            # 数据传输对象（接口数据传输载体）
│   │       ├── exception/      # 自定义异常处理（统一异常管理）
│   │       ├── mapper/         # MyBatis映射接口（数据库操作声明）
│   │       ├── model/          # 数据模型层
│   │       │   ├── mongo/      # MongoDB文档模型（NoSQL数据实体）
│   │       │   └── mysql/      # MySQL实体模型（关系型数据库实体）
│   │       ├── repository/     # 数据访问仓库接口（数据库操作抽象）
│   │       └── service/        # 业务逻辑层（核心服务实现）
│   └── resources/              # 资源文件目录
│       ├── application.yml     # 主配置文件（Spring Boot配置）
│       └── generatorConfig.xml # MyBatis Generator配置文件（数据库CRUD代码生成配置）
└── test/                       # 测试代码目录
    └── java/                   # 测试Java代码
        └── com/basis67/dbkits/ # 测试类包路径（对应主代码的单元测试）
```

## 快速启动

### 1. 克隆仓库

```bash
git clone https://github.com/basis67/dbkits-basis67.git
cd dbkits-basis67
```

### 2. 配置环境

- 修改 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/dbkits?useSSL=false
    username: root
    password: your_password
  data:
    mongodb:
      uri: mongodb://localhost:27017/dbkits
```

### 3. 启动服务

```bash
mvn spring-boot:run
```

### 4. 访问API文档

浏览器打开：<http://localhost:8080/swagger-ui/>

## 使用示例

### 创建MySQL记录

**请求：**

```bash
curl -v -X POST "http://localhost:8080/api/v1/users" \
-H "Content-Type: application/json" \
-d '{"username":"zhangsan","firstName":"Zhang","lastName":"San","email":"zhangsan@example.com"}'
```

**响应：**

```text
HTTP/1.1 201 Created
```

### 查询MongoDB文档

**请求：**

```bash
curl "http://localhost:8080/api/v1/logs/action?action=login&start=2025-03-19T08:00:00Z&end=2025-03-19T10:00:00Z"
```

**响应：**

```json
{
  "code": 200,
  "message": "Success",
  "data": [
      {
        "id": "6417b2d1a1b2c3d4e5f6a7b8",
        "action": "login",
        "userId": "1",
        "details": "user logged in.",
        "timestamp": "2025-03-19T09:55:00Z"
      }
  ]
}

```

## 功能特性

1. **双数据库支持**：
   - MySQL关系型数据存储（使用MyBatis）
   - MongoDB文档存储（使用Spring Data）

2. **标准化API设计**：
   - 统一响应格式（HTTP标准响应码 + code/message/data）
   - 分页查询支持（page/size参数）
   - RESTful风格接口设计

3. **开发效率提升**：
   - Swagger UI实时API文档
   - 通用DTO对象转换
   - 异常统一处理机制

4. **扩展性设计**：
   - 模块化代码结构
   - 仓库模式数据访问层
   - 配置化数据库连接

## 测试覆盖

- 单元测试：JUnit 5 + Mockito
- 集成测试：Testcontainers（可选）
- API测试：Swagger Contract

## 贡献指南

1. Fork仓库并创建新分支

2. 提交代码前运行：

    ```bash
    mvn clean test
    ```

3. 提交PR时请遵循[Conventional Commits规范](https://www.conventionalcommits.org/)
