MVN?=./mvnw

run:
	$(MVN) compile quarkus:dev

native-build:
	$(MVN) package -Pnative -Dquarkus.native.container-build=true
	docker build -f src/main/docker/Dockerfile.native -t quarkus-eval .

native-run:
	docker run -i --rm --env POSTGRES_HOST=host.docker.internal -p 8081:8080 quarkus-eval
