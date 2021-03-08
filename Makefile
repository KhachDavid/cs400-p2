
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



