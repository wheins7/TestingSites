run: CS400Graph.class Location.class Main.class test build
	@echo "Testing CS400Graph & GraphTest"

CS400Graph.class: CS400Graph.java GraphADT.java
	javac CS400Graph.java

Location.class: Location.java
	javac Location.java

Main.class: Main.java Location.class CS400Graph.class
	javac Main.java

build: 
	java Main
	
JUnit5:
	wget http://pages.cs.wisc.edu/~cs400/junit5.jar


GraphTest.class: JUnit5 junit5.jar CS400Graph.class
	javac -cp .:junit5.jar GraphTest.java


test: Main.class CS400Graph.class GraphTest.class
	java -jar junit5.jar --class-path . --scan-classpath --details tree


clean:
	$(RM) *.class
