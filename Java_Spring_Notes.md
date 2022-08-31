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

## [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)

This guide walks you through the process of creating a “Hello, World” RESTful web service with Spring.

Project:

A service that will accept HTTP GET requests at `http://localhost:8080/greeting`.

It will respond with a JSON representation of a greeting, as the following listing shows:

```json
{"id":1,"content":"Hello, World!"}
```

You can customize the greeting with an optional `name` parameter in the query string, as the following listing shows:

```
http://localhost:8080/greeting?name=User
```

The `name` parameter value overrides the default value of `World` and is reflected in the response, as the following listing shows:

```json
{"id":1,"content":"Hello, User!"}
```

[Download](https://github.com/spring-guides/gs-rest-service/archive/main.zip) and unzip the source repository for this guide, or clone it using [Git](https://spring.io/understanding/Git): `git clone https://github.com/spring-guides/gs-rest-service.git`

## Create a Resource Representation Class

Now that you have set up the project and build system, you can create your web service.

Begin the process by thinking about service interactions.

The service will handle `GET` requests for `/greeting`, optionally with a `name` parameter in the query string. The `GET` request should return a `200 OK` response with JSON in the body that represents a greeting. It should resemble the following output:

```json
{
    "id": 1,
    "content": "Hello, World!"
}
```

The `id` field is a unique identifier for the greeting, and `content` is the textual representation of the greeting.

To model the greeting representation, create a resource representation class. To do so, provide a plain old Java object with fields, constructors, and accessors for the `id` and `content` data, as the following listing (from `src/main/java/com/example/restservice/Greeting.java`) shows:

```java
package com.example.restservice;

public class Greeting {

	private final long id;
	private final String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
```

## Create a Resource Controller

n Spring’s approach to building RESTful web services, HTTP requests are handled by a controller. These components are identified by the [`@RestController`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html) annotation, and the `GreetingController` shown in the following listing (from `src/main/java/com/example/restservice/GreetingController.java`) handles `GET` requests for `/greeting` by returning a new instance of the `Greeting` class:

```java
package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
```

This controller is concise and simple, but there is plenty going on under the hood. We break it down step by step.

The `@GetMapping` annotation ensures that HTTP GET requests to `/greeting` are mapped to the `greeting()` method.

`@RequestParam` binds the value of the query string parameter `name` into the `name` parameter of the `greeting()` method. If the `name` parameter is absent in the request, the `defaultValue` of `World` is used.

The implementation of the method body creates and returns a new `Greeting` object with `id` and `content` attributes based on the next value from the `counter` and formats the given `name` by using the greeting `template`.

A key difference between a traditional MVC controller and the RESTful web service controller shown earlier is the way that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML, this RESTful web service controller populates and returns a `Greeting` object. The object data will be written directly to the HTTP response as JSON.

This code uses Spring [`@RestController`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html) annotation, which marks the class as a controller where every method returns a domain object instead of a view. It is shorthand for including both `@Controller` and `@ResponseBody`.

The `Greeting` object must be converted to JSON. Thanks to Spring’s HTTP message converter support, you need not do this conversion manually. Because [Jackson 2](https://github.com/FasterXML/jackson) is on the classpath, Spring’s [`MappingJackson2HttpMessageConverter`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/converter/json/MappingJackson2HttpMessageConverter.html) is automatically chosen to convert the `Greeting` instance to JSON.

`@SpringBootApplication` is a convenience annotation that adds all of the following:

- `@Configuration`: Tags the class as a source of bean definitions for the application context.
- `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if `spring-webmvc` is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a `DispatcherServlet`.
- `@ComponentScan`: Tells Spring to look for other components, configurations, and services in the `com/example` package, letting it find the controllers.

The `main()` method uses Spring Boot’s `SpringApplication.run()` method to launch an application. Did you notice that there was not a single line of XML? There is no `web.xml` file, either. This web application is 100% pure Java and you did not have to deal with configuring any plumbing or infrastructure.

```java
// in  src/main/java/com/example/restservice/RestServiceApplication.java

package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

}
```

### Build an executable JAR

You can run the application from the command line with Gradle or Maven. You can also build a single executable JAR file that contains all the necessary dependencies, classes, and resources and run that. Building an executable jar makes it easy to ship, version, and deploy the service as an application throughout the development lifecycle, across different environments, and so forth.

If you use Gradle, you can run the application by using `./gradlew bootRun`. Alternatively, you can build the JAR file by using `./gradlew build` and then run the JAR file, as follows:

```
java -jar build/libs/gs-rest-service-0.1.0.jar
```

If you use Maven, you can run the application by using `./mvnw spring-boot:run`. Alternatively, you can build the JAR file with `./mvnw clean package` and then run the JAR file, as follows:

```
java -jar target/gs-rest-service-0.1.0.jar
```

## Test the Service

Now that the service is up, visit `http://localhost:8080/greeting`, where you should see:

```json
{"id":1,"content":"Hello, World!"}
```

Provide a `name` query string parameter by visiting `http://localhost:8080/greeting?name=User`. Notice how the value of the `content` attribute changes from `Hello, World!` to `Hello, User!`, as the following listing shows:

```json
{"id":2,"content":"Hello, User!"}
```

This change demonstrates that the `@RequestParam` arrangement in `GreetingController` is working as expected. The `name` parameter has been given a default value of `World` but can be explicitly overridden through the query string.

Notice also how the `id` attribute has changed from `1` to `2`. This proves that you are working against the same `GreetingController` instance across multiple requests and that its `counter` field is being incremented on each call as expected.

[Common Application Properties (spring.io)](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)

[Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.info)

Various properties can be specified inside your `application.properties` file, inside your `application.yml` file, or as command line switches. This appendix provides a list of common Spring Boot properties and references to the underlying classes that consume them.

## 1. Core Properties

| Name                                                         | Description                                                  | Default Value |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------- |
| [`debug`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.debug) | Enable debug logs.                                           | `false`       |
| [`info.*`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.info) | Arbitrary properties to add to the info endpoint.            |               |
| [`spring.profiles.active`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.spring.profiles.active) | Comma-separated list of active profiles. Can be overridden by a command line switch. |               |
| [`spring.config.import`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.spring.config.import) | Import additional config data.                               |               |
| [`spring.info.git.location`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.spring.info.git.location) | Location of the generated git.properties file.               |               |
| [`spring.main.log-startup-info`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.spring.main.log-startup-info) | Whether to log information about the application when it starts. |               |
| [`spring.main.sources`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.spring.main.sources) | Sources (class names, package names, or XML resource locations) to include in the ApplicationContext. |               |
| [`spring.profiles.default`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.spring.profiles.default) | Name of the profile to enable if no profile is active.       |               |
| [`spring.quartz.jdbc.initialize-schema`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.spring.quartz.jdbc.initialize-schema) | Database schema initialization mode.                         |               |

##  11. Server Properties

| Name                                                         | Description                                                  | Default value |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------- |
| [`server.address`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.server.server.address) | Network address to which the server should bind.             |               |
| [`server.error.path`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.server.server.error.path) | Path of the error controlle                                  | /error        |
| [`server.port`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.server.server.port) | Server HTTP port.                                            | 8080          |
| [`server.reactive.session.cookie.name`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.server.server.reactive.session.cookie.name) | Name for the cookie.                                         |               |
| [`server.server-header`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.server.server.server-header) | Value to use for the Server response header (if empty, no header is sent). |               |
| [`server.servlet.context-path`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.server.server.servlet.context-path) | Context path of the application.                             |               |
| [`server.servlet.encoding.charset`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.server.server.servlet.encoding.charset) | Charset of HTTP requests and responses. Added to the "Content-Type" header if not set explicitly. | `UTF-8`       |

## [Profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.profiles)

Spring Profiles provide a way to segregate parts of your application configuration and make it be available only in certain environments. Any `@Component`, `@Configuration` or `@ConfigurationProperties` can be marked with `@Profile` to limit when it is loaded, as shown in the following example:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration(proxyBeanMethods = false)
@Profile("production")
public class ProductionConfiguration {

    // ...

}
```

You can use a `spring.profiles.active` `Environment` property to specify which profiles are active. You can specify the property in any of the ways described earlier in this chapter. For example, you could include it in your `application.properties`, as shown in the following example:

```
spring.profiles.active=dev,hsqldb
```

You could also specify it on the command line by using the following switch: `--spring.profiles.active=dev,hsqldb`.

If no profile is active, a default profile is enabled. The name of the default profile is `default` and it can be tuned using the `spring.profiles.default` `Environment` property, as shown in the following example:

```
spring.profiles.default=none
```

`spring.profiles.active` and `spring.profiles.default` can only be used in non-profile specific documents. This means they cannot be included in [profile specific files](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.files.profile-specific) or [documents activated](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.files.activation-properties) by `spring.config.activate.on-profile`.

For example, the second document configuration is invalid:

```
# this document is valid
spring.profiles.active=prod
#---
# this document is invalid
spring.config.activate.on-profile=prod
spring.profiles.active=metrics
```

# Spring Data

Spring Data’s mission is to provide a familiar and consistent, Spring-based programming model for data access while still retaining the special traits of the underlying data store.

Spring Data JPA, part of the larger Spring Data family, makes it easy to easily implement JPA based repositories. This module deals with enhanced support for JPA based data access layers. It makes it easier to build Spring-powered applications that use data access technologies.

## Features

- Sophisticated support to build repositories based on Spring and JPA
- Support for [Querydsl](http://www.querydsl.com/) predicates and thus type-safe JPA queries
- Transparent auditing of domain class
- Pagination support, dynamic query execution, ability to integrate custom data access code
- Validation of `@Query` annotated queries at bootstrap time
- Support for XML based entity mapping
- JavaConfig based repository configuration by introducing `@EnableJpaRepositories`.

### Connection to database

```
connecting JPA

// in build.gradle file

implementation 'org.springframework.boot:spring-boot-starter-data-jpa' 
// get from https://mvnrepository.com/, search = spring boot data jpa, select spring boot starter jpa
// select the final version -> copy group (in this case = org.springframework.boot) and name ( int 
// this case = spring-boot-starter-data-jpa)
// after load gradle


connection to db

// in application-environment.properties

// spring.datasource.url=jdbc:postgresql://localhost:5432/dbname
// spring.datasource.username=username 
// spring.datasource.password=pass
```

### [Spring Data JPA - Reference Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface)

Spring Data JPA provides repository support for the Java Persistence API (JPA). It eases development of applications that need to access JPA data sources.

#### Working with Spring Data Repositories

The goal of the Spring Data repository abstraction is to significantly reduce the amount of boilerplate code required to implement data access layers for various persistence stores.

### 4.1. Core concepts

The central interface in the Spring Data repository abstraction is `Repository`. It takes the domain class to manage as well as the ID type of the domain class as type arguments. This interface acts primarily as a marker interface to capture the types to work with and to help you to discover interfaces that extend this one. The [`CrudRepository`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html) interface provides sophisticated CRUD functionality for the entity class that is being managed.

[spring-data-commons/CrudRepository.java at main · spring-projects/spring-data-commons (github.com)](https://github.com/spring-projects/spring-data-commons/blob/main/src/main/java/org/springframework/data/repository/CrudRepository.java)

```java
/*
 * Copyright 2008-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.repository;

import java.util.Optional;

import org.springframework.dao.OptimisticLockingFailureException;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 *
 * @author Oliver Gierke
 * @author Eberhard Wolff
 * @author Jens Schauder
 */
@NoRepositoryBean
public interface CrudRepository<T, ID> extends Repository<T, ID> {
	
	/**
	 * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
	 * entity instance completely.
	 *
	 * @param entity must not be {@literal null}.
	 * @return the saved entity; will never be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
	 * @throws OptimisticLockingFailureException when the entity uses optimistic locking and has a version attribute with
	 *           a different value from that found in the persistence store. Also thrown if the entity is assumed to be
	 *           present but does not exist in the database.
	 */
	<S extends T> S save(S entity);

	/**
	 * Saves all given entities.
	 *
	 * @param entities must not be {@literal null} nor must it contain {@literal null}.
	 * @return the saved entities; will never be {@literal null}. The returned {@literal Iterable} will have the same size
	 *         as the {@literal Iterable} passed as an argument.
	 * @throws IllegalArgumentException in case the given {@link Iterable entities} or one of its entities is
	 *           {@literal null}.
	 * @throws OptimisticLockingFailureException when at least one entity uses optimistic locking and has a version
	 *           attribute with a different value from that found in the persistence store. Also thrown if at least one
	 *           entity is assumed to be present but does not exist in the database.
	 */
	<S extends T> Iterable<S> saveAll(Iterable<S> entities);

	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal id} is {@literal null}.
	 */
	Optional<T> findById(ID id);

	/**
	 * Returns whether an entity with the given id exists.
	 *
	 * @param id must not be {@literal null}.
	 * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
	 * @throws IllegalArgumentException if {@literal id} is {@literal null}.
	 */
	boolean existsById(ID id);

	/**
	 * Returns all instances of the type.
	 *
	 * @return all entities
	 */
	Iterable<T> findAll();

	/**
	 * Returns all instances of the type {@code T} with the given IDs.
	 * <p>
	 * If some or all ids are not found, no entities are returned for these IDs.
	 * <p>
	 * Note that the order of elements in the result is not guaranteed.
	 *
	 * @param ids must not be {@literal null} nor contain any {@literal null} values.
	 * @return guaranteed to be not {@literal null}. The size can be equal or less than the number of given
	 *         {@literal ids}.
	 * @throws IllegalArgumentException in case the given {@link Iterable ids} or one of its items is {@literal null}.
	 */
	Iterable<T> findAllById(Iterable<ID> ids);

	/**
	 * Returns the number of entities available.
	 *
	 * @return the number of entities.
	 */
	long count();

	/**
	 * Deletes the entity with the given id.
	 * <p>
	 * If the entity is not found in the persistence store it is silently ignored.
	 *
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
	 */
	void deleteById(ID id);

	/**
	 * Deletes a given entity.
	 *
	 * @param entity must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 * @throws OptimisticLockingFailureException when the entity uses optimistic locking and has a version attribute with
	 *           a different value from that found in the persistence store. Also thrown if the entity is assumed to be
	 *           present but does not exist in the database.
	 */
	void delete(T entity);

	/**
	 * Deletes all instances of the type {@code T} with the given IDs.
	 * <p>
	 * Entities that aren't found in the persistence store are silently ignored.
	 *
	 * @param ids must not be {@literal null}. Must not contain {@literal null} elements.
	 * @throws IllegalArgumentException in case the given {@literal ids} or one of its elements is {@literal null}.
	 * @since 2.5
	 */
	void deleteAllById(Iterable<? extends ID> ids);

	/**
	 * Deletes the given entities.
	 *
	 * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
	 * @throws IllegalArgumentException in case the given {@literal entities} or one of its entities is {@literal null}.
	 * @throws OptimisticLockingFailureException when at least one entity uses optimistic locking and has a version
	 *           attribute with a different value from that found in the persistence store. Also thrown if at least one
	 *           entity is assumed to be present but does not exist in the database.
	 */
	void deleteAll(Iterable<? extends T> entities);

	/**
	 * Deletes all entities managed by the repository.
	 */
	void deleteAll();
}
```

