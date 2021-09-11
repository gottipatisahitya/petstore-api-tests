# Pet API Automation Framework

## Introduction
This project is a demonstration of API test automation for Pet Swagger API using RestAssured framework
Java is chosen as a preffered language with Git as source control.

## Pre-requisites/Tools 
1. IDE - IntelliJ or Eclipse 2020 versions. 
2. Java 10 or higher should be installed. 
3. All package dependencies are handled through pom.xml.

## Test configuration and data
1. Test data configuration is driven through .properties files under .

## Assumptions
1. There's a lag in the Pet API after creating a new Pet using the POST API. For this reason, there is a delay used in the tests using Thread.sleep(). 
2. Tests are assumed to be executed in CI/CD. This solution supports overriding the local config settings from CI server's environment variables.

## Test execution
The test framework is configured to use these default values:

    Environment = DEV

Tests can be executed using JUnit test runner in IntelliJ IDE. 
Alternatively, the following command can execute the tests via cli using the default environment. 

`mvn test`

Use the below command to override the default values (especially for test exeuction in CI) 

`mvn test -Denvironment=UAT`

Use the below command to generate default html report using surefire plugin. The location of the reports is "target/site/surefire-report.html"

`mvn surefire-report:report-only`
