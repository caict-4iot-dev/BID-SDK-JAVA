package src.demo;

import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.model.result.Result;

public class TestBIDQuery {
    public static void main(String[] args){
        String request = "{\"didDocument\":{\"extension\":{\"acsns\":[\"acsn\"],\"attributes\":[{\"encrypt\":1,\"format\":\"text\",\"value\":\"BID文档\",\"key\":\"name\",\"desc\":\"名称\"}],\"delegateSign\":{\"signatureValue\":\"07E5B21A42B38B89A409A720E6BC1BDAC1ED2BD22F7786682470C8B975A9DC9C23E8DD9AC416712E7DE3026CED7F3866A4073368B01750698CBB0FEF783BC60C\",\"signer\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\"},\"recovery\":[\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-2\"],\"ttl\":86400,\"type\":102,\"verifiableCredentials\":[{\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"type\":201}]},\"created\":\"2022-08-29T19:02:59Z\",\"service\":[{\"protocol\":2,\"serverType\":0,\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#resolver\",\"serviceEndpoint\":\"https://bidresolver.com\",\"type\":\"DIDDecrypt\"}],\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"proof\":{\"creator\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\",\"signatureValue\":\"E7985417835294115373036E0470F9047A343A701ABC84DF4A39EE3380FDB013171F29F6107E91B77999DCAD9ABB4BEB91F5D5BC52CDA50B6A4B856F5D71F609\"},\"publicKey\":[{\"controller\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\",\"publicKeyHex\":\"b0656616cef061d0cae2b3e4fa49694cad29b4eb864ec85f1538ccb2edb926b71d276e\",\"type\":\"ED25519\"}],\"@context\":[\"https://www.w3.org/ns/did/v1\"],\"updated\":\"2022-08-29T19:02:59Z\",\"version\":\"1.0.0\",\"alsoKnownAs\":[{\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"type\":101}],\"authentication\":[\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\"]}}";
        isValidProof(request);

        String bid="did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9";
        bidQueryByContract(bid);

        String hash="c792c6901fdece007786f11cfe17a85b85554938e9ea48ea8c18b05dca20605a";
        queryTransactionInfoByHash(hash);

        String resolverBid="did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9";
        resolverBid(resolverBid);
    }
    static void isValidProof(String request){
        SDK sdk = new SDK();
        Result result = sdk.isValidProof(request);
        System.out.println(result);

    }
    static void bidQueryByContract(String bid){
        SDK sdk = SDK.getInstance("http://test-bif-core.xinghuo.space");
        Result result = sdk.bidQueryByContract(bid);
        System.out.println(result);

    }
    static void queryTransactionInfoByHash(String hash){
        SDK sdk = SDK.getInstance("http://test-bif-core.xinghuo.space");
        Result result = sdk.queryTransactionInfoByHash(hash);
        System.out.println(result);
    }
    static void resolverBid(String resolverBid){
        SDK sdk = SDK.getInstance("http://test-bidresolver.bitfactory.cn");
        try {
            Result result = sdk.resolverBid(resolverBid);
            System.out.println(result);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
