# quarkus-eval project

Evaluating Quarkus 1.11.1 as an alternative to Spring Boot.

## Features
- [x] Endpoints with JAX RS via `quarkus-resteasy` & `quarkus-resteasy-jackson`.
- [x] Persistence with Panache using quarkus-hibernate-orm-panache. 
  A Postgres container to run the app locally is provided via `docker-compose.yml`.
- [x] Persistence for unit tests with testcontainers. 
  No manual startup of a postgres container necessary for tests.
- [x] Flyway migrations via `quarkus-flyway.
- [x] OpenApi / SwaggerUI via `quarkus-smallrye-openapi`.
- [x] Hibernate Validator via `quarkus-hibernate-validator`.
- [x] Health Check including live/ready via `quarkus-smallrye-health` on `/q/health`.

## Stumbling blocks
- I had quite a few troubles to get `lombok` and `mapstruct` working together.
  `mapstruct` would not see the builders of my value objects reliably.
  Only the solution with annotation processors was working reliably in the end.
  Not sure though if this is caused by Quarkus or the newest versions of the library itself.
- There is no `@Sql` equivalent to execute DB scripts before and after tests.
  There is an extension based upon flyway though (https://github.com/radcortez/flyway-junit5-extensions).
  For now I did a quick and dirty flyway migration manually before every test method.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using 
`./mvnw compile quarkus:dev` or `make run`. 
- It seems like mapstruct cannot handle all changes in dev mode (probably because annotation processors do not run on reload).

## Creating a native executable
`make native-build` to build a docker image with native executable.

`make native-run` to run it. Though, the current config will only run on Macs.
