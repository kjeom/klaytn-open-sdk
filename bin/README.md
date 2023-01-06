## bin

### install-generator.sh
    - 필요한 버전의 openapi-generator-cli를 다운로드한다
    - 현재는 6.2.1, 6.3.0-SNAPSHOT, 7.0.0-SNAPSHOT을 다운로드 하고 있다
### Download openapi-generator-cli
```shell
$ cd bin
$ sh install-generator.sh
```

### libs
- codegen에서 `./gradlew clean :deployJar`을 통해 생성한 caver-openapi-generator-cli.jar 파일이 deploy되는 디렉토리
- caver-openapi-generator-cli.jar는 각 언어마다 CodeGen을 상속받아 custom하게 생성한 Codegen 파일들이 있다
- [Custom Codegen](https://www.notion.so/User-Guide-for-klaytn-Open-SDK-00525b67fc234d0ba571550e05d1c472)
### caver-openapi-generator-cli
- `openapi-generator-cli-6.2.1.jar` 파일을 실행하는 스크립트
- -Xms512M, -Xmx1024M 옵션을 추가로 JAVA JVM에 주고 있다.
