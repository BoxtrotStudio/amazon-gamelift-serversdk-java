# GameLiftServerSdk Java
A port of Amazon's C# GameLift Server SDK for Java

This library is a direct port of the C# library for Amazon's GameLift Server SDK. All classes 
and functions should map the same way. 

## Documention
 
You can find the official GameLift documentation [here](https://aws.amazon.com/documentation/gamelift/).

## Installation

This package can be found the Boxtrot Studio maven repo. Add the following to your project's pom to add the repo

```
    <repositories>
        <repository>
            <id>maven-central</id>
            <url>http://central.maven.org/maven2</url>
        </repository>
        ....
        <repository>
            <id>boxtrotstudio-repo</id>
            <url>https://repo.boxtrotstudio.com/maven</url>
        </repository>
    </repositories>
```

or if you're using gradle

```
  repositories {
    mavenCentral()
    maven { url "https://repo.boxtrotstudio.com/maven"}
  }
```

once the repo is added, you can simply install the package by adding the following to your project's pom file

```
    <dependencies>
        <dependency>
            <groupId>com.boxtrotstudio</groupId>
            <artifactId>aws-java-server-sdk</artifactId>
            <version>3.1.5</version>
        </dependency>
    </dependencies>
```

or if you're using gradle

```
compile 'com.boxtrotstudio:aws-java-server-sdk:3.1.5'
```

## Compiling

This project uses maven, so you can just use maven to compile

```
mvn clean install
```

## Example code

You can find a simple Java class that showcases a simple game server initialization with GameLift [here](https://github.com/BoxtrotStudio/Java-Amazon-GameLift-Server-SDK/blob/master/src/test/java/com/boxtrotstudio/examples/MyServerClass.java)

## License

This project is [licensed under the MIT license](https://github.com/BoxtrotStudio/Java-Amazon-GameLift-Server-SDK/blob/master/LICENSE)

