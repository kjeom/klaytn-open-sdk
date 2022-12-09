import {
    AccountCreatedBlockNumberOrHashParameterOneOf,
    Configuration,
    KlayAccountApi,
} from "../api/klay";

const configuration = new Configuration({
    basePath: 'http://localhost:7151',
});

/*
accountCreatedRequest = {
            method: 'klay_accountCreated',
            id: 1,
            jsonrpc: '2.0',
            params: [address, blockNumberOrHash]
        }
 */
const api = new KlayAccountApi(configuration);

describe('testing index file', () => {
    test('empty string should result in zero', async () => {
        const receipt = await api.accountCreated(
            "0xa4f42d4d2a3a13874406435500950c9bf2d783db",
            AccountCreatedBlockNumberOrHashParameterOneOf.Latest
        );
        console.log(receipt.data);
    })
});
