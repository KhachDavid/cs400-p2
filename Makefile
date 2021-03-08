<<<<<<< HEAD

=======
>>>>>>> 5f261b6567030315f9b734520f85e438f719b822
test: DataWranglerTests.class BackEndDeveloperTests.class FrontEndDeveloperTests.class
	java -jar junit5.jar -cp . --scan-classpath

DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java

BackEndDeveloperTests.class: BackEndDeveloperTests.java
	javac -cp .:junit5.jar BackEndDeveloperTests.java

FrontEndDeveloperTests.class: FrontEndDeveloperTests.java
	javac -cp .:junit5.jar FrontEndDeveloperTests.java

clean: 
	$(RM) *.class
	$(RM) *~

<<<<<<< HEAD
=======


>>>>>>> 5f261b6567030315f9b734520f85e438f719b822
MAIN = # fill in the main class

all: compile
	java $(MAIN)

compile: *.java
	javac *.java

clean:
	$(RM) *.class
