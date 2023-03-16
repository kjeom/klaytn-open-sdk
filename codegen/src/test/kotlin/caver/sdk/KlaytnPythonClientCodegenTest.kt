/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package caver.sdk

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.openapitools.codegen.CodegenConfig
import java.util.*

class KlaytnPythonClientCodegenTest : BehaviorSpec({
    Given("Klaytn code generator") {
        When("Load by ServiceLoader") {
            Then("should be existed") {
                val loader = ServiceLoader.load(
                    CodegenConfig::class.java,
                    CodegenConfig::class.java.classLoader
                )

                for (config in loader) {
                    println(config.name)
                }
                loader.find { i -> i.name == KlaytnPythonClientCodegen.caverName}?.name.shouldBe(KlaytnPythonClientCodegen.caverName)
            }
        }
        When("Model file name set to KlaytnModel") {
            Then("return 'klaytn-model as camel case") {
                val codeGen = KlaytnPythonClientCodegen()
                codeGen.toModelFilename("KlaytnModel").shouldBe("klaytn-model")
            }
        }
    }
})
