### Шаги выполнения для запуска тестов (база данных MySQL)
1. Открыть проект "GraduateWork" с помощью IntelliJ IDEA;
2. Открыть терминал и последовательно выполнить следующие команды:
- `docker-compose up` (дождаться развертывания всех сервисов)
- `java -jar aqa-shop.jar --spring.credit-gate.url=http://localhost:9999/credit --spring.payment-gate.url=http://localhost:9999/payment --spring.datasource.url=jdbc:mysql://localhost:3306/mydb --spring.datasource.username=user --spring.datasource.password=pass`
- `./gradlew test -DdatabaseURL=jdbc:mysql://localhost:3306/mydb -DdatabaseUser=user -DdatabasePassword=pass`

### Шаги выполнения для запуска тестов (база данных PostgreSQL)
1. Открыть проект "GraduateWork" с помощью IntelliJ IDEA;
2. Открыть терминал и последовательно выполнить следующие команды:
- `docker-compose up` (дождаться развертывания всех сервисов)
- `java -jar aqa-shop.jar --spring.credit-gate.url=http://localhost:9999/credit --spring.payment-gate.url=http://localhost:9999/payment --spring.datasource.url=jdbc:postgresql://localhost:5432/postgredb --spring.datasource.username=user --spring.datasource.password=pass`
- `./gradlew test -DdatabaseURL=jdbc:postgresql://localhost:5432/postgredb -DdatabaseUser=user -DdatabasePassword=pass`
