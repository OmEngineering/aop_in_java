# Copilot instructions for this repository

## Project snapshot

This repository is a small Spring Boot application generated as a minimal Java project. The app entry point is `src/main/java/com/omengineering/Aop/AopApplication.java`, and the project is configured in `pom.xml` to use Java 21 and Spring Boot 4.1.1.

The codebase is intentionally minimal at the moment: there is no custom module split, no controllers/services/repositories yet, and only one smoke test exists in `src/test/java/com/omengineering/Aop/AopApplicationTests.java`.

## Build, test, and verification commands

Use the Maven wrapper included in the repo rather than a global Maven install when possible.

```bash
# Full project verification (compile + tests)
./mvnw test

# Compile only
./mvnw -q -DskipTests compile

# Run the app locally
./mvnw spring-boot:run

# Run one test class
./mvnw -Dtest=AopApplicationTests test

# Run one test method in a class
./mvnw -Dtest=AopApplicationTests#contextLoads test
```

There is no dedicated lint configuration in this repository. The main validation path is the Maven build and test lifecycle (`./mvnw test` / `./mvnw compile`).

Known caveat: the app currently expects a MySQL datasource in `src/main/resources/application.properties` (`jdbc:mysql://localhost:3306/aop`), so a local MySQL instance (or matching datasource override) is required for the application context to boot successfully. Without that, Spring fails to create the `dataSource` bean because the MySQL JDBC driver is not part of this project setup.

## High-level architecture

- Single-module Spring Boot app.
- Main application bootstrap lives in `AopApplication` and is annotated with `@SpringBootApplication`.
- Application properties are in `src/main/resources/application.properties`.
- Dependency management and plugin configuration are centralized in `pom.xml`.
- Persistence and validation support are already included via Spring Boot starters (`spring-boot-starter-data-jpa`, `spring-boot-starter-validation`), and the project includes H2 for local/test support.
- The datasource is currently configured for MySQL in `application.properties`, so any new persistence work should keep database assumptions explicit and consistent with the project configuration.
- Tests are standard JUnit 5 + Spring Boot integration tests.

## Key conventions specific to this repository

- Keep new Java classes under the existing package structure: `com.omengineering.Aop`.
- Prefer the Maven wrapper (`./mvnw`) for all build/test actions.
- Treat `pom.xml` as the source of truth for Java version, Spring Boot version, and dependency additions.
- Keep application configuration in `src/main/resources/application.properties`; do not add arbitrary config files unless the feature requires them.
- New tests should live under `src/test/java/...` and follow the same package layout as the production code.
- The repo is currently a bootstrap/skeleton app. Do not assume an established service layer, package-by-feature layout, or custom architecture until it is introduced intentionally.
- When adding dependencies, prefer the existing Spring Boot starter pattern instead of manual framework wiring unless the project clearly requires a custom setup.

## Documentation sources

This repo is minimal, but the root README reflects the default Spring Boot project scaffolding and is a useful reference for project intent and Spring Boot guidance.
