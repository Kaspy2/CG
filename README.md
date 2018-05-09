# The Maze - a test-driven developed game by CG devs

## Latest Features (release v2.0):
 - New map types (safe | hazardous)
 - Teams

## Installation
The Maze requires Java v1.8+ to run.

Clone the repository into some local directory.
```sh
$ git clone https://github.com/Kaspy2/CG.git
```

Compile the source files.
```sh
$ cd CG/src/main/java/game
$ javac ./*.java
```

Start up a game.
```sh
$ java -classpath ../ game.Game
```

This is a maven based project, for testing use `mvn test` from `CG/`.

*Note: HTML files are generated in the `htmlouts/` directory, which comes equipped with `player.png` and `styles.css` (resources required for proper viewing of the generated HTML files).*

 
**Note: Hazardous map mode is not for the faint of heart.**
