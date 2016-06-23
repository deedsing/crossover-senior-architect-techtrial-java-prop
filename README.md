README
This programs loads properties from different resource path and stores them into a static map.
@Author deedsing
----------------------
Design decisions/Assumptions
----------------------
1. All the properties will be renamed to use '_' as separators instead of '.' for simplicity , but user can combine any combinations of '.' and '_' and the keys will be case insensitive.
2.Supported URI and supported file formats are stored as a java enum. (com.crossover.trial.properties.model.AcceptedFileFormats, com.crossover.trial.properties.model.AcceptedURI)
3. Mandatory properties will be stored as a java enum (com.crossover.trial.properties.model.RequiredProperties). If any of the required property is unset the configuration is invalid.
4. The name of the property and the type of the property is also stored as a java enum (com.crossover.trial.properties.model.PropertyTypeMap).
	 The drawback for point 2,3 and 4 is that user need to change the code if they want to modify uris, accepted file formats and property type map.But I have made an assumption that it is a one time task so we can keep it as a java enum for faster lookups ; instead of opening up socket connections.But as per the use it could be modified later.
5. Java Factory pattern has been used and a Loader interface is defined to load different URI's.As the supported URI increases the code can be modified to add more implementation specific classes.

----------------------
Command Line Arguments
----------------------
All command line arguments will be the URI's path.
Main class : com.crossover.trial.properties.Main
example:  classpath:resources/aws.properties http://localhost:8090/resttest/rest/json/get/config.json file://C:/deed/crossover/files/jdbc.properties
{
args[0] --> classpath:resources/aws.properties 
args[1] --> http://localhost:8090/resttest/rest/json/get/config.json 
args[3] --> file://C:/deed/crossover/files/jdbc.properties
}

----------------------

I am not sure which output is fed to autograder, so I have provided both ways of generating a output file , one with maven logs and other with only the desired 
output.

----------------------
Maven exec command
----------------------
Generating a file with all the maven logs ...
mvn exec:java -Dexec.args="arg1 arg2 arg3" > output.txt 
example:
mvn exec:java  -Dexec.args="classpath:resources/jdbc.properties file://path-to-the-file.properties http://url-path.json" > output.txt


---------------------------
Running java executable jar
---------------------------
Generating file with only loaded properties contents.
java -jar dist-1.0.0-jar-with-dependencies.jar arg1 arg2 arg3 ... > output.txt
Ex: java -jar dist-1.0.0-jar-with-dependencies.jar file://C:/deed/crossover/files/config.json  file://C:/deed/crossover/files/aws.properties file://C:/deed/crossover/files/jdbc.properties > output.txt

Whenever this jar is run it creates a file named MainOutput.txt , which is overiden for each run.
Windows --> The jar will be located at the target folder after running maven build.

Linux  --> Maven may copy this runnable jar to /home/{user}/.m2/repository/com/crossover/trial/properties/dist/1.0.0/dist-1.0.0-jar-with-dependencies.jar 
linux commands to make the jar run :
chmod+x /home/deed/.m2/repository/com/crossover/trial/properties/dist/1.0.0/dist-1.0.0-jar-with-dependencies.jar
java -jar /home/deed/.m2/repository/com/crossover/trial/properties/dist/1.0.0/dist-1.0.0-jar-with-dependencies.jar ile://C:/deed/crossover/files/config.json  file://C:/deed/crossover/files/aws.properties file://C:/deed/crossover/files/jdbc.properties > output.txt


----------------------
Supported URI's
----------------------
1.http://
2.file://
3.classpath:resources/ (Custom)

----------------------
Supported files suffix
----------------------
1. '.properties'
2. '.json '


Output
----------------------
Output is directed towards console and ordered alphabetically with respect to properties name.
Example:

aws_access_key, java.lang.String, AKIAJSF6XRIJNJTTTL3Q
aws_account_id, java.lang.Integer, 12345678
aws_region_id, com.amazonaws.regions.Regions, US_EAST_1
aws_secret_key, java.lang.String, pmqnweEYvdiw7cvCdTOES48sOUvK1rGvvctBsgsa
hibernate_generate_statistics, java.lang.Boolean, true
hibernate_show_sql, java.lang.Boolean, true
jdbc_driver, java.lang.String, com.mysql.jdbc.Driver


----------------------
Logging
----------------------
Logs path --> /tmp/deed/property-loader/logs.logs (Assuming the system to be linux and directory writeable.)
Both INFO and DEBUG are supported currently with system defaulted to DEBUG.



----------------------
Exceptions
----------------------
1. UnsupportedFileFormatException
2. UnsupportedURIException


----------------------
Feedback on Assignment
----------------------
As per the assignment the program is supposed to take only URI's as command line arguments , but the sample main which is provided in the assignment takes the first argument as the output file path and remaining as the URI's path , which is very confusing . I have taken the latter as my assumption.

Also the main class path in the sample command, which is shown for the auto grader is different which adds more to the confusion.
I would suggest that the code and the assignment description should be consistent in order for the test takers to not make any false assumptions.

Hopefully my assumption are fine.  :-)

Note : the output file in the root level directory is created by the test class , so it should not be 
 
