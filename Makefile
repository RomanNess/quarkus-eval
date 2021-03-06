MVN?=./mvnw

run:
	$(MVN) compile quarkus:dev

native-build:
	$(MVN) package -Pnative -Dquarkus.native.container-build=true
	docker build -f src/main/docker/Dockerfile.native -t quarkus-eval-native .

native-run:
	docker run -i --rm --env POSTGRES_HOST=host.docker.internal -p 8081:8080 quarkus-eval-native

docker-build:
	$(MVN) package
	docker build -f src/main/docker/Dockerfile.jvm -t quarkus-eval .

docker-run:
	docker run -i --rm --env POSTGRES_HOST=host.docker.internal -p 8081:8080 quarkus-eval