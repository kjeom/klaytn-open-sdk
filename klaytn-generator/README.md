## User customization of typescript axios 
### deploy
```shell
./gradlew clean :typescript-axios:deployJar
./gradlew cleanTest :typescript-axios:test
```

## Test
- Using the kotest
- Using Behavior Spec
- locate an index.html and report files in build/reports/test

## Jar file
- Generate jar include a customized code generator class
- This jar file include openapi-generator-cli-$VERSION.jar
