# User Guide for klaytn Open SDK

# purpose

- Automatically generate SDKs in various languages using Klaytn JSON-RPC

# directory description

## api

- Define JSON-RPC API in yaml based on openapi 3.0
- [OpenAPI Specification v3.1.0](https://spec.openapis.org/oas/latest.html)
- klay.yaml
    - Added paths of JSON-RPC APIs
- paths
    - The klay.yaml file refers to files created in the paths directory, and API definitions are created by groups (tags) in the paths directory.
- components
    - Defines information about requests, responses, and schemas
- code-samples
    - Create executable examples with CURL
- redocly
    - redoc styles document
    - Create all APIs in one klaytn-openapi.yaml file
    
    ```shell
    $yarn build
    ```
    
    - Creation file location: site/klyatn-openapi.yaml

## bin

- install-generator.sh
     - Download the required version of openapi-generator-cli
     - Currently download 6.2.1, 6.3.0-SNAPSHOT, 7.0.0-SNAPSHOT
- libs
     - The directory where the caver-openapi-generator-cli.jar file created through `./gradlew clean :deployJar` in codegen is deployed.
     - caver-openapi-generator-cli.jar inherits CodeGen for each language and contains custom-generated Codegen files
     - [Custom Codegen](https://www.notion.so/User-Guide-for-klaytn-Open-SDK-00525b67fc234d0ba571550e05d1c472)
- caver-openapi-generator-cli

## codegen

OpenApi generator can be customized for each development language.

To deploy Custom Codegen, use gradle.

### Add Custom Codegen

- The description is given assuming that you are developing the Kotlin SDK.
- Add custom file
    - src/main/kotlin/caver/sdk/KlaytnKotlinClientCodegen.kt
- Extend KotlinClientCodegen class
    
    ```kotlin
    class KlaytnKotlinClientCodegen : KotlinClientCodegen
    ```
    
- Change generator name as `caver-kotlin` Use getName() to find the generatorName. Change getName() and register class in META-INF.services.
- Add META-INF.services resource
    - Edit src/main/resources/META-INF.services
        
        ```kotlin
        caver.sdk.KlaytnKotlinClientCodegen
        ```
        
- Add Test Case
    - Add src/test/kotlin/caver/sdk/KlaytnKotlinClientCodegenTest.kt
    - Add Test for ServiceLoader
    
    ```kotlin
    val loader = ServiceLoader. load(
       CodegenConfig::class.java,
       CodegenConfig::class.java.classLoader
    )
    ```
    

### Deploy

```
./gradlew clean :deployJar
./gradlew cleanTest :test
```

### **Test**

- Using the kotest
- Using Behavior Spec
- Locate an index.html and report files in build/reports/test

### **Jar file**

- Generate jar include a customized code generator class
- This jar file include openapi-generator-cli.jar
- Generate a caver-openapi-generator-cli.jar and locate in bin/libs directory

## SDK

- Create SDK for each development language
- Created by attaching caver prefix
- Currently implemented languages include typescript, kotlin, java, and javascript for clients.
- Separation into client and server
- Describes how to develop an SDK based on kotlin

### kotlin-generate.sh

Create a file name in the format [language]-generate.sh

This is a script file to run bin/caver-openapi-generator-cli.

As a result of the execution, an openapi directory is created, and APIs and models for kotlin are automatically created according to the OpenAPI specification.

If necessary, add a script that can create an additional jar file for installation/distribution of openapi.

Make the jar file created through openapi installation or distribution usable in openapi-test

### kotlin-config.yaml

Create a name of the form [language]-config.yaml

Check the options needed to set up the generator config file

```shell
bin/caver-openapi-generator-cli config-help -g kotlin
```

Check [Kotlin Generator](https://openapi-generator.tech/docs/generators/kotlin)

OpenAPI Generators List

[OpenAPI Generator · Generate clients, servers, and documentation from OpenAPI 2.0/3.x documents](https://openapi-generator.tech/docs/generators)

-generatorName
    - kotlin : It is a generator for kotlin client provided by default in OpenAPI generator.
    - caver-kotlin: The generator name of KlaytnKotlinClientCodegen customized in codegen.
-outputDir
    - This is a directory where APIs and models that are automatically created through the generator are created.
-inputSpec
    - File defining OpenApi specification
    - Use site/klaytn-openapi.yaml file
-templateDir
    - mustache template file to add/change custom

### template

Use the [mustache](https://mustache.github.io/mustache.5.html) template. It seems that it will be changed to [Handlebars](https://handlebarsjs.com/) in the future.

For each language, the mustache template provided by the OpenAPI generator is used.

You need the jvm-retorfit2 template files to use the retrofit2 library.

- libraries/jvm-retrofit2/api.mustache
    - Adding the api.mustache file overrides the existing api.mustache file.
    - [openapi-generator](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator/src/main/resources/kotlin-client/libraries/jvm-retrofit2/api.mustache ) copy and modify the api.mustache file in .
- libraries/jvm-retrofit2/bodyParamJavadoc.mustache
    - bodyParamJavadoc is the newly added mustache file
    - Add description of bodyParam
- libraries/jvm-retrofit2/infrastructure/ResponseExt.kt.mustache
    - [openapi-generator](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator/src/main/resources/kotlin-client/libraries/jvm-retrofit2/infrastructure/ResponseExt Copy and modify the ResponseExt.kt.mustache file in .kt.mustache)

### .openapi-generator-ignore

- Write like a gitignore pattern in case you want to exclude among the files created by the generator
- If you do not want to generate `GetRewardsRequestAllOf.kt` file among the files generated by kotlin generator, add **/*AllOf.kt filter out condition.

### openapi

- Directory automatically created by generator
- It is not recommended to create custom code in the openapi directory.
- It is recommended to delete and re-create each time you run generate.
- If there are necessary utils or features, it is recommended to create an additional module and test it by setting it in openapi-test

### openapi-test

- Use the package built in openapi. Assuming that the openapi package is distributed in the maven repository, configure the jar file to be used.
- build.gradle.kts

```kotlin
dependencies {
     implementation(files("../openapi/build/libs/caver-kotlin-v1.10.0.jar"))
}
```

- It is recommended to test javascript or typescript in an environment distributed with npm
- Create separate test files for each API. Used in samples when defining OpenAPI spec.

![UserGuide.png](kotlin-test-sample.png)

## site

- klaytn-openapi.yaml
     - Define OpenAPIs created with `yarn build` using [redocly](https://www.notion.so/User-Guide-for-klaytn-Open-SDK-00525b67fc234d0ba571550e05d1c472)
     - Create divided API files as one Yaml file
     - Used as spec in redocly format API documentation and SwaggerUI
- index.html
     - API documentation in redocly format is provided based on klaytn-openapi.yaml
     - By providing Request samples, you can see examples of the development language you want.
- SwaggerUI
     - Provides Swagger-style API documentation
     - APIs can be directly tested on the web by providing SwaggerUI function
     - You can test CURL-style APIs online by selecting local, baobab, or cypress servers.