# ConsoleArena

## Overview

*ConsoleArena* is a free console fighting game written in Java and is part of a school project. The game allows
you to fight against the computer. In each fight you can choose how to attack the computer. The computer than reacts
to your attack randomly. When each player has enogh health, the computer now chooses a random attack and you have to
decide how to defend.

The game is finished when one of the players has no more health.

## Installation and Running

There are two options to get *ConsoleArena*:

### Option 1: Cloning and building

Go to https://github.com/yannickkirschen/console-arena and clone the repository. You also have to have
[Apache Maven](https://maven.apache.org/) installed. When you are checking out the repo in an IDE, such as
[IntelliJ IDEA](https://www.jetbrains.com/idea/), you don't have to install Maven.

**Without IDE**

Open the terminal and navigate to the root folder of the project (the one with the `pom.xml` inside it).

Type:

```bash
mvn clean install
```

**With IDE**

Run a new Maven configuration `clean install`.

The project is now built into `target/console-arena-VERSION.jar`. 

### Option 2: Downloading the JAR

The easiest way of running *ConsoleArena* is to download the JAR directly.
Go to [Releases](https://github.com/yannickkirschen/console-arena/releases). Select the latest release and download the
provided JAR file.

In both ways you have to execute the JAR by:

```bash
java -jar target/console-arena-VERSION.jar
```
