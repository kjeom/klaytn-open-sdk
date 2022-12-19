# klaytn-open-sdk

## Download openapi-generator-cli
```shell
$ cd bin
$ sh install-generator.sh
```

## Generate typescript with axios library 
```shell
$ cd sdk/typescript-axios
$ sh typescript-generate.sh
```

## Add API
1. Choose an API tag(group)
    - klay
    - governance
    - eth
    - etc 
1. Add paths
    - choose directory like api/paths/klay, it's up to you which making a new file or using an existed file
      - api/paths/klay/rewards.yaml
    - add REST API paths like /klay/getRewards, it's not a real path in json rpc. It's for a syntactic path, this uri path is not used in json rpc call.

## Setting the typescript-axios
- Use node version more than v12
```shell
$ brew install nvm
$ nvm use v16.13.1
```

### Test
```shell
$ cd sdk/typescript-axios
$ yarn test
```

## Show config help 
```shell
bin/openapi-generator-cli config-help -g typescript-axios
```

## Make a customized code generator
- Directory : klaytn-generator
- Read the klaytn-gernator/README.md
- Build generator cli jar
```shell
$ cd klaytn-generator
$ ./gradlew clean :typescript-axios:deployJar 
```

## Document
### redocly site
[redoc style site](https://henry-will.github.io/klaytn-open-sdk/)
### Swagger UI
[swagger ui for api test](https://henry-will.github.io/klaytn-open-sdk/SwaggerUI/)
