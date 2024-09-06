# Repository Name

<!-- TABLE OF HEADER -->
[![Java][skill-java-shield]][skill-java-url]
[![Spring][skill-spring-shield]][skill-spring-url]
[![Lombok][skill-lombok-shield]][skill-lombok-url]
[![Maven][skill-maven-shield]][skill-maven-url]

[![Commits][documentation-commit-shield]][documentation-commit-url]

<!-- ABOUT THE PROJECT -->

## About The Project

`Repository Name` The service core for incidents.

### Project Organization

This section contains the archetype how the project is built and the content of each of the application directories.

    ├── .github
    │   ├── workflows                           <- Github actions pipelines        
    │   │   ├── deployment.yaml                 <- Deployment pipeline
    │   │   └── deployment.yml                  <- Build pipeline
    ├── deployment
    │   ├── config                          
    │   │   ├── environments                    <- Config files by environment
    │   │   │   ├── prod
    │   │   │   └── qa                      
    │   │   └── newrelic                        <- Newrelic config files
    │   ├── Dockerfile                          <- Dockerfile to build project
    │   └── values.yaml                         <- Config file deployment (ports, resources, flags (loggin, newrelic))
    ├── docs                                    <- OpenApi, AsyncApi, etc
    ├── src
    │   ├── main                             
    │   │   ├── java
    │   │   │   └── com.appgate.dtp
    │   │   │       ├── component1              
    │   │   │       │   ├── adapters            <- IN/OUT Adapters
    │   │   │       │   ├── application         <- Use cases
    │   │   │       │   └── domain              <- Entities, Domain Service, Value Objects, etc
    │   │   │       ├── shared                  <- Shared Components, utils, adapters, value objects
    │   │   │       └── Application.java        <- Entry point of application
    │   │   └── resources                       <- Application resources
    │   │       ├── application.properties
    │   │       └── logback.xml
    │   └── test
    │       └──java
    │          └── com.appgate.dtp
    │              ├── component1
    │              │   ├── adapters
    │              │   ├── application
    │              │   └── domain
    │              └── shared
    ├── .gitignore
    ├── Makefile                                
    ├── pom.xml
    └── README.md

<!-- GETTING STARTED -->

## Getting Started

Instructions on how to configure your project locally. To get a working local copy, follow these example steps.

### Prerequisites

* [Java 17](https://www.oracle.com/java/technologies/downloads/#jdk17-linux)
* [Docker](https://docs.docker.com/engine/install/)
* [Maven](https://maven.apache.org/install.html)

### Installation

#### Build artifact

**Using Java 17 as default JDK:**
If you don't know which JDK is running in your machine, check it using:

```shell
sudo update-alternatives --config java
```

**Now, you can**

```shell
make build-imageLocal
```

#### Start Application

```shell
make run-image
```

#### Run unit tests

*Unit Test*

```shell
mvn test
```

<!-- USAGE EXAMPLES -->

## Usage

### Kafka

The application requires only Kafka to works. for details about Async API,
check [AsyncApi](../repository_name/docs/events-asyncapi.yaml)

**Input Message Example:**

```json
{
}
```

**Success Output Message Example:**

```json
{
}
```

**Error 400 Output Message Example:** .

```json
{
}
```

### REST APIs

More details
at [HTTP Client in IntelliJ IDEA](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html)

**Service api context Path:**

```
/service_name/api/v1
```

### Extra documentation

For more information about work flow of the App please
check [AsyncApi file](../reposirory_name/docs/events-asyncapi.yaml)
and [Wiki](https://appgateinc.atlassian.net/wiki/spaces/...)

# Contribution Guide

We want to make contributing to this project as easy and transparent as possible. Please take a look
the [CI/CD Processes](https://appgateinc.atlassian.net/wiki/spaces/DTP/pages/4136468485/CI+CD+Process)
guidelines so that you can see all the things you need to keep in mind related to the branches management or commits
naming conventions in a deep way.

## Pull Requests

The branch strategy we use is `GitHub Flow` which is a straightforward way to handle branches in order to favor the
continuous integration. More
information can be found
in [Branching Strategy](https://appgateinc.atlassian.net/wiki/spaces/DTP/pages/4136468485/CI+CD+Process#Branching-Strategy)

## Commits Guidelines

it is important to keep working on the semantics behind every single change that is going to be pushed to our
repositories.
With that in mind, DTP uses the `Conventional Commits` specification together with `gitmoji` in order to reduce the
cognitive
complexity and make readable commits for humans and even, for automation processes.

### Commit Structure

```
[gitmoji] [type]([Jira ID]){!}: [description]
```

> **Convention**
>
>`[]` : mandatory placeholder.
>
>`{}` : optional placeholder.
>
>`!` : indicates that a commit has breaking changes that should represent a Major Version.
(See the CI/CD [Versioning](https://appgateinc.atlassian.net/wiki/spaces/DTP/pages/4136468485/CI+CD+Process#Versioning)
> wiki)

### Types

There will be a close relationship with versioning if the commit types are handled in a proper way,
below you can find the list of allowed types, if further information is needed, please go to
the [Versioning](https://appgateinc.atlassian.net/wiki/spaces/DTP/pages/4136468485/CI+CD+Process#Versioning) wiki

| Type       | Description                                                                                                             |
|------------|-------------------------------------------------------------------------------------------------------------------------|
| `feat`     | prefix in the title indicates that PR is a feature and MINOR release must be triggered                                  |
| `fix`      | prefix in the title indicates that PR is a bug fix and PATCH release must be triggered                                  |
| `docs`     | prefix in the title indicates that PR is only related to the documentation and there is no need to trigger release.     |
| `chore`    | prefix in the title indicates that PR is only related to cleanup in the project and there is no need to trigger release |
| `test`     | prefix in the title indicates that PR is only related to tests and there is no need to trigger release                  |
| `refactor` | prefix in the title indicates that PR is only related to refactoring and there is no need to trigger release            |
| `ci`       | prefix in the title indicates that PR is only related to CI/CD stuff                                                    |
| `style`    | prefix in the title indicates that PR is only related to aesthetics stuff, such as style sheets like CSS, or SASS.      |

### Samples
> :sparkles: **feat(DPTV10-653)**: publish business events to message broker
>
> :test_tube: **test(DPTV10-653)**: include integration tests related to kafka integration
> 
> :sparkles: **feat(DTPV10-1234)!**: version 2 of customers tickets API
> 
> :closed_lock_with_key: **fix(DTPV10-2345)**: fix secrets management

## More information

You can find more information like c4 model diagrams and entrypoints documentation at confluence, please refer to
the [Documentation](https://appgateinc.atlassian.net/wiki/spaces/...)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://shields.io/ -->

[skill-java-shield]: https://img.shields.io/badge/JAVA-17-blue

[skill-java-url]: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

[skill-spring-shield]: https://img.shields.io/badge/Spring%20Boot-2.7.5-blue

[skill-spring-url]: https://spring.io/guides/gs/spring-boot/

[skill-lombok-shield]: https://img.shields.io/badge/lombok-1.18.22-blue

[skill-lombok-url]: https://projectlombok.org/

[skill-springfeign-shield]: https://img.shields.io/badge/springfeign-3.1.0-blue

[skill-springfeign-url]: https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html

[skill-caffeine-shield]: https://img.shields.io/badge/caffeine-3.0.5-blue

[skill-caffeine-url]: https://www.baeldung.com/spring-boot-caffeine-cache

[skill-springkafka-shield]: https://img.shields.io/badge/springkafka-2.8.2-blue

[skill-springkafka-url]: https://kafka.apache.org/

[skill-maven-shield]: https://img.shields.io/badge/maven-3.8.6-blue

[skill-maven-url]: https://maven.apache.org/install.html

[documentation-commit-shield]: https://img.shields.io/badge/Conventional%20Commits-1.0.0--beta.2-orange

[documentation-commit-url]: https://www.conventionalcommits.org/es/v1.0.0-beta.2/

[documentation-DDD-shield]: https://img.shields.io/badge/DDD-clean%20architecture-orange

[documentation-DDD-url]: https://github.com/ddd-crew
