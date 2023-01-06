## api directory

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

    ```shell
    $ yarn build
    ```

    - 생성 파일 위치 : site/klyatn-openapi.yaml

### Add API
#### Choose an API tag(group)
- klay
- governance
- eth
- etc
#### Add paths
- choose directory like api/paths/klay, it's up to you which making a new file or using an existed file
  - api/paths/klay/rewards.yaml
- add REST API paths like /klay/getRewards, it's not a real path in json rpc. It's for a syntactic path, this uri path is not used in json rpc call.
