Hoover Problem statement:

Introduction

You will write a service that navigates a imaginary robotic hoover (much like a Roomba) through an equally imaginary room based on:
room dimensions as X and Y coordinates, identifying the top right corner of the room rectangle. This room is divided up in a grid based on these dimensions; a room that has dimensions X: 5 and Y: 5 has 5 columns and 5 rows, so 25 possible hoover positions. The bottom left corner is the point of origin for our coordinate system, so as the room contains all coordinates its bottom left corner is defined by X: 0 and Y: 0.
locations of patches of dirt, also defined by X and Y coordinates identifying the bottom left corner of those grid positions.
an initial hoover position (X and Y coordinates like patches of dirt)
driving instructions (as cardinal directions where e.g. N and E mean "go north" and "go east" respectively)
The room will be rectangular, has no obstacles (except the room walls), no doors and all locations in the room will be clean (hoovering has no effect) except for the locations of the patches of dirt presented in the program input.

Placing the hoover on a patch of dirt ("hoovering") removes the patch of dirt so that patch is then clean for the remainder of the program run. The hoover is always on - there is no need to enable it.

Driving into a wall has no effect (the robot skids in place).

Goal
The goal of the service is to take the room dimensions, the locations of the dirt patches, the hoover location and the driving instructions as input and to then output the following:

The final hoover position (X, Y)
The number of patches of dirt the robot cleaned up
The service must persist every input and output to a database.

Input
Program input will be received in a json payload with the format described here.

Example:

{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
Output
Service output should be returned as a json payload.

Example (matching the input above):

{
  "coords" : [1, 3],
  "patches" : 1
}
Where coords are the final coordinates of the hoover and patches is the number of cleaned patches.
# HooverAssignment
This is the solution of robo hoover for cleaning and moving on wall or floor
Steps Required to run the Application::

Installing Tools

For running this application you need to install required development environmet. Here is given list you require for running this application.

SOFTWARE REQUIRED
	Java 8+
	Maven
    MongoDB
	Eclipse or STS (optional)

MongoDB DATABASE CONFIGURATION
	URL: localhost
	Port: 20017
	Database Name: hoover	
	
HOW TO RUN TEST CASES
	Using maven
	run mvn clean test
Using Eclipse IDE
	select project -> run as -> maven test
	COMPILATION INSTRUCTIONS
Using Command Line Interface
	run mvn clean package
Using Eclipse IDE
	select project -> run as -> maven build
Note: project compilation will generate hoover-yoti-0.0.1-SNAPSHOT.jar file under target directory.

DEPLOYMENT INSTRUCTIONS
Using Command Line Interface
	go to target directory
	run java -jar robotic_hoover-0.0.1-SNAPSHOT.jar
Using Eclipse IDE
	select project -> run as -> Spring Boot App	

HOW TO ACCESS API:  
	I have configured swagger for api documentation. So we can get this by http://localhost:8080/swagger-ui.html
	Please open UML diagram on https://www.draw.io 
	
Running Examples Steps:
  1. Download the zip or clone the Git repository.
  
  2. Unzip the zip file (if you downloaded one)
  
  3. Open Command Prompt and Change directory (cd) to folder containing pom.xml
  
  4. Open Eclipse/STS
  
  5. File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
  
  6. Select the right project
  
  7. Please update the application.yml file as per your mongodb credentials.
  
  7. Choose the Spring Boot Application file (search for @SpringBootApplication)
  
  8. Right Click on the file and Run as Java Application
  
   You are all Set
  
  I have written test cases also. So you can test all test cases. From the name of test case you will get to know the perpose of test case.