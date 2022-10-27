package src.demo;

import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.model.result.Result;


public class TestBIDCreate {
    public static void main(String[] args){
        getBIDTemplate();

        String requestCreateTemplate ="{\"bifamount\":0,\"senderAddress\":\"did:bid:efZfEeQAE1jup1H9musAZP1S3PqV3UdF\",\"feeLimit\":1000000,\"BIFAmount\":0,\"bid\":[{\"document\":{\"version\":\"1.0.0\",\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\",\"publicKey\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-1\",\"type\":\"ED25519\",\"publicKeyHex\":\"b0656631627656f082b438a747164c2c9abbe5dd72a0582bdbf404e959c133b89b723e\",\"controller\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\"}],\"authentication\":[\"did:bid:efZfEeQAE1jup1H9musAZP1S3PqV3UdF#key-1\"],\"alsoKnownAs\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\",\"type\":101}],\"extension\":{\"recovery\":[\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-2\"],\"ttl\":86400,\"type\":102,\"delegateSign\":{\"signer\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-1\",\"signatureValue\":\"A897845DAD953A68BCF22F31FC7EE2BB316EDA74D0BA5C02D99FF99981E610E50659D65EB2082D30312E91D677E7CFDAF3773155BBEE330E3C67412786E2BD01\"},\"attributes\":[{\"key\":\"name\",\"desc\":\"名称\",\"value\":\"BID文档\",\"format\":\"text\",\"encrypt\":1}],\"acsns\":[\"acsn\"],\"verifiableCredentials\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\",\"type\":201}]},\"service\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#resolver\",\"type\":\"DIDDecrypt\",\"protocol\":2,\"serverType\":0,\"serviceEndpoint\":\"https://bidresolver.com\"}],\"created\":\"2022-09-09T17:37:19Z\",\"updated\":\"2022-09-09T17:37:19Z\",\"proof\":{\"creator\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-1\",\"signatureValue\":\"BCB3818AFC60C2121D88A6E6AF9B4FBD4F36931D27428DD72E149EF37E6E5A413787585E7E52BA32CCEA52AC3EB36664FCDAF8EBD02BC116F8875AFF00E28700\"},\"@context\":[\"https://www.w3.org/ns/did/v1\"]}},{\"document\":{\"version\":\"1.0.0\",\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\",\"publicKey\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-1\",\"type\":\"ED25519\",\"publicKeyHex\":\"b065668a2dd499847a0e4edec1560e7c10a2366b671a96011461fdcf1455d27e6b5d2a\",\"controller\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\"}],\"authentication\":[\"did:bid:efZfEeQAE1jup1H9musAZP1S3PqV3UdF#key-1\"],\"alsoKnownAs\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\",\"type\":101}],\"extension\":{\"recovery\":[\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-2\"],\"ttl\":86400,\"type\":102,\"delegateSign\":{\"signer\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-1\",\"signatureValue\":\"DD3CCC07EDB286A4AEC2A2A63562A498C8582EDA3CE779425D86FE46D5F6774EF0831E9F03FCAA579882F20F21FCAD6616D62689D360AC8159F4414F112D2F09\"},\"attributes\":[{\"key\":\"name\",\"desc\":\"名称\",\"value\":\"BID文档\",\"format\":\"text\",\"encrypt\":1}],\"acsns\":[\"acsn\"],\"verifiableCredentials\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\",\"type\":201}]},\"service\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#resolver\",\"type\":\"DIDDecrypt\",\"protocol\":2,\"serverType\":0,\"serviceEndpoint\":\"https://bidresolver.com\"}],\"created\":\"2022-09-09T17:45:09Z\",\"updated\":\"2022-09-09T17:45:09Z\",\"proof\":{\"creator\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-1\",\"signatureValue\":\"3A6C2D2601CA3A78EA3C736D3D5E93AFF194BF32CC45CE308AFC47854BDDD72A81776F88798B83D56E2770BFDFDBCC3A250D51CDB63C45E34773ED910C2F770F\"},\"@context\":[\"https://www.w3.org/ns/did/v1\"]}}],\"ceilLedgerSeq\":0,\"remarks\":\"creat DDO\",\"privateKey\":\"priSPKhJ59Y6EePWbFybWzNkhPGjJ1ReQBeFk3KgaC2nFz4Nfy\",\"gasPrice\":1000}";
        createBIDByTemplate(requestCreateTemplate);

    }
    static void getBIDTemplate(){
        SDK sdk=new SDK();
        Result result = sdk.getBIDTemplate();
        System.out.println(result);
    }
    static void createBIDByTemplate(String request){
        SDK sdk = SDK.getInstance("http://test.bifcore.bitfactory.cn/");
        Result result = sdk.createBIDByTemplate(request);
        System.out.println(result);
    }


}
