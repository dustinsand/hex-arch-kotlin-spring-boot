# Contributing

## Use a Consistent Coding Style
### Intellij
Uncheck optimize imports: Editor -> General -> Auto Import -> Optimize imports on the fly

Select: Editor -> Code Style -> Kotlin -> Imports -> Use single name import

### Ktlint is used to lint and format the Kotlin code
You can manually invoke the tasks, but know they will be invoked on Git commit.
#### Gradle Tasks
formatKotlin: format Kotlin source code according to ktlint rules or warn when auto-format not possible.

lintKotlin: report Kotlin lint errors and by default fail the build.

## One Time Local Setup
### Add Git hook to execute Ktlint before commit.
Create pre-commit hook, .git/hooks/pre-commit
```
#!/bin/sh
<path to project>/gradlew formatKotlin
if [ $? -ne 0 ]; then exit 1; fi
<path to project>/gradlew lintKotlin
if [ $? -ne 0 ]; then exit 1; fi
```
chmod +x .git/hooks/pre-commit 