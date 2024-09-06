# ----------------------------------------------------------------------------------------------------------------------
# VARIABLES
# ----------------------------------------------------------------------------------------------------------------------

SOURCE_PATH := pom.xml
SKIP_TESTS ?= false

# ----------------------------------------------------------------------------------------------------------------------
# DISCLAIMERS
# ----------------------------------------------------------------------------------------------------------------------

# KEEP IN MIND THAT MAKEFILE WILL SHOW ERRORS UNLESS THE LINE IS STARTED WITH A TAB. IF YOU SEE ERRORS
# INDICATING A MISSING SEPARATOR, YOU PROBABLY HAVE SPACES INSTEAD OF TAB

# ----------------------------------------------------------------------------------------------------------------------
# HELP
# ----------------------------------------------------------------------------------------------------------------------

# HELP
# This will output the help for each task
# thanks to https://marmelab.com/blog/2016/02/29/auto-documented-makefile.html
.PHONY: help

help: ## This help.
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

.DEFAULT_GOAL := help

# ----------------------------------------------------------------------------------------------------------------------
# CI/CD COMMANDS
# ----------------------------------------------------------------------------------------------------------------------

build: # Build the application Artifact
	echo "Building jar"
	docker run --rm -w /build -v '${PWD}:/build' devops-tools.dtp.appgate.com:9443/base/maven-jdk17 mvn package -Dmaven.test.skip=$(SKIP_TESTS) -f $(SOURCE_PATH)

unit-tests: # Execute the unitary tests
	echo "Running unit tests"
ifeq ($(SKIP_TESTS),false)
	docker run --rm -w /build -v '${PWD}:/build' devops-tools.dtp.appgate.com:9443/base/maven-jdk17 mvn test -f $(SOURCE_PATH)
endif

integration-tests:  # Execute the integration tests
	echo "TO BE IMPLEMENTED"

component-tests: # Execute component test, to be implemented
	eecho "TO BE IMPLEMENTED"

contract-tests: # Execute contract test, to be implemented
	echo "TO BE IMPLEMENTED"

smoke-tests: # Execute smoke test, to be implemented
	echo "TO BE IMPLEMENTED"

clean: # Clean Project artifacts and directories
	docker run --rm -w /build -v '${PWD}:/build' devops-tools.dtp.appgate.com:9443/base/maven-jdk17 mvn clean -f $(SOURCE_PATH)
	docker run --rm -w /build -v '${PWD}:/build' devops-tools.dtp.appgate.com:9443/base/maven-jdk17 mvn dependency:purge-local-repository -DreResolve=false -f $(SOURCE_PATH)

get-version: # Get version for the application
	appversion=$(shell cat $(SOURCE_PATH) | grep "^    <version>.*</version>$$" | awk -F'[><]' '{print $$3}')
#echo "The extracted version is ${version}"
