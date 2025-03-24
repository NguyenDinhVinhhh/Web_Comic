# Sử dụng hình ảnh OpenJDK 17
FROM openjdk:17-jdk

ARG FILE_JAR=target/*.jar

ADD ${FILE_JAR} api-service.jar
# Đặt thư mục làm việc trong containe

# Chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-jar", "api-service.jar"]

# Mở cổng 8080 để ứng dụng chạy
EXPOSE 8080
