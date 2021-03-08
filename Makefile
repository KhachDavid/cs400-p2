MAIN = # fill in the main class

all: compile
	java $(MAIN)

compile: *.java
	javac *.java

clean:
	$(RM) *.class
