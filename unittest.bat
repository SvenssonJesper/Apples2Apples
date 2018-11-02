@echo off
::set JDK_HOME=c:\PROGRA~1\Java\jdk1.8.0_144
set REPORT=lib\org.jacoco.examples-0.7.7.jar
set AGENT=lib\org.jacoco.agent-0.7.7.jar
set JUNIT=lib\org.junit4-4.3.1.jar
@echo "Running unittests ..."
java -javaagent:%AGENT% -cp %JUNIT%;bin org.junit.runner.JUnitCore tests.UnitTest
@echo "Generating report ..."
java -jar %REPORT% .