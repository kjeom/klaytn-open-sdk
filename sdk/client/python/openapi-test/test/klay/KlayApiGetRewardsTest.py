# coding: utf-8
import unittest
from caver_python.configuration import Configuration
from caver_python.api_client import ApiClient
from caver_python.apis.tags.klay_api import KlayApi

class TestKlayGetRewards(unittest.TestCase):
    host="http://localhost:8551"
    # host="https://api.baobab.klaytn.net:8651"
    klay = KlayApi(ApiClient(configuration=Configuration(host=host)))
    
    def test_post(self):
        result = self.klay.get_rewards(1)
        print(result)

if __name__ == '__main__':
    unittest.main()
