# BID-SDK开发指南

### 基本概念介绍：  
BID开发工具包，主要是为了方便开发者可以快速加入到星火主链的生态建设中，有以下功能：  

- 获取版本号：获取BID-SDK版本号、获取BID版本号。
- BID工具：生成BID标识和验证BID地址格式的合法性。  
- 公私钥工具：生成星火格式的公私钥、使用星火格式的私钥生成签名、使用星火格式的公钥生成签名。    

### BID-SDK与BID版本对应关系说明
> |ID-SDK版本|BID版本                              |
> | :-------- | :--------|
> |1.0.0  |1.0.0 |

### 快速使用
- jar包获取：下载开源项目ID-SDK，导出jar包，并从该项目中获取依赖jar包，commons-codec-1.11.jar、eddsa-0.1.0.jar。
- 环境要求：jdk1.8或以上。


### 开发指南
#### 1. 获取sdk版本号
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
import cn.ac.caict.bid.SDK;
public class TestGetSdkVersion {
    public static void main(String[] args){
        //创建SDK实例
        SDK bidSdk = new SDK();
        String sdkVersion= bidSdk.getSdkVersion();
        System.out.println(sdkVersion);
    }
}
```
#### 2. 获取bid版本号
- 函数体
> getBidVersion


- 输入参数
> 无



- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|String   |BID版本号 |


- 接口示例
```java
import cn.ac.caict.bid.SDK;
public class TestGetBidVersion {
        public static void main(String[] args){
            //创建SDK实例
            SDK bidSdk = new SDK();
            String bidVersion= bidSdk.getBidVersion();
            System.out.println(bidVersion);
    }
}
```

#### 3. 生成BID地址和公私钥对

- 函数体
> getBidByBifPubkey


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|chaincode    |false    |String| chaincode                          |


- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|KeyPairEntity   |KeyPairEntity对象，属性包含bid和星火格式的公私钥|


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.model.KeyPairEntity;

public class TestGetBidAndKeyPair {
        public static void main(String[] args){
            //创建SDK实例
            SDK bidSdk = new SDK();
            try {
                KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair();
                String publicKey = kaypairEntity.getPublicKey();
                String privateKey = kaypairEntity.getPrivateKey();
                String bid = kaypairEntity.getBid();
                System.out.println(publicKey);
                System.out.println(privateKey);
                System.out.println(bid);
            } catch (SDKException e) {
                System.out.println(e.getMessage());
            }
        }
}
```

#### 4. 根据用户公钥生成BID地址
- 函数体
> getBidByBifPubkey


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|publicKey    |true    |String|星火格式的公钥                          |
>|chaincode    |false    |String| chaincode                          |


- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|String   |bid|


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exceptions.SDKException;
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


#### 5. 根据私钥生成公钥

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
import cn.ac.caict.bid.exceptions.SDKException;

public class TestGetPubkeyByPrikey {
        public static void main(String[] args){
            String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
            SDK bidSdk = new SDK();
            try {
                String publicKey = bidSdk.getBifPubkeyByPrivateKey(privateKey);
                System.out.println(publicKey);
            }catch (SDKException e){
                System.out.println(e.getMessage());
            }
        }
}
```

#### 5. 签名星火链Blob消息
- 函数体
> signBlob


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|privateKey    |true    |String|星火格式的私钥                          |
>|msg    |true    |byte[]   |原消息|
-返回值
> |类型|说明                              |
> | :-------- | :--------|
>|String   |返回签名后的消息|


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.util.HexFormatUtil;
public class TestSigBlob {
        public static void main(String[] args){
            SDK bidSdk = new SDK();
            String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
            String msg = "0a2e61303032643833343562383964633334613537353734656234393736333566663132356133373939666537376236100122b90108012ab4010a2e61303032663836366337663431356537313934613932363131386363353565346365393939656232396231363461123a123866756e6374696f6e206d61696e28696e707574537472297b0a202f2ae8bf99e698afe59088e7baa6e585a5e58fa3e587bde695b02a2f207d1a06080a1a020807223e0a0568656c6c6f1235e8bf99e698afe5889be5bbbae8b4a6e58fb7e79a84e8bf87e7a88be4b8ade8aebee7bdaee79a84e4b880e4b8aa6d65746164617461";
            try {
                String result = HexFormatUtil.byteToHex(bidSdk.signBlob(privateKey, HexFormatUtil.hexStringToBytes(msg)));
                System.out.println(result);
            }catch (SDKException e){
                System.out.println(e.getMessage());
            }
        }
}
```

#### 6. 验正星火链Blob消息签名
用来验证星火链上的签名信息，星火链的签名信息byte[]经过16进制格式化后存储，因此在验签之前需要先转回byte[]
- 函数体
> verifySig


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|pulicKey    |true    |String|星火格式的公钥                          |
>|msg    |true    |byte[]   |原消息|
>|signature |true |byte[]  |签名消息|


- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|boolean   |验签成功返回true,失败返回false |


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.util.HexFormatUtil;
public class TestVerifyBlob {
        public static void main(String[] args){
            SDK bidSdk = new SDK();
            String publicKey = "B065667CC1E4584BC9DDB6806C455BDEA9F8390724A77A6ED2F6369C830043418C0745";
            String msg = "0a2e61303032643833343562383964633334613537353734656234393736333566663132356133373939666537376236100122b90108012ab4010a2e61303032663836366337663431356537313934613932363131386363353565346365393939656232396231363461123a123866756e6374696f6e206d61696e28696e707574537472297b0a202f2ae8bf99e698afe59088e7baa6e585a5e58fa3e587bde695b02a2f207d1a06080a1a020807223e0a0568656c6c6f1235e8bf99e698afe5889be5bbbae8b4a6e58fb7e79a84e8bf87e7a88be4b8ade8aebee7bdaee79a84e4b880e4b8aa6d65746164617461";
            String sig = "8055422ADDC330ECAC1B328E5DFFE79A7CB51FDC111A8D10DAE1AE93297D87E8D7F0D4210B29546ACDE2DE9CB1A6FDAC15BB73E0B8C590F7DB874989C626FB07";
            try {
                boolean result = bidSdk.verifySig(publicKey, HexFormatUtil.hexStringToBytes(msg), HexFormatUtil.hexToByte(sig));
                System.out.println(result);
            }catch (SDKException e){
                System.out.println(e.getMessage());
            }
      }
}
```


#### 7. 验证bid格式是否合法
- 函数体
> isValidBid


- 输入参数
>|参数|必选|类型|说明|
>| :-------- | :--------| :--------| :--------|
>|bid    |true    |String|bid                          |


- 返回值
> |类型|说明                              |
> | :-------- | :--------|
>|boolean   |验证结果|


- 接口示例
```java
import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.model.Result;
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

附表1 - 异常码

> |异常码     |标识符|提示消息|
>| :-------- | :--------| :--------|
>|-1   |EXCEPTIONCODE_GENERATOR_KEY_ERROR    | GENERATOR_KEY_ERROR     |
>|-2   |EXCEPTIONCODE_UNSUPPORT_ENCODETYPE    |UNSUPPORT_ENCODETYPE                          |
>|-4   |EXCEPTIONCODE_UNSUPPORT_KEYTYPE    |UNSUPPORT_KEYTYPE                          |
>|-5   |EXCEPTIONCODE_INVALID_CHAINCODE    |INVALID_CHAINCODE                          |
>|-6   |EXCEPTIONCODE_INVALID_KEY    |INVALID_KEY                          |
>|-7   |EXCEPTIONCODE_SIGN_FAILED    |SIGN_FAILED                         |
>|-8   |IDENTIFIER_ENGINE_ERROR    |IDENTIFIER_ENGINE_ERROR                          |
>|-9   |EXCEPTIONCODE_VERIFY_FAILED|VERIFY_FAILED                      |
>|-10   |EXCEPTIONCODE_SYSTEM_ERROR    |SYSTEM_ERROR                  |
>|-11   |EXCEPTIONCODE_INVALID_SIGN    |INVALID_SIGN                          |
>|-12   |EXCEPTIONCODE_HASH_FAILED    |HASH_FAILED                          |

版权声明：中国信息通信研究院工业互联网与物联网研究所
