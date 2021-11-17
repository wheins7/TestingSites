run: compile
	@echo "Testing CS400Graph & GraphTest"

compile:
	@echo "Compiling the code..."
	@javac CS400Graph.java
	@javac GraphADT.java
	@javac -cp .:junit5.jar  GraphTest.java

test:
	@echo "Testing... 1, 2, 3..."
	java -jar junit5.jar --class-path . --scan-classpath --details tree


clean:
	$(RM) *.class
