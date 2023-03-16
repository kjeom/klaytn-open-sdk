package caver.sdk.apis

import caver.sdk.models.GetRewardsRequest
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService;

class KlaytnApiGetRewardsTest : BehaviorSpec({
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    Given("In Local Klaytn network") {
        When("Call KlayApiService.getRewards") {
            klaytnWeb3j = Web3j(HttpService("http://127.0.0.1:8551"));
            Then("Get getRewards by number") {
                val rewards = klaytnWeb3j.getRewards(BigDecimal(1)).send().getResult()
                rewards.shouldNotBe(null)
                rewards!!.result!!.rewards.shouldNotBe(null)
            }
        }
    }
})
