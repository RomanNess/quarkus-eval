MVN?=./mvnw

run:
	$(MVN) compile quarkus:dev

native-build:
	$(MVN) package -Pnative -Dquarkus.native.container-build=true
	docker build -f src/main/docker/Dockerfile.native -t quarkus-eval .

native-run:
# todo: note that H2 does not run in native image
	docker run -i --rm -p 8080:8080 quarkus-eval