# Swiggy Assignment - Magical Arena Game | 09-06-2024

### Problem Statement

Design a Magical Arena where players engage in battles. Each player is defined by attributes such as "health," "strength," and "attack," all positive integers. A player is defeated if their health reaches zero. Two players can engage in a match in the arena. Players take turns attacking, with the attacking player rolling the attacking dice and the defending player rolling the defending dice. The attacker's "attack" value multiplied by the outcome of the attacking dice roll represents the damage inflicted. The defender's "strength" value multiplied by the outcome of the defending dice represents the damage defended. Any excess damage inflicted reduces the defender's health. The game ends when any player's health reaches zero.
At the start of a match, the player with lower health attacks first.

#### Example

Assume two players:

- Player A: 50 health, 5 strength, 10 attack.
- Player B: 100 health, 10 strength, 5 attack.

Both the attacking and defending dice are 6-sided, with values ranging from 1 to 6.

1. Player A attacks and rolls the die. Die roll: 5. Player B defends and rolls the die. Die roll: 2.
   - Attack damage: 5 * 10 = 50
   - Defending strength: 10 * 2 = 20
   - Player B's health reduced by 30 to 70.

2. Player B attacks and rolls the die. Die roll: 4. Player A defends and rolls the die. Die roll: 3.
   - Attack damage: 4 * 5 = 20
   - Defending strength: 5 * 3 = 15
   - Player A's health reduced by 5 to 45.
   
3. Game Continues until either of the player health touches 0.


### Software Requirements
- Java(TM) SE Runtime Environment (build 19.0.2+7-44)
- [Eclipse IDE for Enterprise Java and Web Developers](https://www.eclipse.org/) | Version: 2023-09 (4.29.0)

## Installation

- Unzip the MagicalArenaGameByTejasMN.zip file to your local machine.

# Running the Java Project

Here I explain how to run the Java Maven project both in Eclipse and from the terminal/command line.

## Running in Eclipse

A. **Import the Maven Project:**
   - Open Eclipse and select "File" > "Import".
   - In the import dialog, choose "Maven" > "Existing Maven Projects" and click "Next".
   - Browse to the  directory "\MagicalArenaGame" containing your Maven project (directory containing /src, .project, pom.xml file etc ) and click "Finish".

B. **Run the Client.java file containing main method:**
   - Locate the Client.java file (\MagicalArenaGame -> \src\main\java -> \com\swiggy\MagicalArenaGame -> Client.java) in the Project Explorer.
   - Right-click on the Client.java file in the Project Explorer.
   - Select "Run As" > "Java Application".

C. **Run the unit tests:**
   - Locate the Unit Tests folder (\MagicalArenaGame -> \src\test\java -> \com\swiggy\MagicalArenaGame\UnitTests ) or com.swiggy.MagicalArenaGame.UnitTests package in the Project Explorer.
   - To Run single test file: Right-click on the file.
   - To Run all tests: Right-click on the UnitTests package/folder in the Project Explorer.
   - Select "Run As" > "Junit Test".
   - To see the test results : Open the JUnit view by selecting "Window" > "Show View" > "Other..." and then typing "JUnit" in the search box. Select "JUnit" from the list and click "OK" to open the view.
   

## Running from Terminal/Command Line

A. **Navigate to Project Directory:**
   - Open your terminal/command prompt.
   - Use the `cd` command to navigate to the directory  "\MagicalArenaGame" containing your Maven project (directory containing .project, pom.xml file etc ).

B. **Compile the Client.Java file containing main method:**
   - Use the `javac` command to compile your Client.java file. You'll need to specify the classpath and the fully qualified name of your Client.java file.
   - Run: `javac -cp target/classes -d target/classes src/main/java/com/swiggy/MagicalArenaGame/Client.java`
   - Client.class file will be generated in directory "\MagicalArenaGame\target\classes\com\swiggy\MagicalArenaGame"
     
C. **Run the Main Class:**
   - Use the `java` command to run your main Client class. You'll need to specify the classpath and the fully qualified package name of your Client class.
   - Run: `java -cp target/classes com.swiggy.MagicalArenaGame.Client`
     
D. ** OR Run using already compiled Jar File:**
   - Using `cd` command Navigate to folder containing MagicalArenaGame.jar (\MagicalArenaGame)
   - Run: `java -cp MagicalArenaGame.jar com.swiggy.MagicalArenaGame.Client` where Client is the class containing main method.
  
## Project Structure

- `MagicalArenaGame/` : Project directory
    - `src/`: This directory contains the source code of the project.
      - `main/`: Main source code directory.
        - `java/`: Java source files for the main application code.
          - `com.swiggy.MagicalArenaGame/` : Package for the source code.
            - `Client.java` : Main Code containing main method which client uses for playing.
            - `Arenas/` : Directory containing implementation for Arenas.
            - `Dice/` : Directory containing implementation for different types of Dice.
            - `Exceptions/` : Directory containing custom Exception classes for the game.
            - `Interfaces/` : Directory containing interfaces for the Players, Dice, Arenas and other Entities in the game.
            - `Players/` : Directory containing implementation for Players.
            - `Strategies/` : Directory containing implementation for Playing and Winning Strategy.
            - `Utility/` : Directory containing implementation for any helper or utility functions. Eg: For swapping players when taking turns.
            - `Validations/` : Directory containing validations for the Entities like Player and Die in the game.
      - `test/`: Test source code directory.
        - `java/`: Java source files for the main application code.
            - `com.swiggy.MagicalArenaGame/` : Package for the source code.
               - `UnitTests/` : Directory containing unit tests for the game.
    - `target/`: This directory contains compiled classes and metadata.
      - `classes/`: Contains classes for java source files. 
      - `test-classes/`: Contains classes for java unit test source files.
     - `junit/`: This directory contains junit test report for the unit tests. Open `index.html` to view the report.
     - `coverage/`: This directory contains code coverage report for the project. Open `index.html` to view the report.
    - `pom.xml`: Maven project configuration file.
    - `build.xml`: Ant build file.

#### Result

- Number of Tests: 29
- Test Coverage: 91%


### Submitted By
 - [Tejas M N]
 
 
 
 