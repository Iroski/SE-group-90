su root
rm -R out
mkdir out
cp -r lib out
javac -encoding UTF-8 -cp lib/guava-20.0.jar;lib/annotations-20.1.0.jar;lib/fastjson-1.2.75.jar;lib/javafx-base-15.0.1-win.jar;lib/javafx-base-15.0.1.jar;lib/javafx-controls-15.0.1.jar;lib/javafx-controls-15.0.1-win.jar;lib/javafx-fxml-15.0.1-win.jar;lib/javafx-fxml-15.0.1.jar;lib/javafx-graphics-15.0.1.jar;lib/javafx-graphics-15.0.1-win.jar;lib/javafx-media-15.0.1.jar;lib/javafx-media-15.0.1-win.jar;lib/javafx-swing-15.0.1.jar;lib/javafx-swing-15.0.1-win.jar;lib/javafx-web-15.0.1.jar;lib/javafx-web-15.0.1-win.jar;lib/lombok-1.18.10.jar;lib/reflections-0.9.11.jar;lib/guava-20.0.jar;lib/javassist-3.21.0-GA.jar -d out @documents/document/setup/alljava.txt
cp -r src/main/resources/view out
cp -r src/main/resources/video out
mkdir -p out/src/main/resources
cp -r src/main/resources/database out/src/main/resources
cd out
java -cp lib/guava-20.0.jar;lib/annotations-20.1.0.jar;lib/fastjson-1.2.75.jar;lib/javafx-base-15.0.1-win.jar;lib/javafx-base-15.0.1.jar;lib/javafx-controls-15.0.1.jar;lib/javafx-controls-15.0.1-win.jar;lib/javafx-fxml-15.0.1-win.jar;lib/javafx-fxml-15.0.1.jar;lib/javafx-graphics-15.0.1.jar;lib/javafx-graphics-15.0.1-win.jar;lib/javafx-media-15.0.1.jar;lib/javafx-media-15.0.1-win.jar;lib/javafx-swing-15.0.1.jar;lib/javafx-swing-15.0.1-win.jar;lib/javafx-web-15.0.1.jar;lib/javafx-web-15.0.1-win.jar;lib/lombok-1.18.10.jar;lib/javassist-3.21.0-GA.jar;lib/reflections-0.9.11.jar;.  main.Launcher
cd ..