# cs400-p2

This repository is contains the second group project of CS400-S21 created by the CF team.
In essence, it is a quizlet clone.

# Table of Contents

1. [BackEnd Developer](#packageone)
	1. [Files Written](#f1)
	2. [Instructions To Work On The Project](#i1)
	3. [Additional Contributions](#ac1)
	4. [Signature](#s1)
2. [FrontEnd Developer](#packagetwo)
	1. [Files Written](#f2)
	2. [Instructions To Work On The Project](#i2)
	3. [Additional Contributions](#ac2)
	4. [Signature](#s2)
3. [Data Wrangler](#packagethree)
	1. [Files Written](#f3)
	2. [Instructions To Work On The Project](#i3)
	3. [Additional Contributions](#ac3)
	4. [Signature](#s3)
4. [Integration Manager](#sql-games)
	1. [Complete List Of Files](#file-list)
	3. [Instructions To Run The Project](#run)
	4. [Team Member Contributions](#tmc)
	5. [Signature](#s4)
5. [Demo](#demo)

</br></br></br></br>
## BackEnd Developer<a name="packageone" />

========================================================

Name of BackEndDeveloper: Zhiyuan Han</br>
@wisc.edu Email of BackEndDeveloper: zhan98@wisc.edu</br>
Group: CF</br>
Team: Adilnur Istekov, David Khachatryan, Abhimanyu Gupta</br>

Files Written by Me: <a name="f1" />
--------------------


Instructions To Work: <a name="i1" />
--------------------
If you don't have git bash (or any other bash), start by downloading from [here](https://git-scm.com/downloads)  </br>
Open git bash, or your system's local bash and navigate to a folder where you want to store your project files. </br>

Once you find yourself in your working folder, execute the following command.

```
git clone -b data https://github.com/KhachDavid/cs400-p2.git
```

This fill add every file from the data branch. </br>
You are going to need the following files: </br>
	[Directory Data](https://github.com/KhachDavid/cs400-p2/tree/main/data) </br>
	[DataDummy.java](https://github.com/KhachDavid/cs400-p2/blob/main/DataDummy.java) </br>
	[DataWranglerTests.java](https://github.com/KhachDavid/cs400-p2/blob/main/DataWranglerTests.java) </br>
	[QuestionDataReaderInterface.java](https://github.com/KhachDavid/cs400-p2/blob/main/QuestionDataReaderInterface.java) </br>

Start by making your first commit to be check whether this works. </br> 
Take your implemented tests and add them to the DataWranglerTests.java using the text editor of your choice. </br>

Once you are done with that, execute the following commands from your bash.

```
git init
```

```	
git add . 
```

```
git commit -m "Added Test Files For Data Branch"
```

```
git remote add origin https://github.com/KhachDavid/cs400-p2.git
```

```
git push origin data
``` 

Additional Contributions: <a name="ac1" />
-------------------------


Signature: <a name="s1" />
----------
Signature: Zhiyuan Han

</br></br></br></br>
## FrontEnd Developer<a name="packagetwo" />

========================================================

Name of FrontEndDeveloper: David Khachatryan</br>
@wisc.edu Email of FrontEndDeveloper: dkhachatryan@wisc.edu</br>
Group: CF</br>
Team: red</br>

Files Written by Me: <a name="f2" />
--------------------


Additional Contributions: <a name="ac2" />
-------------------------


Signature: <a name="s2" />
----------
Signature: David Khachatryan
</br></br></br></br>

## Data Wrangler<a name="packagethree" />

========================================================

Name of DataWrangler: Abhimanyu Gupta</br>
@wisc.edu Email of DataWrangler: adgupta2@wisc.edu</br>
Group: CF</br>
Team: red</br>

Files Written by Me: <a name="f3" />
--------------------


Instructions To Work: <a name="i3" />
--------------------


Additional Contributions: <a name="ac3" />
-------------------------


Signature: <a name="s3" />
----------
Signature: Abhimnayu Gupta
</br></br></br></br>
## Integration Manager<a name="sql-games" />

========================================================

Name of IntegrationManager: Adilnur Istekov</br>
@wisc.edu Email of IntegrationManager: istekov@wisc.edu</br>
Group: CF</br>
Team: Red Team</br>

Complete List of Files: <a name="file-list" />
-----------------------


Instructions to Build, Run and Test your Project:<a name="run" />
-------------------------------------------------
Since this project uses GUI Swing and JFrame libraries, it is not possible to open the GUI with a make run command. 
You would need to open the code outside the command line to use the frontend.

To clone the repository
```
$ git clone https://github.com/KhachDavid/cs400-p2.git
```

To run the tests, you can run 
```
make test.
```
To run a specific test run:
```
make testFrontend
```

```
make testBackend
```
	
```
make testData
```

To clear the .class files, run 
```
make clean. 
```

Team Member Contributions: <a name="tmc" />
--------------------------


Signature: Adilnur Istekov <a name="s4" />
----------

## Demo<a name="demo"/>

[Link]⋮ </br>
[Part 1]: # </br>
[Part 2]: # </br>
[Part 3]: # </br>

Courtesy of David Khachatryan
