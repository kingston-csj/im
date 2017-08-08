::::deploy script
echo off 
echo.
echo begin deploy...
call mvn clean package -DskipTests

echo export jar finished, begin to run....
cd .\target\
java -jar .\Chatroom_Server.jar
