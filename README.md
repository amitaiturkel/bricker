# Bricker Game

## Overview
Bricker is a simple brick breaker game implemented in Java. It provides a classic gaming experience where the player controls a paddle to bounce a ball and break bricks. The game includes features such as multiple levels, falling hearts for extra lives, and special power-ups.

## Installation
To use the Bricker game, follow these steps:
1. Clone the repository: `git clone https://github.com/amitaiturkel/bricker.git`
2. Navigate to the project directory: `cd bricker`
3. Install DanoGameLab: `git clone https://github.com/DanoGameLab/DanoGameLab.git`
4. Compile the Java files: `javac -cp DanoGameLab/src:src *.java`
5. Run the game: `java -cp DanoGameLab/src:src BrickerGameManager`

## Usage
Upon running the game, you can control the paddle using the arrow keys. The objective is to bounce the ball to break all the bricks on the screen. Be careful not to let the ball fall off the bottom of the screen, or you will lose a life. Collect falling hearts to gain extra lives and power-ups.

## Command Line Arguments
The Bricker game accepts two optional command line arguments for custom brick configurations:
- Argument 1: Number of bricks in each row.
- Argument 2: Number of rows of bricks.

Example usage:
```bash
java -cp DanoGameLab/src:src BrickerGameManager 10 5
```

## Dependencies
The game relies on the following Java libraries:
- DanoGameLab: A library for managing game objects, collisions, and graphics. (https://github.com/DanoGameLab/DanoGameLab)
- bricker: A package containing game logic and objects specific to the Bricker game.

## Contributions
Contributions to the Bricker game are welcome. If you find any bugs or have suggestions for improvements, please open an issue or submit a pull request on the GitHub repository.

