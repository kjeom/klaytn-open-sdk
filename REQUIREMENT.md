# Open SDK

## YAML 과 JSON 중에 오픈 API 정의는 어떤 걸로 하나?

- [x]  YAML로 결정

## API 정의(api, schemas)는 one-source로 관리할까?

- [x]  YAML 파일로 한 곳에서 관리하고 여러개의 SDK언어 지원한다

## YAML API request body는 JSONRPC로 할까?

- [x]  JSONRPC : id, method, jsonrpc, params
- [x]  REST API :
   - [x]  method : getRewards()
   - [x]  params : blockNumber Integer

## Do params use by array in jsonrpc 2.0 request?

- [x]  Array를 인자로 받아서 사용할 것인가?
   - [x]  use the getRewards(params Array[Integer])
- [x]  단일 파라미터를 선언해서 인자로 받고 Array로 변환 할 것인가?
   - [x]  use the getRewards(num Integer)
   - [ ]  send the array params: [integer]


## Do id increase in jsonrpc 2.0 request?

- [x]  auto increment를 사용할 것인가? (Java, Kotlin)
- [x]  고정 값으로 1을 사용할 것인가?

## method 이름은 어떻게 변경을 할 것인가?

- [x]  yaml(json) spec에서는 method 이름을 API마다 고정을 하고 generated code에서 method 사용할 때는 따로 설정을 하지 않으면 좋겠다

## jsonrpc 필드는 2.0으로 고정하나?

- [x]  jsonrpc: 2.0 고정

## generator로 만들어진 API는 jsonrpc request를 hidden 처리 할 수 있나?

- [x]  usage : getRewards(blockNumber Integer)
- [x]  rpc call
   - [x]  id : auto increment
   - [x]  method : set to klay_getRewards automatically
   - [x]  jsonrpc : 2.0
   - [x]  params : array of integer, [ blockNumber ]

## caver-java 구조를 재사용할까?

- [ ]  web3j Request, Response, `Web3jService`사용
- [x]  openapi generator가 생성한 코드를 사용할 것인가?

## generator는 openapi와 swagger 중에 어떤 것을 사용할까?

- [x]  openapi generator : [https://github.com/OpenAPITools/openapi-generator](https://github.com/OpenAPITools/openapi-generator)
- [ ]  swagger codegen : [https://github.com/swagger-api/swagger-codegen](https://github.com/swagger-api/swagger-codegen)

## SDK generator는 어디까지 자동 생성을 할까?

- [x]  API
- [x]  Client
- [x]  Model
- [x]  RPC call

## SDK 지원 언어는?

- [x]  java
- [x]  javascript
- [x]  typescript + axios
- [x]  kotlin + retrofit2
- [ ]  android(kotlin)
- [ ]  go
- [ ]  rust
- [ ]  python
- [ ]  swift

## 자동 생성이 안되는 코드는 어떻게 관리를 할까?

- [ ]  solidity(contract)
- [ ]  ~~wallet~~
- [ ]  ~~account~~
- [ ]  transaction
- [ ]  ~~keystore~~
- [ ]  ~~klaytn specific feature~~
- [ ]  ethereum feature

## 테스트 코드 작성 규칙은 어떻게 할까?

- [ ]  coverage
- [x]  BDD
- [x]  API 추가/변경시에 함께 코딩을 해야 한다

## 문서 관리는 어떻게 할까?

- [x]  RPCJSON
- [x]  SDK API 문서
- [x]  SDK 언어별 사용 예제
- [x]  Online API Server 구성(SwaggerUI)

## Does it need to customize the mustache template?

- [x]  Copy all template files of the specific language and library
- [x]  Patch a snippet codes

## Generators List

[https://openapi-generator.tech/docs/generators/](https://openapi-generator.tech/docs/generators/)

### OpenAPI 문서를 볼 수 있는 공개된 페이지 제공을 위해 서버를 구성해야 하나?

- [ ]  서버 운영
- [x]  github books 이용

### 버전 관리는 yaml 파일 안에서 할까?

- [x]  같은 yaml 파일에서 서버/태그로 버전 분기
- [ ]  새로운 yaml 파일 만들고 전체 복사하기

# TODO

### Klaytn API 모두 정의해 보기

- [ ]  To list

### OpenAPI로 만들 수 없는 API들을 정리하기

- [ ]  To list

### 다양한 언어와 라이브러리로 API를 개발하기

- [ ]  To List

### 모든 API들에 대한 Test Case 리스팅하고, BDD 방식으로 구현은 생략한 요구사항 skeleton을 만들어 보기

- [ ]  To List

### 뉴비 온보딩 프로그램 제안

- [ ]  가장 잘하는 언어로  SDK 만들어 보기
- [ ]  만들어 놓은 모든 API들의 Test Case 시나리오를 제시해 주고, API가 잘 동작하는지 테스트케이스를 만들기
- [ ]  새로운 Klaytn API를 정의하고 SDK에 추가하고 테스트 해보기

# References

### OpenAPI

OpenAPI Getting started : [github.io](https://oai.github.io/Documentation/start-here.html)

OpenAPI specification : [github.io](https://oai.github.io/Documentation/specification.html)

OpenAPI 3.1.0 specification : [github](https://github.com/OAI/OpenAPI-Specification/blob/3.1.0/versions/3.1.0.md#pathsObject)

OpenAPI generator : [github](https://github.com/OpenAPITools/openapi-generator)

### Mustache

Mustache Manual : [github.io](https://mustache.github.io/mustache.5.html)

Mustache Template : [medium](https://github.com/samskivert/jmustache)

Mustache turorial : [tsmean](https://www.tsmean.com/articles/mustache/the-ultimate-mustache-tutorial/)

JMustache : [github](https://github.com/samskivert/jmustache)

### Swagger

Swagger specification : [swagger](https://swagger.io/specification/#oas-version)

Swagger codegen : [github](https://github.com/swagger-api/swagger-codegen)

Swagger editor : [editor](https://editor.swagger.io/)

Swagger codegen handlebar : [notes](https://sites.google.com/site/bingsite/web-development/java-goodies/template-engine/handlebar-mustache?pli=1)

JSON-RPC specification : [specification](https://www.jsonrpc.org/specification)
