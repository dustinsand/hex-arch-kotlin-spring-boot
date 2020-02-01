# Contributing

## Use a Consistent Coding Style
### Intellij
Uncheck optimize imports: Editor -> General -> Auto Import -> Optimize imports on the fly

### Ktlint is used to lint and format the Kotlin code
#### Gradle Tasks
formatKotlin: format Kotlin source code according to ktlint rules or warn when auto-format not possible.

lintKotlin: report Kotlin lint errors and by default fail the build.