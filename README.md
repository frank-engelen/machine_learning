Taking a look at Java-based Machine Learning by Classification
==============================================================

This Git-Repository comes along with my blog post "Taking a look at Java-based Machine Learning by Classification".


Installing Rapid-Miner 
----------------------

1. Download RapidMiner-Installer from rapid-i.com (I've tested with Version 5.3.005 on Windows 7 with 64bit JDK 1.7)

2. Install and start RapidMiner

3. Add some extension. Click "Help" from the main menu and "Updates and Extensions (Marketplace)...". Add at least:
	* Text Mining Extension (tested with 5.3.0)
	* Weka Extension (tested with 5.3.1)
	* Parallel Processing Extension (tested with 5.3.0)

4. After the installation of the Extension RapidMiner will be restarted.

5. After Restart press F8 to switch to the Design Perspective.

6. Import the Rapid-Miner Repo from this files

	

Using the Java Example
----------------------

1. Start Eclipse with a new Workspace(I used Eclipse Java EE IDE for Web Developers, Juno Service Release 1)

2. Define two Classpath Variables:
	* RAPIDMINER_LIB:         <RapidMinerInstallationDir>/lib, e.g. C:\progs\RapidMiner5\lib
	* RAPIDMINER_LIB_MANAGED: <UserDir>/.RapidMiner5/managed,  e.g. C:\Users\frank.engelen\.RapidMiner5\managed

3. Import "RapidMinerFromJava" via File | Import | "Existing Projects into Workspace"

4. You will find a launch configuration for the test program in the subdirectory "launch" of the project. Use it to run RapidMiner classification



Java Programs
-------------


In the "src"-subdirectory you will find two Java-Classes:

### MainClassifier.java                 
Call to RapidMiner to let RapidMiner classify all files in \machine_learning\data\03-20_newsgroup_java_in.

Initially you will find the following examples in the "in"-directory	
Path: C:\machine_learning\data\03-20_newsgroup_java_in\103124:    Real Label:rec.motorcycles
Path: C:\machine_learning\data\03-20_newsgroup_java_in\53300:     Real Label:talk.politics.guns
Path: C:\machine_learning\data\03-20_newsgroup_java_in\59872:     Real Label:sci.space
Path: C:\machine_learning\data\03-20_newsgroup_java_in\9140:      Real Label:comp.os.ms-windows.misc



### ClearMetadataFromFiles
Removes some headers from the original dataset postings. Usenet postings contains some headers and especially the header "Newsgroups:", which includes the newsgroup name. This would lead to a "target leak", so all headers but "Subject", "Lines", "From", "Keywords" and "Summary" are removed.

