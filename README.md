# Birthday Assignment Application

## Assignment

Given a JSON file with a list of people and their dates of birth, write a program to print out the people whose birthday is today.

If a person was born on Feb 29th, then their birthday during a non-leap year should be considered to be Feb 28th.

Input sample file:

```
[
    ["Doe", "John", "1982/10/08"],
    ["Wayne", "Bruce", "1965/01/30"],
    ["Gaga", "Lady", "1986/03/28"],
    ["Curry", "Mark", "1988/02/29"]
]
```

You can use whichever programming language you like. The assignment should take 60 to 90 min. If it’s taking longer, consider whether you’re complicating things.

If you make any assumptions, trade-offs or de-prioritise features for timeliness, please document these decisions.

Your submission must have:

* Instructions to run the code

* Tests to check if your code is correct, robust and complete

* Edge cases handled and tested

* Iterative development, backed by commit history

* Modular, cohesive code with sensible separation of concerns

Bonus points for following Test-Driven Development.

Please do not overcomplicate the code. You don’t need a web framework, database or message queues for this submission. Keep it simple!


## Implementation

### Assumptions

- every 'Person' in the input is represented as a list of 3 String elements: last name, first name, date of birth (in this order) where the date of birth is in the format "yyyy/MM/dd"
- the input is a Json file in the format List<List<String>>

### Prerequisites

- Java 11 including maven (see [Java 11 Downloads](https://www.oracle.com/java/technologies/downloads/#java11))

### How to run the application

1. ```mvn clean compile assembly:single```
Followed by either 
2. ```java -cp target/backend-birthday-assigment-1.0.0-SNAPSHOT.jar com.backend.birthday.assignment.BirthdayAssignmentApplication``` to execute the program using the default json input file `sample_input.json` or
3. ```java -cp target/backend-birthday-assigment-1.0.0-SNAPSHOT.jar com.backend.birthday.assignment.BirthdayAssignmentApplication ~/Downloads/sample_input.json``` to execute the program using a custom file located on your machine in the Downloads directory (or update the path to be any directory on your machine)

### Future improvements

- add more Java Doc
- add more corner cases such as when a person's date is not in the correct format or the person's array is more / less than 3 elements
- add shell script to run program as the long java commands are not user-friendly
- test how this works with large input files, as we read entire file at once rather than using an input stream with a buffered reader and readLine (would require each line to contain exactly one person element)
- could create an API for this and send the input to the API via a POST endpoint
