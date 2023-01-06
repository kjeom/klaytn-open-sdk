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
