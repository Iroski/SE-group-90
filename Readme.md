# Readme
## Important : 
- If your jdk version is 16, you can only run the program with method 1 or method 3 as jdk 16 add JEP 396 which will let lombok in our project inoperable.
- If you open this project with a ide such as idea and find that pom.xml has red wavy lines, this is ok. This is because we have adapted jdk16 and must add part of the configuration information to the pom, but this part of the configuration is regarded as an error due to the ide. The project can be run correctly and you will not find errors
## Start program
### 1. Run with maven 3 (__recommend__)
- Ensure you have a maven with version 3.x.x . You can check it by typing "mvn -version" to command line.
If you do not have maven, you can try to install with:https://www.baeldung.com/install-maven-on-windows-linux-mac
- Open a terminal and use cd command to switch in the project folder.
- type command "mvn clean javafx:run" then press enter to start the program. (hint: it may take few seconds to start the program as it will download jars from maven repository)
### 2. Run with javac (__Windows only__, no need for network to download jars)
- Open a terminal and switch in the project folder.
- type command "startup.bat" then press enter to start the program.

if you want to use or test the file storage, when you close the program and the terminal ask you if you want to copy the files, you need to type "A" to the command line 
### 3. Run with idea
- Open the idea from the terminal by type "idea" and press enter.
- Click File-Open and choose the project folder.
- Click Run-Run 'Launcher' to start the program (hint: if you can't find the Run 'Launcher', please click the "project" button and find Launcher at "se/src/main/java/main". open the Launcher class and click the green triangle to start the program)

You can use the account with username "1" and password "1" to login in. You can also sign up a new account.
