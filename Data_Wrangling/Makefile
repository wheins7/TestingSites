run: compile Data3.csv
	java Main

compile: Location.java GraphADT.java CS400Graph.java Main.java GraphTest.java
	javac Location.java
	javac GraphADT.java
	javac CS400Graph.java
	javac Main.java
	javac -cp .:junit5.jar GraphTest.java

test: compile
	java -jar junit5.jar -class-path . -- scan-classpath --details tree
	
clean:
	$(RM) *.class
