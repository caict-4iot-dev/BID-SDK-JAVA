# BID-SDK开发指南

### 基本概念介绍：  
BID开发工具包，主要是为了方便开发者可以快速加入到星火主链的生态建设中，有以下功能：  

- 获取版本号：获取BID-SDK版本号。
- BID工具：生成BID标识和验证BID地址格式的合法性。  
- 公私钥工具：生成星火格式的公私钥、使用星火格式的私钥生成签名、使用星火格式的公钥生成签名。    
- BID标识工具：创建BID标识模板、创建BID标识、查询BID标识、校验BID标识。     

### BID-SDK与BID版本对应关系说明
> |BIF-Core-SDK版本|BID版本                              |
> | :-------- | :--------|
> |1.0.2  |1.0.0 |

### 快速使用
- jar包获取：下载开源项目BIF-Core-SDK，导出jar包，并从该项目中获取依赖jar包。
- 环境要求：jdk1.8或以上。


### 开发指南
#### 1. 获取SDK版本号
- 函数体
> getSdkVersion


- 输入参数
> 无


- 返回值
>
> |类型|说明                              |
> | :-------- | :--------|
> |String   |SDK版本号 |


- 接口示例
```java
public class TestGetSdkVersion {
    public static void main(String[] args){
        //创建SDK实例
        SDK sdk = new SDK();
        String sdkVersion= sdk.getSdkVersion();
        System.out.println(sdkVersion);
    }
}
```
#### 2. 生成BID地址和公私钥对

- 函数体

> getBidAndKeyPair


- 返回值

> | 类型          | 说明                                             |
> | :------------ | :----------------------------------------------- |
> | KeyPairEntity | KeyPairEntity对象，属性包含bid和星火格式的公私钥 |


- 接口示例

```java
import cn.ac.caict.bid.SDK;
import cn.bif.exception.SDKException;
import cn.bif.model.crypto.KeyPairEntity;
import cn.bif.module.encryption.model.KeyType;

public class TestGetBidAndKeyPair {
        public static void main(String[] args){
            testGetBidAndKeyPair();
        }
    
        static void testGetBidAndKeyPair(){
            SDK bidSdk = new SDK();
            try {
                KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair();
                String publicKey = kaypairEntity.getEncPublicKey();
                String privateKey = kaypairEntity.getEncPrivateKey();
                String bid = kaypairEntity.getEncAddress();
                System.out.println(publicKey);
                System.out.println(privateKey);
                System.out.println(bid);
            }catch (SDKException e) {
                System.out.println(e.getMessage());
            }
        }
}

```

#### 3. 根据编码类型生成BID地址和公私钥对

- 函数体
> getBidAndKeyPair


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|KeyType    |false    |String| KeyType                   |


- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|KeyPairEntity   |KeyPairEntity对象，属性包含bid和星火格式的公私钥|


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.bif.exception.SDKException;
import cn.bif.model.crypto.KeyPairEntity;
import cn.bif.module.encryption.model.KeyType;

public class TestGetBidAndKeyPair {
        public static void main(String[] args){      
            testGetBidAndKeyPairByKeyType();      
        }

        static void testGetBidAndKeyPairByKeyType(){
            SDK bidSdk = new SDK();
            try {
                KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair(KeyType.ED25519);
                String publicKey = kaypairEntity.getEncPublicKey();
                String privateKey = kaypairEntity.getEncPrivateKey();
                String bid = kaypairEntity.getEncAddress();
                System.out.println(publicKey);
                System.out.println(privateKey);
                System.out.println(bid);
            }catch (SDKException e) {
                System.out.println(e.getMessage());
            }

        }

}
```

#### 4. 根据编解码类型和ChainCode生成BID地址和公私钥对

- 函数体
> getBidAndKeyPair


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|chaincode    |true    |String| chaincode                          |
>|KeyType    |true    |String| KeyType                   |


- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|KeyPairEntity   |KeyPairEntity对象，属性包含bid和星火格式的公私钥|


- 接口示例
```java

import cn.ac.caict.bid.SDK;
import cn.bif.exception.SDKException;
import cn.bif.model.crypto.KeyPairEntity;
import cn.bif.module.encryption.model.KeyType;

public class TestGetBidAndKeyPair {
        public static void main(String[] args){
            testGetBidAndKeyPairByKeyTypeAndChainCode();        
        }

        static void testGetBidAndKeyPairByKeyTypeAndChainCode(){
            SDK bidSdk = new SDK();
            try {
                KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair(KeyType.SM2,"aa1c" );
                String publicKey = kaypairEntity.getEncPublicKey();
                String privateKey = kaypairEntity.getEncPrivateKey();
                String bid = kaypairEntity.getEncAddress();
                System.out.println(publicKey);
                System.out.println(privateKey);
                System.out.println(bid);
            } catch (SDKException e) {
                System.out.println(e.getMessage());
            }
        }
      
}
```

#### 5. 根据ChainCode生成BID地址和公私钥对

- 函数体

> getBidAndKeyPair


- 输入参数

>| 参数      | 必选 | 类型   | 说明      |
>| :-------- | :--- | :----- | :-------- |
>| chaincode | true | String | chaincode |


- 返回值

> | 类型          | 说明                                             |
> | :------------ | :----------------------------------------------- |
> | KeyPairEntity | KeyPairEntity对象，属性包含bid和星火格式的公私钥 |


- 接口示例

```java
import cn.ac.caict.bid.SDK;
import cn.bif.exception.SDKException;
import cn.bif.model.crypto.KeyPairEntity;
import cn.bif.module.encryption.model.KeyType;

public class TestGetBidAndKeyPair {
        public static void main(String[] args){        
            testGetBidAndKeyPairByChainCode();         
        }
        static void testGetBidAndKeyPairByChainCode(){
            SDK bidSdk = new SDK();
            try {
                KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair("aa1c");
                String publicKey = kaypairEntity.getEncPublicKey();
                String privateKey = kaypairEntity.getEncPrivateKey();
                String bid = kaypairEntity.getEncAddress();
                System.out.println(publicKey);
                System.out.println(privateKey);
                System.out.println(bid);
            }catch (SDKException e) {
                System.out.println(e.getMessage());
            }
        }
}
```

#### 6. 根据用户公钥生成BID地址

- 函数体
> getBidByBifPubkey


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|publicKey    |true    |String|星火格式的公钥                          |


- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|String   |bid|


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.bif.exception.SDKException;
public class TestGetBidByPubkey {
        public static void main(String[] args){
            String publicKey = "b065667cc1e4584bc9ddb6806c455bdea9f8390724a77a6ed2f6369c830043418c0745";
            SDK bidSdk = new SDK();
            try {
                String bid = bidSdk.getBidByBifPubkey(publicKey);
                System.out.println(bid);
            }catch (SDKException e){
                System.out.println(e.getMessage());
            }
        }
}
```


#### 7. 根据私钥生成公钥

- 函数体
> getBifPubkeyByPrivateKey


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|privateKey    |true    |String| 星火格式的私钥                          |


- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|publicKey   |星火格式的公钥|


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.bif.exception.SDKException;

public class TestGetPubkeyByPrikey {
        public static void main(String[] args){
            String privateKey = "priSrrqp43q9g2fT99zwPeFZn7vnAwZrzi9697mPitUPqbxDJg";
            SDK sdk = new SDK();
            try {
                String publicKey = sdk.getBifPubkeyByPrivateKey(privateKey);
                System.out.println(publicKey);
            }catch (SDKException e){
                System.out.println(e.getMessage());
            }
        }
}
```

#### 8. 签名Blob消息

- 函数体

> signBlob


- 输入参数

>| 参数       | 必选 | 类型   | 说明           |
>| :--------- | :--- | :----- | :------------- |
>| privateKey | true | String | 星火格式的私钥 |
>| msg        | true | byte[] | 原消息         |


- 返回值

> | 类型   | 说明             |
> | :----- | :--------------- |
> | String | 返回签名后的消息 |


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.bif.exception.SDKException;
import cn.bif.utils.hex.HexFormat;

public class TestSigBlob {
        public static void main(String[] args){
            SDK bidSdk = new SDK();
            String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
            String msg = "0a2e61303032643833343562383964633334613537353734656234393736333566663132356133373939666537376236100122b90108012ab4010a2e61303032663836366337663431356537313934613932363131386363353565346365393939656232396231363461123a123866756e6374696f6e206d61696e28696e707574537472297b0a202f2ae8bf99e698afe59088e7baa6e585a5e58fa3e587bde695b02a2f207d1a06080a1a020807223e0a0568656c6c6f1235e8bf99e698afe5889be5bbbae8b4a6e58fb7e79a84e8bf87e7a88be4b8ade8aebee7bdaee79a84e4b880e4b8aa6d65746164617461";
            try {
                String result = HexFormat.byteToHex(bidSdk.signBlob(msg,privateKey));
                System.out.println(result);
            }catch (SDKException e){
                System.out.println(e.getMessage());
            }
        }
}
```

#### 9. 验证Blob消息签名
用来验证星火链上的签名信息,星火链的签名信息byte[]经过16进制格式化后存储，因此在验签之前需要先转回byte[]。
- 函数体
> verifySig


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|pulicKey    |true    |String|星火格式的公钥                          |
>|msg    |true    |String   |原消息|
>|signature |true |byte[]  |签名消息|


- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|boolean   |验签成功返回true,失败返回false |


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.bif.exception.SDKException;
import cn.bif.utils.hex.HexFormat;

public class TestVerifyBlob {
        public static void main(String[] args){
            SDK bidSdk = new SDK();
            String publicKey = "B065667CC1E4584BC9DDB6806C455BDEA9F8390724A77A6ED2F6369C830043418C0745";
            String msg = "0a2e61303032643833343562383964633334613537353734656234393736333566663132356133373939666537376236100122b90108012ab4010a2e61303032663836366337663431356537313934613932363131386363353565346365393939656232396231363461123a123866756e6374696f6e206d61696e28696e707574537472297b0a202f2ae8bf99e698afe59088e7baa6e585a5e58fa3e587bde695b02a2f207d1a06080a1a020807223e0a0568656c6c6f1235e8bf99e698afe5889be5bbbae8b4a6e58fb7e79a84e8bf87e7a88be4b8ade8aebee7bdaee79a84e4b880e4b8aa6d65746164617461";
            String sig = "8055422ADDC330ECAC1B328E5DFFE79A7CB51FDC111A8D10DAE1AE93297D87E8D7F0D4210B29546ACDE2DE9CB1A6FDAC15BB73E0B8C590F7DB874989C626FB07";
            boolean result = false;
            try {
                result = bidSdk.verifySig(publicKey,msg, HexFormat.hexToByte(sig));
                System.out.println("verify-result: " + result);
            }catch (IllegalArgumentException e) {
                System.out.println("verify-result: " + result);
            }catch (SDKException e){
                System.out.println(e.getMessage());
            }
        }
}
```


#### 10. 验证BID格式是否合法
- 函数体
> isValidBid


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|bid    |true    |String|BID地址                          |


- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|boolean   |验证结果|


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.model.result.Result;
import cn.bif.exception.SDKException;

public class TestIsBidVaild {
        public static void main(String[] args){
            SDK bidSdk = new SDK();
            String bid = "did:bid:efJgt44mNDewKK1VEN454R17cjso3mSG";
            try {
                Result result = bidSdk.isValidBid(bid);
                System.out.println(result);
            } catch (SDKException e) {
                System.out.println(e.getMessage());
            }
        }
}
```

#### 11. 获取BID标识模板

- 函数体

> getBIDTemplate


- 返回值

> | 参数                  | 类型         | 说明                                                         |
> | --------------------- | :----------- | :----------------------------------------------------------- |
> | status                | Boolean      | 成功true,失败false                                           |
> | message               | String       | 返回JSON结果信息                                             |
> | message.bifamount     | Long         | 转账金额                                                     |
> | message.senderAddress | String       | 地址                                                         |
> | message.privateKey    | String       | 私钥                                                         |
> | message.remarks       | String       | 备注                                                         |
> | message.bids          | List<Object> | List集合                                                     |
> | message.feeLimit      | Long         | 交易花费的手续费，单位是星火萤(glowstone)，默认1000000L      |
> | message.gasPrice      | Long         | 打包费用 ，单位是星火萤(glowstone)，默认100L                 |
> | message.ceilLedgerSeq | Long         | 区块高度限制, 如果大于0，则交易只有在该区块高度之前（包括该高度）才有效 |


- 接口示例

```java
    @Test
    public void getBIDTemplate() {
        Result result = sdk.getBIDTemplate();
        System.out.println(result);
    }
```

#### 12. 通过模板创建BID标识

- 函数体

> createBIDByTemplate


- 输入参数

>| 参数    | 必选 | 类型   | 说明     |
>| :------ | :--- | :----- | :------- |
>| request | true | String | 文档内容 |


- 返回值

> | 参数         | 类型    | 说明               |
> | ------------ | :------ | :----------------- |
> | status       | Boolean | 成功true,失败false |
> | message      | String  | 返回结果信息       |
> | message.hash | String  | 返回交易hash       |


- 接口示例

```java
   @Test
    public void createBIDByTemplate() {
        String request ="{\"bifamount\":0,\"senderAddress\":\"did:bid:efZfEeQAE1jup1H9musAZP1S3PqV3UdF\",\"feeLimit\":1000000,\"BIFAmount\":0,\"bid\":[{\"document\":{\"version\":\"1.0.0\",\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\",\"publicKey\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-1\",\"type\":\"ED25519\",\"publicKeyHex\":\"b0656631627656f082b438a747164c2c9abbe5dd72a0582bdbf404e959c133b89b723e\",\"controller\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\"}],\"authentication\":[\"did:bid:efZfEeQAE1jup1H9musAZP1S3PqV3UdF#key-1\"],\"alsoKnownAs\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\",\"type\":101}],\"extension\":{\"recovery\":[\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-2\"],\"ttl\":86400,\"type\":102,\"delegateSign\":{\"signer\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-1\",\"signatureValue\":\"A897845DAD953A68BCF22F31FC7EE2BB316EDA74D0BA5C02D99FF99981E610E50659D65EB2082D30312E91D677E7CFDAF3773155BBEE330E3C67412786E2BD01\"},\"attributes\":[{\"key\":\"name\",\"desc\":\"名称\",\"value\":\"BID文档\",\"format\":\"text\",\"encrypt\":1}],\"acsns\":[\"acsn\"],\"verifiableCredentials\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH\",\"type\":201}]},\"service\":[{\"id\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#resolver\",\"type\":\"DIDDecrypt\",\"protocol\":2,\"serverType\":0,\"serviceEndpoint\":\"https://bidresolver.com\"}],\"created\":\"2022-09-09T17:37:19Z\",\"updated\":\"2022-09-09T17:37:19Z\",\"proof\":{\"creator\":\"did:bid:efWH8wDnogNijNJWiaWJcZ33QSEF9beH#key-1\",\"signatureValue\":\"BCB3818AFC60C2121D88A6E6AF9B4FBD4F36931D27428DD72E149EF37E6E5A413787585E7E52BA32CCEA52AC3EB36664FCDAF8EBD02BC116F8875AFF00E28700\"},\"@context\":[\"https://www.w3.org/ns/did/v1\"]}},{\"document\":{\"version\":\"1.0.0\",\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\",\"publicKey\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-1\",\"type\":\"ED25519\",\"publicKeyHex\":\"b065668a2dd499847a0e4edec1560e7c10a2366b671a96011461fdcf1455d27e6b5d2a\",\"controller\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\"}],\"authentication\":[\"did:bid:efZfEeQAE1jup1H9musAZP1S3PqV3UdF#key-1\"],\"alsoKnownAs\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\",\"type\":101}],\"extension\":{\"recovery\":[\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-2\"],\"ttl\":86400,\"type\":102,\"delegateSign\":{\"signer\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-1\",\"signatureValue\":\"DD3CCC07EDB286A4AEC2A2A63562A498C8582EDA3CE779425D86FE46D5F6774EF0831E9F03FCAA579882F20F21FCAD6616D62689D360AC8159F4414F112D2F09\"},\"attributes\":[{\"key\":\"name\",\"desc\":\"名称\",\"value\":\"BID文档\",\"format\":\"text\",\"encrypt\":1}],\"acsns\":[\"acsn\"],\"verifiableCredentials\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7\",\"type\":201}]},\"service\":[{\"id\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#resolver\",\"type\":\"DIDDecrypt\",\"protocol\":2,\"serverType\":0,\"serviceEndpoint\":\"https://bidresolver.com\"}],\"created\":\"2022-09-09T17:45:09Z\",\"updated\":\"2022-09-09T17:45:09Z\",\"proof\":{\"creator\":\"did:bid:efgeUN1NrZ8g8emyaxMrpH6yKZvTfar7#key-1\",\"signatureValue\":\"3A6C2D2601CA3A78EA3C736D3D5E93AFF194BF32CC45CE308AFC47854BDDD72A81776F88798B83D56E2770BFDFDBCC3A250D51CDB63C45E34773ED910C2F770F\"},\"@context\":[\"https://www.w3.org/ns/did/v1\"]}}],\"ceilLedgerSeq\":0,\"remarks\":\"creat DDO\",\"privateKey\":\"priSPKhJ59Y6EePWbFybWzNkhPGjJ1ReQBeFk3KgaC2nFz4Nfy\",\"gasPrice\":1000}";
        SDK sdk = SDK.getInstance("http://test.bifcore.bitfactory.cn/");
        Result result = sdk.createBIDByTemplate(request);
        System.out.println(result);
```

#### 13. 通过对象创建BID标识

- 函数体

>  createBID


- 输入参数

>| 参数             | 必选 | 类型         | 说明                                        |
>| :--------------- | :--- | :----------- | :------------------------------------------ |
>| senderAddress    | true | String       | 地址                                        |
>| senderPrivateKey | true | String       | 私钥                                        |
>| amount           | true | Long         | 转账金额                                    |
>| remark           | true | String       | 交易备注                                    |
>| bids             | true | List<Object> | 详情见参数列表[BidParameter](#BidParameter) |

- 返回值

> | 参数         | 类型    | 说明               |
> | ------------ | :------ | :----------------- |
> | status       | Boolean | 成功true,失败false |
> | message      | String  | 返回结果信息       |
> | message.hash | String  | 返回交易hash       |

- 接口示例

```java
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
        serviceOperation.setServiceEndpoint("http://test-bidresolver.bitfactory.cn");
        //服务类型
        serviceOperation.setType(DidType.DID_DECRYPT.getCode());
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
```

#### 14. 递归解析BID标识

- 函数体

> resolverBid


- 输入参数

>| 参数 | 必选 | 类型   | 说明    |
>| :--- | :--- | :----- | :------ |
>| bid  | true | String | BID地址 |


- 返回值

> | 参数    | 类型    | 说明               |
> | ------- | :------ | :----------------- |
> | status  | Boolean | 成功true,失败false |
> | message | String  | 返回结果信息       |


- 接口示例

```java
 @Test
    public void resolverBid(){
        SDK sdk = SDK.getInstance("http://test-bidresolver.bitfactory.cn");
        String bid="did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9";
        Result result = sdk.resolverBid(bid);
        System.out.println(result);
    }
```

#### 15. 对文档内容签名验签

文档所有者对文档内容的签名进行验签

- 函数体

> isValidProof


- 输入参数

>| 参数    | 必选 | 类型   | 说明     |
>| :------ | :--- | :----- | :------- |
>| request | true | String | 文档内容 |


- 返回值

> | 参数    | 类型    | 说明               |
> | ------- | :------ | :----------------- |
> | status  | Boolean | 成功true,失败false |
> | message | String  | 返回结果信息       |


- 接口示例

```java
    @Test
    public void isValidProof(){
        String request = "{\"didDocument\":{\"extension\":{\"acsns\":[\"acsn\"],\"attributes\":[{\"encrypt\":1,\"format\":\"text\",\"value\":\"BID文档\",\"key\":\"name\",\"desc\":\"名称\"}],\"delegateSign\":{\"signatureValue\":\"07E5B21A42B38B89A409A720E6BC1BDAC1ED2BD22F7786682470C8B975A9DC9C23E8DD9AC416712E7DE3026CED7F3866A4073368B01750698CBB0FEF783BC60C\",\"signer\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\"},\"recovery\":[\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-2\"],\"ttl\":86400,\"type\":102,\"verifiableCredentials\":[{\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"type\":201}]},\"created\":\"2022-08-29T19:02:59Z\",\"service\":[{\"protocol\":2,\"serverType\":0,\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#resolver\",\"serviceEndpoint\":\"https://bidresolver.com\",\"type\":\"DIDDecrypt\"}],\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"proof\":{\"creator\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\",\"signatureValue\":\"E7985417835294115373036E0470F9047A343A701ABC84DF4A39EE3380FDB013171F29F6107E91B77999DCAD9ABB4BEB91F5D5BC52CDA50B6A4B856F5D71F609\"},\"publicKey\":[{\"controller\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\",\"publicKeyHex\":\"b0656616cef061d0cae2b3e4fa49694cad29b4eb864ec85f1538ccb2edb926b71d276e\",\"type\":\"ED25519\"}],\"@context\":[\"https://www.w3.org/ns/did/v1\"],\"updated\":\"2022-08-29T19:02:59Z\",\"version\":\"1.0.0\",\"alsoKnownAs\":[{\"id\":\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9\",\"type\":101}],\"authentication\":[\"did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9#key-1\"]}}";
        Result result = sdk.isValidProof(request);
        System.out.println(result);
    }
```

#### 16. BID合约查询

通过合约地址查询bid标识

- 函数体

> bidQueryByContract


- 输入参数

>| 参数 | 必选 | 类型   | 说明    |
>| :--- | :--- | :----- | :------ |
>| bid  | true | String | bid地址 |


- 返回值

> | 参数    | 类型    | 说明               |
> | ------- | :------ | :----------------- |
> | status  | Boolean | 成功true,失败false |
> | message | String  | 返回结果信息       |


- 接口示例

```java
@Test
    public void bidQueryByContract() {
        SDK sdk = SDK.getInstance("http://test-bif-core.xinghuo.space111");
        String bid="did:bid:efj3FikDU8c7An3SPUoRtEWf2JDg1Hg9";
        Result result = sdk.bidQueryByContract(bid);
        System.out.println(result);
    }
```

#### 17. 根据Hash查询交易信息

通过hash地址查询交易信息

- 函数体

> queryTransactionInfoByHash


- 输入参数

>| 参数 | 必选 | 类型   | 说明     |
>| :--- | :--- | :----- | :------- |
>| hash | true | String | Hash地址 |


- 返回值

> | 参数    | 类型    | 说明               |
> | ------- | :------ | :----------------- |
> | status  | Boolean | 成功true,失败false |
> | message | String  | 返回结果信息       |


- 接口示例

```java
@Test
    public void queryTransactionInfoByHash(){
        SDK sdk = SDK.getInstance("http://test-bif-core.xinghuo.space");
        String hash="c792c6901fdece007786f11cfe17a85b85554938e9ea48ea8c18b05dca20605a";
        Result result = sdk.queryTransactionInfoByHash(hash);
        System.out.println(result);
    }
```

#### 18. 通过Hash获取BID标识

通过解析BID标识交易hash地址获取BID标识地址

- 函数体

> getBidByHash


- 输入参数

>| 参数 | 必选 | 类型   | 说明                |
>| :--- | :--- | :----- | :------------------ |
>| hash | true | String | BID标识交易Hash地址 |


- 返回值

> | 参数    | 类型    | 说明               |
> | ------- | :------ | :----------------- |
> | status  | Boolean | 成功true,失败false |
> | message | String  | 返回结果信息       |


- 接口示例

```java
@Test
   public void getBidByHash(){
        SDK sdk = SDK.getInstance("http://test-bif-core.xinghuo.space");
        String hash="6c40f186b702db243766785af4ae125cfeeb116eacb5b01e66a8b6456a128122";
        Result result = sdk.getBidByHash(hash);
        System.out.println(result);
    }
```

### 参数集合列表

**BID协议文档地址**：[星火·链网BID协议](https://bid-resolution-protocol-doc.readthedocs.io/zh_CN/latest/doc/%E6%98%9F%E7%81%AB%C2%B7%E9%93%BE%E7%BD%91BID%E5%8D%8F%E8%AE%AE.html)

##### BidParameter

| 序号 | 成员           | 描述                                                         |
| ---- | -------------- | ------------------------------------------------------------ |
| 1    | context        | 必填字段。一组解释JSON-LD文档的规则,遵循DID规范，用于实现不同DID Document的互操作，必须包含https://www.w3.org/ns/did/v1 |
| 2    | version        | 必填字段。文档的版本号                                       |
| 3    | id             | 必填字段。文档的BID                                          |
| 4    | publicKey      | 选填字段。一组公钥，包含id，type，controller,publicKeyHex四个字段，凭证类的BID文档不包含该字段。[publicKeyParameter](#publicKeyParameter) |
| 5    | authentication | 必填字段。一组公钥的BID，表名此BID的归属，拥有此公钥对应私钥的一方可以控制和管理此BID文档 |
| 6    | alsoKnownAs    | 选填字段。一组和本BID关联的其他ID, 包括type和id两个字段。[alsoKnownAsParamer](#alsoKnownAsParamer) |
| 7    | extension      | BID扩展字段。[extensionParamer](#extensionParamer)           |
| 8    | service        | 选填字段。一组服务地址，包括id，type，serviceEndpoint三个必填字段。[serviceParamer](#serviceParamer) |
| 9    | created        | 必填字段。创建时间                                           |
| 10   | updated        | 必填字段。上次的更新时间。                                   |
| 11   | proof          | 选填字段。文档所有者对文档内容的签名，包括：creator和signatureValue。[proofParamer](#proofParamer) |

##### publicKeyParameter

| 序号 | 成员         | 描述                                                 |
| ---- | ------------ | ---------------------------------------------------- |
| 1    | id           | 公钥的ID                                             |
| 2    | type         | 字符串，代表公钥的加密算法类型，支持Ed25519和SM2两种 |
| 3    | controller   | 一个BID,表明此公钥的归属。                           |
| 4    | publicKeyHex | 公钥的十六进制编码。                                 |

##### alsoKnownAsParamer

| 序号 | 成员 | 描述                                        |
| ---- | ---- | ------------------------------------------- |
| 1    | id   | 关联的标识。                                |
| 2    | type | 关联标识的类型,取值详见附录**关联标识类型** |

##### extensionParamer

| 序号 | 成员                  | 描述                                                         |
| ---- | --------------------- | ------------------------------------------------------------ |
| 1    | recovery              | 选填字段。一组公钥id, 在authentication私钥泄漏或者丢失的情况下用来恢复对文档的控制权。 |
| 2    | ttl                   | 必填字段。Time-To-Live，即如果解析使用缓存的话缓存生效的时间，单位秒。 |
| 3    | delegateSign          | 选填字段。第三方对publicKey的签名，可信解析使用。包括：signer和signatureValue。[delegateSignParamer](#delegateSignParamer) |
| 4    | type                  | 必填字段。BID文档的属性类型，取值见附录**属性类型**。        |
| 5    | attributes            | 必填字段。一组属性。[attributesParamer](#attributesParamer)  |
| 6    | acsns                 | 选填字段。一组子链AC号，只有BID文档类型不是凭证类型且文档是主链上的BID文档才可能有该字段，存放当前BID拥有的所有AC号 |
| 7    | verifiableCredentials | verifiableCredentials:选填字段。凭证列表，包含id和type两个字段。[verifiableCredentialsParamer](#verifiableCredentialsParamer) |

##### delegateSignParamer

| 序号 | 成员           | 描述                                |
| ---- | -------------- | ----------------------------------- |
| 1    | signer         | 签名者，这里是一个公钥的id。        |
| 2    | signatureValue | 使用相应私钥对publicKey字段的签名。 |

##### attributesParamer

| 序号 | 成员    | 描述                                        |
| ---- | ------- | ------------------------------------------- |
| 1    | key     | 属性的关键字                                |
| 2    | desc    | 选填。属性描述                              |
| 3    | encrypt | 选填。是否加密，0非加密，1加密              |
| 4    | format  | 选填。image、text、video、mixture等数据类型 |
| 5    | value   | 选填。属性自定义value                       |

##### verifiableCredentialsParamer

| 序号 | 成员 | 描述              |
| ---- | ---- | ----------------- |
| 1    | id   | 可验证声明的BID。 |
| 2    | type | 凭证类型          |

##### serviceParamer

| 序号 | 成员            | 描述                                                         |
| ---- | --------------- | ------------------------------------------------------------ |
| 1    | id              | 服务地址的ID。                                               |
| 2    | type            | 字符串，代表服务的类型。取值见附录**服务类型**。             |
| 3    | serviceEndpoint | 一个URI地址。当type为子链解析服务时， service为以下结构：[serviceEndpoint](#serviceEndpoint) |

##### serviceEndpoint

| 序号 | 成员            | 描述                                                         |
| ---- | --------------- | ------------------------------------------------------------ |
| 1    | id              | 服务地址的ID                                                 |
| 2    | type            | DIDSubResolver                                               |
| 3    | version         | 服务支持的BID解析协议版本                                    |
| 4    | protocol        | 解析协议支持的传输协议类型, 具体取值见附录**解析服务协议类型** |
| 5    | serverType      | 服务地址类型，0为域名形式，1为IP地址形式                     |
| 6    | serviceEndpoint | 解析地址的IP或域名                                           |
| 7    | port            | serverType为1时有该字段，解析服务的端口号                    |

##### proofParamer

| 序号 | 成员           | 描述                                         |
| ---- | -------------- | -------------------------------------------- |
| 1    | creator        | proof的创建者，这里是一个公钥的id            |
| 2    | signatureValue | 使用相应私钥对除proof字段的整个BID文档签名。 |

### 附表 - 异常码

> |异常码     |标识符|提示消息|
>| :-------- | ---------| :--------|
>|1   |EXCEPTIONCODE_GENERATOR_KEY_ERROR    | GENERATOR_KEY_ERROR     |
>|2   |EXCEPTIONCODE_UNSUPPORT_ENCODETYPE    |UNSUPPORT_ENCODETYPE                          |
>|3   |EXCEPTIONCODE_UNSUPPORT_KEYTYPE    |UNSUPPORT_KEYTYPE                          |
>|4   |EXCEPTIONCODE_INVALID_CHAINCODE    |INVALID_CHAINCODE                          |
>|5   |EXCEPTIONCODE_INVALID_KEY    |INVALID_KEY                          |
>|6   |EXCEPTIONCODE_SIGN_FAILED    |SIGN_FAILED                         |
>|7   |IDENTIFIER_ENGINE_ERROR    |IDENTIFIER_ENGINE_ERROR                          |
>|8   |EXCEPTIONCODE_VERIFY_FAILED|VERIFY_FAILED                      |
>|9   |EXCEPTIONCODE_SYSTEM_ERROR    |SYSTEM_ERROR                  |
>|10   |EXCEPTIONCODE_INVALID_SIGN    |INVALID_SIGN                          |
>|11   |EXCEPTIONCODE_HASH_FAILED    |HASH_FAILED                          |

版权声明：中国信息通信研究院工业互联网与物联网研究所
