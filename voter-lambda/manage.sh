function cmd_create() {
  echo Creating function
  aws lambda create-function \
    --function-name ${FUNCTION_NAME} \
    --zip-file ${ZIP_FILE} \
    --handler ${HANDLER} \
    --runtime ${RUNTIME} \
    --role ${LAMBDA_ROLE_ARN} \
    --memory-size 256 \
    --timeout 10 \
    ${LAMBDA_META}
}

function cmd_delete() {
  echo Deleting function
  aws lambda delete-function --function-name ${FUNCTION_NAME}
}

function cmd_invoke() {
  echo Invoking function
  aws lambda invoke response.txt \
    --function-name ${FUNCTION_NAME} \
    --payload file://payload.json 
}

function cmd_update() {
  echo Updating function
  aws lambda update-function-code \
    --function-name ${FUNCTION_NAME} \
    --zip-file ${ZIP_FILE}
}

FUNCTION_NAME=VoterFunction
HANDLER=io.quarkus.amazon.lambda.runtime.QuarkusStreamHandler::handleRequest
RUNTIME=java8
ZIP_FILE=fileb://build/voter-lambda-1.0-SNAPSHOT-runner.jar
LAMBDA_ROLE_ARN=arn:aws:iam::408207669206:role/lambda-ex

if [ "$1" == "native" ]
then
  RUNTIME=provided
  ZIP_FILE=fileb://build/function.zip
  FUNCTION_NAME=FooNativeFunction
  LAMBDA_META="--environment Variables={DISABLE_SIGNAL_HANDLERS=true}"
  shift
fi

while [ "$1" ]
do
  eval cmd_${1}
  shift
done

