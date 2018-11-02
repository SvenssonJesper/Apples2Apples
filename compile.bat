@echo off
::set JDK_HOME=c:\PROGRA~1\Java\jdk1.8.0_144
@echo "Compiling code ..."
@mkdir bin
@javac -d bin -cp src src/ClientMain.java src/ServerMain.java
@echo "Compiling tests ..."
set JUNIT=lib\org.junit4-4.3.1.jar
@javac -d bin -cp %JUNIT%;src src/tests/UnitTest.java