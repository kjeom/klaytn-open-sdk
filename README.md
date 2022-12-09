# klaytn-open-sdk

## Download openapi-generator-cli
```shell
$ cd bin
$ sh install-generator.sh
```

## Generate typescript with axios library 
```shell
$ cd typescript-axios
$ sh generate-typescript.sh
```

## Add API
1. Choose an API tag(group)
    - klay
    - governance
    - eth
    - etc 
1. Add paths
    - choose directory like /klay/getRewards, it's up to you which making a new file or using an existed file
      - ./api/klay/rewards.yaml
    - add REST API paths like /klay/getRewards, it's not a real path in json rpc. It's for a syntactic path, this uri path is not used in json rpc call.

## Setting the typescript-axios
- Use node version more than v12
```shell
$ brew install nvm
$ nvm use v16.13.1
```

### Test
```shell
$ cd typescript-axios
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
