FROM openjdk:16
WORKDIR /app/
COPY src ./src/
RUN javac -cp src -d out src/Lexer/*.java
RUN javac -cp src -d out src/*.java

