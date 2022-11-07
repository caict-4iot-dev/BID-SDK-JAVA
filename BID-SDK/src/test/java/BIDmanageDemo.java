import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.common.*;
import cn.ac.caict.bid.model.*;
import cn.ac.caict.bid.model.extension.BIDAttributesOperation;
import cn.ac.caict.bid.model.extension.BIDDelegateSignOperation;
import cn.ac.caict.bid.model.extension.BIDExtensionOperation;
import cn.ac.caict.bid.model.extension.BIDVerifiableCredentialsOperation;
import cn.ac.caict.bid.model.request.BIDRequest;
import cn.ac.caict.bid.model.result.Result;
import cn.ac.caict.bid.model.service.BIDServiceOperation;
import cn.bif.common.JsonUtils;
import cn.bif.exception.SDKException;
import cn.bif.model.crypto.KeyPairEntity;
import cn.bif.module.encryption.key.PrivateKeyManager;
import cn.bif.module.encryption.model.KeyType;
import cn.bif.utils.hex.HexFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BIDmanageDemo {
    SDK sdk;

    @Before
    public void createTestSdk() {
        sdk = new SDK();
    }

    @Test
    public void getSdkVersionNumber(){
        String sdkVersion= sdk.getSdkVersion();
        Assert.assertEquals("V1.0.0", sdkVersion);
    }

    @Test
    public void getBIDAndKeyPair()  {
        KeyPairEntity kaypairEntity = sdk.getBidAndKeyPair(KeyType.ED25519);
        String publicKey = kaypairEntity.getEncPublicKey();
        String privateKey = kaypairEntity.getEncPrivateKey();
        String bid = kaypairEntity.getEncAddress();
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        Assert.assertNotNull(bid);
        System.out.println(publicKey);
        System.out.println(privateKey);
        System.out.println(bid);
    }

    @Test
    public void isValidBid() {
        String bid="did:bid:efJgt44mNDewKK1VEN454R17cjso3mSG";
        Result result = sdk.isValidBid(bid);
        System.out.println(result);
        Assert.assertTrue(result.getStatus());
    }
    @Test
    public void getBidByPublicKey() {
        String bid="did:bid:efJgt44mNDewKK1VEN454R17cjso3mSG";
        String publicKey="B06566766FC2A97B74339EB3F662B49410CD56C23D5CC8B31005459AA92B5D4D7D7563";
        String result = sdk.getBidByBifPublicKey(publicKey);
        System.out.println(result);
        Assert.assertEquals(bid,result);
    }
    @Test
    public void getBifPubkeyByPrivateKey() {
        String privateKey = "priSrrqp43q9g2fT99zwPeFZn7vnAwZrzi9697mPitUPqbxDJg";
        String firstPublicKey = sdk.getBifPubkeyByPrivateKey(privateKey);
        System.out.println(firstPublicKey);
        String publicKey = "B07A66041C537BBF90DA47E2B3A08D81524CA3711612D06907720B0800C5BD98A6CCDA709C0E83672E9FEBCAE31AF11F01199C948C6C58AB3FBD84582BC247B680987FEE";
        Assert.assertEquals(sdk.getBifPubkeyByPrivateKey(privateKey).toUpperCase(),publicKey);
    }
    @Test
    public void signAndVerify() throws SDKException {
        String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
        String publicKey = "b065667cc1e4584bc9ddb6806c455bdea9f8390724a77a6ed2f6369c830043418c0745";
        String msg = "1234";
        byte[] sign_static = sdk.signBlob(msg,privateKey);
        Assert.assertTrue(sdk.verifySig(publicKey, msg,sign_static));
    }
    @Test
    public void getBIDTemplate() {
        Result result = sdk.getBIDTemplate();
        System.out.println(result);
    }
    @Test
    public void createBIDByTemplate() {
        String request ="{\"bifamount\":0,\"senderAddress\":\"did:bid:efZfEeQAE1jup1H9musAZP1S3PqV3UdF\",\"feeLimit\":1000000,\"BIFAmount\":0,\"bid\":[{\"document\":{\"version\":\"1.0.0\",\"id\":\"did:bid:efBfxEAHTvaMzApHcp9X6PJZR1HHvk2T\",\"publicKey\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-1\",\"type\":\"ED25519\",\"publicKeyHex\":\"b0656631627656f082b438a747164c2c9abbe5dd72a0582bdbf404e959c133b89b723e\",\"controller\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\"}],\"authentication\":[\"did:bid:efZfEeQAE1jup1H9musAZP1S3PqV3UdF#key-1\"],\"alsoKnownAs\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\",\"type\":101}],\"extension\":{\"recovery\":[\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-2\"],\"ttl\":86400,\"type\":102,\"delegateSign\":{\"signer\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-1\",\"signatureValue\":\"A897845DAD953A68BCF22F31FC7EE2BB316EDA74D0BA5C02D99FF99981E610E50659D65EB2082D30312E91D677E7CFDAF3773155BBEE330E3C67412786E2BD01\"},\"attributes\":[{\"key\":\"name\",\"desc\":\"名称\",\"value\":\"BID文档\",\"format\":\"text\",\"encrypt\":1}],\"acsns\":[\"acsn\"],\"verifiableCredentials\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\",\"type\":201}]},\"service\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#resolver\",\"type\":\"DIDDecrypt\",\"protocol\":2,\"serverType\":0,\"serviceEndpoint\":\"https://bidresolver.com\"}],\"created\":\"2022-09-09T17:37:19Z\",\"updated\":\"2022-09-09T17:37:19Z\",\"proof\":{\"creator\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-1\",\"signatureValue\":\"BCB3818AFC60C2121D88A6E6AF9B4FBD4F36931D27428DD72E149EF37E6E5A413787585E7E52BA32CCEA52AC3EB36664FCDAF8EBD02BC116F8875AFF00E28700\"},\"@context\":[\"https://www.w3.org/ns/did/v1\"]}},{\"document\":{\"version\":\"1.0.0\",\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\",\"publicKey\":[{\"id\":\"did:bid:ef14cTRLGW14xHWmYeXTi9bBgBkCDwE1#key-1\",\"type\":\"ED25519\",\"publicKeyHex\":\"b065668a2dd499847a0e4edec1560e7c10a2366b671a96011461fdcf1455d27e6b5d2a\",\"controller\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\"}],\"authentication\":[\"did:bid:efZfEeQAE1jup1H9musAZP1S3PqV3UdF#key-1\"],\"alsoKnownAs\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\",\"type\":101}],\"extension\":{\"recovery\":[\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-2\"],\"ttl\":86400,\"type\":102,\"delegateSign\":{\"signer\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-1\",\"signatureValue\":\"DD3CCC07EDB286A4AEC2A2A63562A498C8582EDA3CE779425D86FE46D5F6774EF0831E9F03FCAA579882F20F21FCAD6616D62689D360AC8159F4414F112D2F09\"},\"attributes\":[{\"key\":\"name\",\"desc\":\"名称\",\"value\":\"BID文档\",\"format\":\"text\",\"encrypt\":1}],\"acsns\":[\"acsn\"],\"verifiableCredentials\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\",\"type\":201}]},\"service\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#resolver\",\"type\":\"DIDDecrypt\",\"protocol\":2,\"serverType\":0,\"serviceEndpoint\":\"https://bidresolver.com\"}],\"created\":\"2022-09-09T17:45:09Z\",\"updated\":\"2022-09-09T17:45:09Z\",\"proof\":{\"creator\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-1\",\"signatureValue\":\"3A6C2D2601CA3A78EA3C736D3D5E93AFF194BF32CC45CE308AFC47854BDDD72A81776F88798B83D56E2770BFDFDBCC3A250D51CDB63C45E34773ED910C2F770F\"},\"@context\":[\"https://www.w3.org/ns/did/v1\"]}}],\"ceilLedgerSeq\":0,\"remarks\":\"creat DDO\",\"privateKey\":\"priSPKhJ59Y6EePWbFybWzNkhPGjJ1ReQBeFk3KgaC2nFz4Nfy\",\"gasPrice\":1000}";
        SDK sdk = SDK.getInstance("http://test.bifcore.bitfactory.cn/");
        Result result = sdk.createBIDByTemplate(request);
        System.out.println(result);
    }


    @Test
    public void createBID() {
        //请求参数
        String senderAddress = "did:bid:ef7zyvBtyg22NC4qDHwehMJxeqw6Mmrh";
        KeyPairEntity kaypairEntity = sdk.getBidAndKeyPair(KeyType.ED25519);
        String bidId = kaypairEntity.getEncAddress();
        String senderPrivateKey = "priSPKr2dgZTCNj1mGkDYyhyZbCQhEzjQm7aEAnfVaqGmXsW2x";
        Long amount = 0L;
        String remark="create ddo";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date now = new Date();

        // Init request
        List<BID> bidList=new ArrayList<>();
        BID bid=new BID();
        BIDDocumentOperation bidDocumentOperation=new BIDDocumentOperation();
        String context[]=new String[1];
        context[0]="https://www.w3.org/ns/did/v1";
        //1.@context
        bidDocumentOperation.setContext(context);
        //2.version,文档的版本号
        bidDocumentOperation.setVersion("1.0.0");
        //3.id,文档的BID
        bidDocumentOperation.setId(bidId);

        //4.publicKey
        String firstPublicKey = sdk.getBifPubkeyByPrivateKey(senderPrivateKey);
        BIDpublicKeyOperation publicKey[]=new  BIDpublicKeyOperation[1];
        BIDpublicKeyOperation keyOperation=new BIDpublicKeyOperation();
        keyOperation.setId(senderAddress+"#key-1");
        keyOperation.setPublicKeyHex(firstPublicKey.toUpperCase());
        keyOperation.setType(KeyType.ED25519);
        keyOperation.setController(senderAddress);
        publicKey[0]=keyOperation;
        bidDocumentOperation.setPublicKey(publicKey);

        //5. authentication
        String authentication[]=new String[1];
        authentication[0]=senderAddress+"#key-1";
        bidDocumentOperation.setAuthentication(authentication);
        //6.alsoKnownAs
        BIDAlsoKnownAsOperation alsoKnownAsOperation=new BIDAlsoKnownAsOperation();
        //关联标识类型
        alsoKnownAsOperation.setType(RelevanceType.RELEVANCE_BID.getCode());
        alsoKnownAsOperation.setId(senderAddress);
        BIDAlsoKnownAsOperation knownAsOperation[]=new BIDAlsoKnownAsOperation[1];
        knownAsOperation[0]=alsoKnownAsOperation;
        bidDocumentOperation.setAlsoKnownAs(knownAsOperation);

        //7. extension,BID扩展字段
        BIDExtensionOperation bidExtensionOperation=new BIDExtensionOperation();
        //7.1 recovery
        String recovery[]=new String[1];
        recovery[0]=senderAddress+"#key-2";
        bidExtensionOperation.setRecovery(recovery);
        //7.2 ttl
        bidExtensionOperation.setTtl(86400L);
        //7.3delegateSign
        //签名
        byte[] signatureValue = PrivateKeyManager.sign(HexFormat.hexStringToBytes(firstPublicKey), senderPrivateKey);
        BIDDelegateSignOperation bidDelegateSignOperation=new BIDDelegateSignOperation();
        bidDelegateSignOperation.setSigner(senderAddress+"#key-1");
        bidDelegateSignOperation.setSignatureValue(HexFormat.byteToHex(signatureValue));

        bidExtensionOperation.setDelegateSign(bidDelegateSignOperation);
        //7.4 type 属性类型
        bidExtensionOperation.setType(AttributeType.ATTRIBUTE_ENTERPRISE.getCode());
        //7.5 attributes
        BIDAttributesOperation attributes[] =new BIDAttributesOperation[1];
        BIDAttributesOperation attributesOperation=new BIDAttributesOperation();
        attributesOperation.setKey("name");
        attributesOperation.setDesc("名称");
        attributesOperation.setFormat("text");
        attributesOperation.setValue("BID文档");
        attributesOperation.setEncrypt(EncryptType.ENCRYPT_TYPE_NO.getCode());
        attributes[0]=attributesOperation;

        bidExtensionOperation.setAttributes(attributes);

        //7.6 acsns
        String acsns[]=new String[1];
        acsns[0]="acsn";
        bidExtensionOperation.setAcsns(acsns);
        //7.7 verifiableCredentials
        BIDVerifiableCredentialsOperation bidVerifiableCredentialsOperation=new BIDVerifiableCredentialsOperation();
        bidVerifiableCredentialsOperation.setId(senderAddress);
        //凭证类型
        bidVerifiableCredentialsOperation.setType(CredentialsType.Credentials_VERIFIABLE.getCode());
        BIDVerifiableCredentialsOperation verifiableCredentialsOperations[] =new BIDVerifiableCredentialsOperation[1];
        verifiableCredentialsOperations[0]=bidVerifiableCredentialsOperation;
        bidExtensionOperation.setVerifiableCredentials(verifiableCredentialsOperations);
        bidDocumentOperation.setExtension(bidExtensionOperation);

        //8.service
        BIDServiceOperation service[]=new BIDServiceOperation[1];
        BIDServiceOperation serviceOperation=new BIDServiceOperation();
        serviceOperation.setId(senderAddress+"#resolver");
        serviceOperation.setServiceEndpoint("https://bidresolver.com");
        //服务类型
        serviceOperation.setType(DidType.DID_RESOLVER.getCode());
        //解析服务协议类型
        serviceOperation.setProtocol(ResolverType.RESOLVER_HTTP.getCode());
        //服务地址类型,0为域名形式，1为IP地址形式
        serviceOperation.setServerType(AddressType.ADDRESS_DOMAIN.getCode());
        service[0]=serviceOperation;
        bidDocumentOperation.setService(service);
        //9.created,创建时间
        bidDocumentOperation.setCreated(sdf.format(now));
        //10.updated,上次的更新时间
        bidDocumentOperation.setUpdated(sdf.format(now));


        //签名
        String document=JsonUtils.toJSONString(bidDocumentOperation);
        byte[] documentByte = document.getBytes();
        String documentBlob = HexFormat.byteToHex(documentByte);
        byte[] sign_static = PrivateKeyManager.sign(HexFormat.hexStringToBytes(documentBlob), senderPrivateKey);
        // 11.proof
        BIDProofOperstion bidProofOperstion=new BIDProofOperstion();
        bidProofOperstion.setCreator(senderAddress+"#key-1");
        bidProofOperstion.setSignatureValue(HexFormat.byteToHex(sign_static));
        bidDocumentOperation.setProof(bidProofOperstion);

        bid.setDocument(bidDocumentOperation);
        bidList.add(bid);

        BIDRequest request=new BIDRequest();
        request.setSenderAddress(senderAddress);
        request.setPrivateKey(senderPrivateKey);
        request.setBIFAmount(amount);
        request.setRemarks(remark);
        request.setBids(bidList);

        SDK sdk = SDK.getInstance("http://test-bif-core.xinghuo.space");
        Result result = sdk.createBID(request);
        System.out.println(result);
    }


    /**
     * 递归解析bid
     */
    @Test
    public void resolverBid(){
        SDK sdk = SDK.getInstance("http://test-bidresolver.bitfactory.cn");
        String bid="did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9";
        Result result = sdk.resolverBid(bid);
        System.out.println(result);
    }

    /**
     * 对文档内容的签名进行验签
     */
    @Test
    public void isValidProof(){
        String request = "{\"didDocument\":{\"extension\":{\"acsns\":[\"acsn\"],\"attributes\":[{\"encrypt\":1,\"format\":\"text\",\"value\":\"BID文档\",\"key\":\"name\",\"desc\":\"名称\"}],\"delegateSign\":{\"signatureValue\":\"07E5B21A42B38B89A409A720E6BC1BDAC1ED2BD22F7786682470C8B975A9DC9C23E8DD9AC416712E7DE3026CED7F3866A4073368B01750698CBB0FEF783BC60C\",\"signer\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\"},\"recovery\":[\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-2\"],\"ttl\":86400,\"type\":102,\"verifiableCredentials\":[{\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"type\":201}]},\"created\":\"2022-08-29T19:02:59Z\",\"service\":[{\"protocol\":2,\"serverType\":0,\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#resolver\",\"serviceEndpoint\":\"https://bidresolver.com\",\"type\":\"DIDDecrypt\"}],\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"proof\":{\"creator\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\",\"signatureValue\":\"E7985417835294115373036E0470F9047A343A701ABC84DF4A39EE3380FDB013171F29F6107E91B77999DCAD9ABB4BEB91F5D5BC52CDA50B6A4B856F5D71F609\"},\"publicKey\":[{\"controller\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\",\"publicKeyHex\":\"b0656616cef061d0cae2b3e4fa49694cad29b4eb864ec85f1538ccb2edb926b71d276e\",\"type\":\"ED25519\"}],\"@context\":[\"https://www.w3.org/ns/did/v1\"],\"updated\":\"2022-08-29T19:02:59Z\",\"version\":\"1.0.0\",\"alsoKnownAs\":[{\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"type\":101}],\"authentication\":[\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\"]}}";
        Result result = sdk.isValidProof(request);
        System.out.println(result);
    }

    /**
     * BID地址合约查询
     */
    @Test
    public void bidQueryByContract() {
        SDK sdk = SDK.getInstance("http://test-bif-core.xinghuo.space");
        String bid="did:bid:efahXNwuTvJCgJPfKYCmXHb2fLrp33i4";
        Result result = sdk.bidQueryByContract(bid);
        System.out.println(result);
    }

    /**
     * 根据hash查询交易信息
     */
    @Test
    public void queryTransactionInfoByHash(){
        SDK sdk = SDK.getInstance("http://test-bif-core.xinghuo.space");
        String hash="c792c6901fdece007786f11cfe17a85b85554938e9ea48ea8c18b05dca20605a";
        Result result = sdk.queryTransactionInfoByHash(hash);
        System.out.println(result);
    }
    /**
     * 根据hash获取bid标识
     */
    @Test
    public void getBidByHash(){
        SDK sdk = SDK.getInstance("http://test-bif-core.xinghuo.space");
        String hash="6c40f186b702db243766785af4ae125cfeeb116eacb5b01e66a8b6456a128122";
        Result result = sdk.getBidByHash(hash);
        System.out.println(result);
    }
}
