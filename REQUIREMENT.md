# Open SDK

## YAML 과 JSON 중에 오픈 API 정의는 어떤 걸로 하나?

- [x]  YAML로 결정

## API 정의(api, schemas)는 one-source로 관리할까?

- [x]  YAML 파일로 한 곳에서 관리하고 여러개의 SDK언어 지원한다

## YAML API request body는 JSONRPC로 할까?

- [x]  JSONRPC : id, method, jsonrpc, params
- [ ]  REST API :
    - [ ]  method : getRewards()
    - [ ]  params : blockNumber Integer

## Do params use by array in jsonrpc 2.0 request?

- [ ]  Array를 인자로 받아서 사용할 것인가?
    - [ ]  use the getRewards(params Array[Integer])
- [ ]  단일 파라미터를 선언해서 인자로 받고 Array로 변환 할 것인가?
    - [ ]  use the getRewards(num Integer)
    - [ ]  send the array params: [integer]
    

## Do id increase in jsonrpc 2.0 request?

- [ ]  auto increment를 사용할 것인가?
- [x]  고정 값으로 1을 사용할 것인가?

## method 이름은 어떻게 변경을 할 것인가?

- [x]  yaml(json) spec에서는 method 이름을 API마다 고정을 하고 generated code에서 method 사용할 때는 따로 설정을 하지 않으면 좋겠다

## jsonrpc 필드는 2.0으로 고정하나?

- [x]  jsonrpc: 2.0 고정

## generator로 만들어진 API는 jsonrpc request를 hidden 처리 할 수 있나?

- [ ]  usage : getRewards(blockNumber Integer)
- [ ]  rpc call
    - [ ]  id : auto increment
    - [ ]  method : set to klay_getRewards automatically
    - [ ]  jsonrpc : 2.0
    - [ ]  params : array of integer, [ blockNumber ]

## caver-java 구조를 재사용할까?

- [ ]  web3j Request, Response, `Web3jService`사용
- [x]  openapi generator가 생성한 코드를 사용할 것인가?

## caver-js 구조를 재사용할까?

- [x]  openapi generator가 생성한 코드를 사용할 것인가?

## generator는 openapi와 swagger 중에 어떤 것을 사용할까?

- [x]  openapi generator : [https://github.com/OpenAPITools/openapi-generator](https://github.com/OpenAPITools/openapi-generator)
- [ ]  swagger codegen : [https://github.com/swagger-api/swagger-codegen](https://github.com/swagger-api/swagger-codegen)

## SDK generator는 어디까지 자동 생성을 할까?

- [ ]  API
- [ ]  Client
- [ ]  RPC call

## SDK 지원 언어는?

- [x]  java
- [x]  javascript
- [x]  typescript + axios
- [x]  kotlin server + ratrofit
- [x]  android(kotlin)
- [x]  go
- [x]  rust
- [x]  python
- [x]  swift

## 자동 생성이 안되는 코드는 어떻게 관리를 할까?

- [ ]  solidity
- [ ]  wallet
- [ ]  account
- [ ]  transaction
- [ ]  keystore

## 테스트 코드 작성 규칙은 어떻게 할까?

- [ ]  coverage
- [ ]  API 추가/변경시에 함께 코딩을 해야 한다

## 문서 관리는 어떻게 할까?

- [ ]  RPCJSON
- [ ]  SDK API 문서
- [ ]  SDK 언어별 사용 예제
- [ ]  Online API Server 구성

## Generators List

[https://openapi-generator.tech/docs/generators/](https://openapi-generator.tech/docs/generators/)