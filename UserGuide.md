# User Guide for klaytn Open SDK

# 목적

- Klaytn JSON-RPC를 사용한 다양한 언어로 SDK를 자동 생성

# 디렉토리 설명

## api

- openapi 3.0을 기준으로 yaml로 JSON-RPC API 정의
- [OpenAPI Specification v3.1.0](https://spec.openapis.org/oas/latest.html)
- klay.yaml
    - JSON-RPC API들의 paths 추가
- paths
    - klay.yaml 파일은 paths 디렉토리에 만들어진 파일들을 참고하고, API 정의는 paths 디렉토리 그룹(태그)별로 만든다.
- components
    - requests, response, schemas 에 대한 정보를 정의한다
- code-samples
    - CURL로 실행 가능한 예제를 작성한다
- redocly
    - redoc styles document
    - 모든 API들을 하나의 klaytn-openapi.yaml 파일로 생성
    
    ```jsx
    $ yarn build
    ```
    
    - 생성 파일 위치 : site/klyatn-openapi.yaml

## bin

- install-generator.sh
    - 필요한 버전의 openapi-generator-cli를 다운로드한다
    - 현재는 6.2.1, 6.3.0-SNAPSHOT, 7.0.0-SNAPSHOT을 다운로드 하고 있다
- libs
    - codegen에서 `./gradlew clean :deployJar`을 통해 생성한 caver-openapi-generator-cli.jar 파일이 deploy되는 디렉토리
    - caver-openapi-generator-cli.jar는 각 언어마다 CodeGen을 상속받아 custom하게 생성한 Codegen 파일들이 있다
    - [Custom Codegen](https://www.notion.so/User-Guide-for-klaytn-Open-SDK-00525b67fc234d0ba571550e05d1c472)
- caver-openapi-generator-cli

## codegen

개발 언어별로 OpenApi generator를 커스터마이징 할 수 있습니다.

Custom Codegen을 배포하는 방법은 gradle을 사용합니다.

### Custom Codegen 추가하기

- Kotlin SDK를 개발하는 경우를 가정해서 설명을 합니다.
- Add custom File
    - src/main/kotlin/caver/sdk/KlaytnKotlinClientCodegen.kt
- Extend KotlinClientCodegen class
    
    ```kotlin
    class KlaytnKotlinClientCodegen : KotlinClientCodegen
    ```
    
- Change generator name as `caver-kotlin` getName()을 사용해 generatorName을  찾는다. getName()을 바꾸고 META-INF.services에 class를 등록하면 된다.
- Add META-INF.services resource
    - Edit src/main/resources/META-INF.services
        
        ```kotlin
        caver.sdk.KlaytnKotlinClientCodegen
        ```
        
- Add Test Case
    - Add src/test/kotlin/caver/sdk/KlaytnKotlinClientCodegenTest.kt
    - Add Test for ServiceLoader
    
    ```kotlin
    val loader = ServiceLoader.load(
      CodegenConfig::class.java,
      CodegenConfig::class.java.classLoader
    )
    ```
    

### D**eploy**

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

- 각 개발 언어 별로 SDK를 만든다
- caver prefix를 붙여서 만든다
- 현재 구현된 언어는 client용으로 typescript, kotlin, java, javascript가 있다
- client와 server로 분리한다
- kotlin을 기준으로  SDK 개발하는 방법을 설명한다

### kotlin-generate.sh

[language]-generate.sh 형식의 파일 이름을 만든다

bin/caver-openapi-generator-cli를 실행하기 위한 스크립트 파일이다

실행 결과로 openapi 디렉토리가 생성이 되고, OpenAPI 스팩에 맞게 kotlin용  API와 Model들이 자동 생성이 된다

필요시에 추가로 openapi의 설치/배포 버전 jar 파일 생성할 수 있는 스크립트도 추가를 한다

openapi 설치나 배포를 통해 생성한 jar 파일을 openapi-test에서 사용할 수 있도록 한다

### kotlin-config.yaml

[language]-config.yaml 형식의 이름을 만든다

generator config 파일 설정에 필요한 옵션을 확인하기 

```kotlin
bin/caver-openapi-generator-cli config-help -g kotlin
```

[Kotlin Generator](https://openapi-generator.tech/docs/generators/kotlin) 설명 확인하기

OpenAPI Generators List

[OpenAPI Generator · Generate clients, servers, and documentation from OpenAPI 2.0/3.x documents](https://openapi-generator.tech/docs/generators)

- generatorName
    - kotlin : OpenAPI generator에서 기본으로 제공하는 kotlin client 용 generator이다
    - caver-kotlin : codegen에서 customizing한 KlaytnKotlinClientCodegen의 generator 이름이다.
- outputDir
    - generator를 통해 자동으로 생성해 주는 api와 model들이 생성되는 디렉토리이다
- inputSpec
    - OpenApi 스팩을 정의한 파일
    - site/klaytn-openapi.yaml 파일을 사용한다
- templateDir
    - custom 하게 추가/변경할 mustache 템플릿 파일

### template

[mustache](https://mustache.github.io/mustache.5.html) 템플릿을 사용한다. 추후에는 [Handlebars](https://handlebarsjs.com/)로 바꿀 예정으로 보인다

각 언어마다 OpenAPI generator에서 제공하는 mustache 템플릿이 사용된다.

retrofit2 library를 사용해서 jvm-retorfit2 템플릿 파일들이 필요하다.

- libraries/jvm-retrofit2/api.mustache
    - api.mustache 파일을 추가하면 기존에 있던 api.mustache 파일을 override 한다.
    - [openapi-generator](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator/src/main/resources/kotlin-client/libraries/jvm-retrofit2/api.mustache) 에 있는 api.mustache 파일을 복사해서 수정한다.
    - 
- libraries/jvm-retrofit2/bodyParamJavadoc.mustache
    - bodyParamJavadoc은 새로 추가한 mustache 파일이다
    - bodyParam에 대한 설명을 추가한다
- libraries/jvm-retrofit2/infrastructure/ResponseExt.kt.mustache
    - [openapi-genrator](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator/src/main/resources/kotlin-client/libraries/jvm-retrofit2/infrastructure/ResponseExt.kt.mustache)에 있는 ResponseExt.kt.mustache 파일을 복사해서 수정한다

### .openapi-generator-ignore

- generator로 생성되는 파일들 중에서 제외를 하고 싶은 경우에 gitignore 패턴처럼 작성한다
- kotlin generator에서 생성되는 파일들 중에 `GetRewardsRequestAllOf.kt`파일 생성을 하고 싶지 않으면 **/*AllOf.kt filter out 조건을 추가한다.

### openapi

- generator로 자동 생성된 디렉토리
- custom 코드를 openapi 디렉토리 안에서 만드는 것은 권장하지 않는다.
- generate를 돌릴 때마다 매번 삭제되고 다시 생성하는 것을 추천한다.
- 필요한 utils이나 feature가 있는 경우에는 추가로 module을 만들어서 openapi-test에서 설정하여 test하는 것을 추천한다

### openapi-test

- openapi에서 빌드된 패키지를 이용한다. openapi package는 maven repository에 배포 되었다는 가정하에 jar 파일을 사용하도록 설정을 한다.
- build.gradle.kts

```kotlin
dependencies {
    implementation(files("../openapi/build/libs/caver-kotlin-v1.10.0.jar"))
}
```

- javascript나  typescript도 npm으로 배포된 환경으로 테스트를 하기를 권장한다
- API별로 test 파일을 따로 만든다. OpenAPI spec을 정의할 때에 samples에서 사용한다.

![UserGuide.png](kotlin-test-sample.png)

## site

- klaytn-openapi.yaml
    - [redocly](https://www.notion.so/User-Guide-for-klaytn-Open-SDK-00525b67fc234d0ba571550e05d1c472)를 사용해서 `yarn build` 로 생성한 OpenAPI들을 정의
    - 나눠진 API 파일들을 하나의 Yaml 파일로 생성한다
    - redocly 형식의 API 문서와 SwaggerUI에서 spec으로 사용된다
- index.html
    - redocly 형식의 API 문서를 klaytn-openapi.yaml을 기준으로 제공한다
    - Request samples를 제공해서 원하는 개발 언어의 예제를 볼 수 있다
- SwaggerUI
    - Swagger 스타일의 API 문서를 제공한다
    - SwaggerUI 기능을 제공해서 API들을 직접 웹에서 테스트 가능하다
    - local, baobab, cypress 서버를 선택해서 CURL 스타일의 API를 온라인으로 테스트 가능하다
