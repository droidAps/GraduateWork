### Шаги выполнения для запуска тестов (база данных MySQL)
1. Открыть проект "GraduateWork" с помощью IntelliJ IDEA;
2. Открыть терминал и последовательно выполнить следующие команды:
- `docker-compose up` (дождаться развертывания всех сервисов)
- `java -jar artifacts/aqa-shop.jar --spring.profiles.active=mysql`
- `./gradlew test -DdatabaseURL=jdbc:mysql://localhost:3306/mydb -DdatabaseUser=user -DdatabasePassword=pass`

### Шаги выполнения для запуска тестов (база данных PostgreSQL)
1. Открыть проект "GraduateWork" с помощью IntelliJ IDEA;
2. Открыть терминал и последовательно выполнить следующие команды:
- `docker-compose up` (дождаться развертывания всех сервисов)
- `java -jar artifacts/aqa-shop.jar --spring.profiles.active=postgresql`
- `./gradlew test -DdatabaseURL=jdbc:postgresql://localhost:5432/postgredb -DdatabaseUser=user -DdatabasePassword=pass`
