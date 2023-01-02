# caver-kotlin sdk

## Requirements
- download openapi-generator
- check codegen/src/main/kotlin/caver/typescript/KlaytnTypescriptAxiosClientCodegen.kt
- build caver-openapi-generator-cli jar
- check caver-openapi-generator-cli

## Make openapi generator source
```shell
$ cd sdk/client/typescript-axios
$ sh typescript-generate.sh
```
## Test
```shell
nvm use v16.xx.x
yarn test
```
## To see files
### .openapi-generator-ignore
### typescript-config.yaml
### template
- template/apiInner.mustache
- template/model.mustache
### Test file
- openapi-test/klay/getRewards.test.ts
