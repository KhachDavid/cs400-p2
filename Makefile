<<<<<<< HEAD

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



=======
MAIN = # fill in the main class

all: compile
	java $(MAIN)

compile: *.java
	javac *.java

clean:
	$(RM) *.class
>>>>>>> 236c4aba7299e61934bdd89caf4d0f44c2730221
