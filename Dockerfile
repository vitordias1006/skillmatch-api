# Use the Eclipse temurin alpine official image
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar os ficheiros necessários
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Garantir permissões
RUN chmod +x mvnw

# Só agora copiar o resto do código
COPY src src

# Build
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

CMD ["sh", "-c", "java -jar target/quarkus-app/quarkus-run.jar"]
