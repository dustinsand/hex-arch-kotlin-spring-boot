# voter-lambda project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./gradlew :voter-lambda:quarkusDev
```

## Packaging and running the application

The application is packageable using `./gradlew quarkusBuild`.
It produces the executable `voter-lambda-1.0.0-SNAPSHOT-runner.jar` file in `build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

The application is now runnable using `java -jar build/voter-lambda-1.0.0-SNAPSHOT-runner.jar`.

If you want to build an _über-jar_, just add the `--uber-jar` option to the command line:
```
./gradlew quarkusBuild --uber-jar
```

## Creating a native executable

You can create a native executable using: `./gradlew buildNative`.

Or you can use Docker to build the native executable using: `./gradlew buildNative --docker-build=true`.

You can then execute your binary: `./build/voter-lambda-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling#building-a-native-executable .

## Deploying the lambda to AWS

### JDK8 Lambda
cd <root project>/voter-lambda
../gradlew quarkusBuild --uber-jar
sam local invoke --template sam.jvm.yaml --event payload.json
sh manage.sh create

### Native Lambda
cd <root project>/voter-lambda
../gradlew buildNative --docker-build=true --enable-http-url-handler
zip -j build/function.zip src/main/resources/native/bootstrap build/voter-lambda-1.0-SNAPSHOT-runner
sam local invoke --template sam.native.yaml --event payload.json
sh manage.sh native create 