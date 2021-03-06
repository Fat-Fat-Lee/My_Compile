FROM openjdk:16
WORKDIR /app/
COPY src ./src/
RUN javac -cp src -d out src/Comment/*.java
RUN javac -cp src -d out src/Lexer/*.java
RUN javac -cp src -d out src/Exp/*.java
RUN javac -cp src -d out src/Analysis/*.java
RUN javac -cp src -d out src/Parser/*.java
RUN javac -cp src -d out src/Var/*.java
RUN javac -cp src -d out src/Block/*.java
RUN javac -cp src -d out src/*.java


