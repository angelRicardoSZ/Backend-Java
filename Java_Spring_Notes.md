# Spring Boot

Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need minimal Spring configuration.

## Features

- Create stand-alone Spring applications
- Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
- Provide opinionated 'starter' dependencies to simplify your build configuration
- Automatically configure Spring and 3rd party libraries whenever possible
- Provide production-ready features such as metrics, health checks, and externalized configuration
- Absolutely no code generation and no requirement for XML configuration

## Getting Started

### What you'll build

You will build a classic “Hello World!” endpoint which any browser can connect to. You can even tell it your name, and it will respond in a more friendly way.

## What you’ll need

- An Integrated Developer Environment (IDE). Popular choices include [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Spring Tools](https://spring.io/tools), [Visual Studio Code](https://code.visualstudio.com/docs/languages/java), or [Eclipse](https://www.eclipse.org/downloads/packages/), and many more.

- A Java™ Development Kit (JDK). We recommend [BellSoft Liberica JDK](https://bell-sw.com/) version 8 or version 11.

## Start a new Spring Boot project

#### Step 1: Start a new Spring Boot project

Use [start.spring.io](https://start.spring.io/) to create a “web” project. In the “Dependencies” dialog search for and add the “web” dependency as shown in the screenshot. Hit the “Generate” button, download the zip, and unpack it into a folder on your computer.

Projects created by [start.spring.io](https://start.spring.io/) contain [Spring Boot](https://spring.io/projects/spring-boot), a framework that makes Spring ready to work inside your app, but without much code or configuration required. Spring Boot is the quickest and most popular way to start Spring projects.

#### Step 2: Add your code

Open up the project in your IDE and locate the `DemoApplication.java` file in the `src/main/java/com/example/demo` folder. Now change the contents of the file by adding the extra method and annotations shown in the code below. You can copy and paste the code or just type it.

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

public static void main(String[] args) {
SpringApplication.run(DemoApplication.class, args);
}

@GetMapping("/hello")
public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
return String.format("Hello %s!", name);
}
}
```

The `hello()` method we’ve added is designed to take a String parameter called `name`, and then combine this parameter with the word `"Hello"` in the code. This means that if you set your name to `“Amy”` in the request, the response would be `“Hello Amy”`.

The `@RestController` annotation tells Spring that this code describes an endpoint that should be made available over the web. The `@GetMapping(“/hello”)` tells Spring to use our `hello()` method to answer requests that get sent to the `http://localhost:8080/hello` address. Finally, the `@RequestParam` is telling Spring to expect a `name` value in the request, but if it’s not there, it will use the word “World” by default.

#### Step 3: Try it

Let’s build and run the program. Open a command line (or terminal) and navigate to the folder where you have the project files. We can build and run the application by issuing the following command:

**MacOS/Linux:**

```
./mvnw spring-boot:run
```

**Windows:**

```
mvnw spring-boot:run
```

Example:

http://localhost:8080/test/hello?name=NameTest

response:

Hello NameTest!