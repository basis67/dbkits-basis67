# 使用 Eclipse Temurin 的 JDK 17 作为基础镜像
FROM eclipse-temurin:17-ubi9-minimal

# 参数化JAR文件名称，构建时可通过--build-arg覆盖
ARG JAR_FILE=app.jar

# 安装必要的用户管理工具
RUN microdnf install -y shadow-utils \
    && microdnf clean all

# 创建专用用户和用户组（避免使用root）
RUN groupadd -r appgroup \
    && useradd -r -g appgroup -d /app -s /sbin/nologin appuser

# 设置工作目录并复制应用
WORKDIR /app
COPY ${JAR_FILE} app.jar

# 设置权限（UBI镜像默认使用root用户，需要显式赋权）
RUN chown -R appuser:appgroup /app

# 切换到非特权用户
USER appuser

# 暴露 Spring Boot 的默认端口（按需修改）
EXPOSE 8080

# 启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]