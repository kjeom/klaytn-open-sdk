import {Configuration, GetRewardsRequest, KlayApi} from "../openapi/klay";

const configuration = new Configuration({
    basePath: 'http://localhost:7151',
});

const api = new KlayApi(configuration);

describe('testing index file', () => {
    test('empty string should result in zero', async () => {
        const receipt = await api.getRewards(2126704);
        console.log(receipt.data);
    });
});
