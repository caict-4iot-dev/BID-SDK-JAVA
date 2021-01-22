# BID-SDK开发指南

### 基本概念介绍：  
BID开发工具包。  

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
package test;
import cn.bid.sdk;
public class GetSdkVersion {
    public static void main(String[] args){
		//创建SDK实例
		SDK bidSdk = new SDK();
		String sdkVersion= sdk.getSdkVersion();
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
package test;

import cn.bid.sdk;
public class GetSdkVersion {
    public static void main(String[] args){
		//创建SDK实例
		SDK bidSdk = new SDK();
		String bidVersion= sdk.getBidVersion();
		System.out.println(bidVersion);
	}
}
```
#### 3. 验签
用来验证星火链上的签名信息，星火链的签名信息byte[]经过16进制格式化后存储，因此在验签之前需要先转回byte[]
- 函数体
> verifyMessage


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
package test;

import cn.bid.sdk;
public class VerifyMessage {
    public static void main(String[] args){
		  SDK bidSdk = new SDK();
          String publicKey = "b065667cc1e4584bc9ddb6806c455bdea9f8390724a77a6ed2f6369c830043418c0745";
		  String msg = "1234";
		  String sig = "B91AB8D815D3230AC678AE560351A10CC536470F80C6B0B89498BB0DA2811A1A5500AAE1AAE25EF05FBC6FB0F9CBE919779C28F424629E7B324E9EA81924550D";
		  try {
		      boolean result = bidSdk.verifyMessage(publicKey, HexFormat.hexStringToBytes(msg), HexFormat.hexToByte(sig));
		      System.out.println(result);
		  }catch (SDKException e){
		     System.out.println(e.getMessage());
		  }
	}
}
```

#### 4. 签名
- 函数体
> signMessage


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
package test;

import cn.bid.sdk;
public class SignMessage {
    public static void main(String[] args){
		 SDK bidSdk = new SDK();
		 String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
		 String msg = "1234";
		 try {
		     String result = HexFormat.byteToHex(bidSdk.signMessage(privateKey, HexFormat.hexStringToBytes(msg)));
		     System.out.println(result);
		 }catch (SDKException e){
		    System.out.println(e.getMessage());
		 }
	}
}
```
#### 5. 验证bid格式是否合法
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
package test;

import cn.bid.sdk;
public class IsValidBid {
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
#### 6. 根据用户公钥生成BID地址
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
package test;

import cn.bid.sdk;
public class GetBidByPublicKey {
    public static void main(String[] args){
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

#### 7. 获取BID地址和公私钥对

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
package test;

import cn.bid.sdk;
public class GetBidAndKeyPair {
    public static void main(String[] args){
		//创建SDK实例
		SDK bidSdk = new SDK();
		try {
            KeyPairEntity kaypairEntity = sdk.getBidAndKeyPair();
            String publicKey = kaypairEntity.getPublicKey();
            String privateKey = kaypairEntity.getPrivateKey();
            String bid = kaypairEntity.getBid();
            System.out.println(publicKey);
            System.out.println(privateKey);
            System.out.println(bid);
		} catch (SDKException e) {
			e.printStackTrace();
		}
	}
}
```


#### 8. 根据私钥生成公钥

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
package test;

import cn.bid.sdk;
public class GetPublickeyByPrivateKey {
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