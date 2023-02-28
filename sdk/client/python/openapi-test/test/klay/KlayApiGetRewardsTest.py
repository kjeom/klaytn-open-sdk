# coding: utf-8
import unittest
from caver_python.configuration import Configuration
from caver_python.api_client import ApiClient
from caver_python.apis.tags.klay_api import KlayApi

class TestKlayGetRewards(unittest.TestCase):
    _configuration = Configuration(host="http://localhost:8551")
    klay = KlayApi(ApiClient(configuration=_configuration))
    
    def test_post(self):
        get_rewards_request = {
            "method": "klay_getRewards",
            "id": 1,
            "jsonrpc": "2.0",
            "params": [
                0
            ]
        }
        result = self.klay.get_rewards(body=get_rewards_request)

if __name__ == '__main__':
    unittest.main()
