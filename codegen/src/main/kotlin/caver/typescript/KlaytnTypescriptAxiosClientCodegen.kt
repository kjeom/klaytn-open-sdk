package caver.typescript

import org.openapitools.codegen.languages.TypeScriptAxiosClientCodegen

class KlaytnTypescriptAxiosClientCodegen : TypeScriptAxiosClientCodegen {
    companion object {
        val caverName = "caver-typescript-axios"
    }
    constructor() : super() {
        println("Caver Typescript Client")
    }

    override fun getName(): String {
        return caverName
    }
}
