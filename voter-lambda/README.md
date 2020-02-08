# voter-lambda project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Local One Time Setup

### Create an Execution Role
View the [Getting Started Guide](https://docs.aws.amazon.com/lambda/latest/dg/gettingstarted-awscli.html) for deploying a lambda with AWS CLI. Specifically, make sure you have created an Execution Role. To use the provided scripts, you will either need to define a LAMBDA_ROLE_ARN environment variable in your profile or define it prior to executing the scripts like this:

export LAMBDA_ROLE_ARN="arn:aws:iam::1234567890:role/lambda-role"

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

(not working) You can create a native executable using: `./gradlew buildNative`.

Or you can use Docker to build the native executable using: `./gradlew buildNative --docker-build=true`.

You can then execute your binary: `./build/voter-lambda-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling#building-a-native-executable .

## Deploying the lambda to AWS

### JDK8 Lambda
```
cd <root project>/voter-lambda
../gradlew quarkusBuild --uber-jar
sam local invoke --template sam.jvm.yaml --event payload.json
sh manage.sh create
Use AWS Lambda console to invoke it using the Test feature. Example test payload to pass: { "lastName": "shimono" }
```

### Native Lambda
```
cd <root project>/voter-lambda
../gradlew buildNative --docker-build=true --enable-http-url-handler
zip -j build/function.zip src/main/resources/native/bootstrap build/voter-lambda-1.0-SNAPSHOT-runner
sam local invoke --template sam.native.yaml --event payload.json
sh manage.sh native create
Use AWS Lambda console to invoke it using the Test feature. Example test payload to pass: { "lastName": "shimono" }
```

# AWS Lambda Performance Results

| Runtime | Cold Start Latency* | Warm Latency | Resources Configured | Max Memory Used | Artifact Size |
| ------- | -------: | -------: | -------: | -------: | -------: | 
| JDK 8   | 765 ms | .73 ms | 256 MB | 124 MB | 38 MB|
| Custom (Native) | 18 ms | .99 ms | 256 MB | 72 MB | 11 MB|

Conclusion

* Native results show JVM languages can be used for lambdas to have similar latency and resource utilization as other languages like Go, Python and Node   
* Execution times were essentially the same when the lambda was warm
* Cold Start is a serious concern when the runtime was JDK 8 and latency is important

Note: Cold start latency fluctuated and were not consistent for both JDK 8 and Custom (Native).     