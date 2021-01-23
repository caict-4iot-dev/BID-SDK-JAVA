## 简介

2020年2月，中国信通院制定的BID方法被纳入W3C凭证社区工作组（Credentials Community Group）分布式标识（DID）规范。BID面向实体（包括人、物、组织）和数字对象，可用于拥有者证明其对BID的控制权及身份验证功能，而不需要依赖其他外部组织。BID标识目前应用于“星火链网”，由公钥经过一系列的算法后编码生成，支持ed25519、国密sm2、secp256k1等常用非对称加密算法生成的公钥；编码算法支持base58、 base64、betch32等常用的编码算法。BID实现了拥有者对标识的自我控制和管理，同时，通过密码学算法实现了隐私保护、安全可靠。

BID-SDK通过API调用的方式提供了“星火链网”公私钥对生成、“星火链网”私钥签名公钥验签、BID标识生成、BID标识验证等接口，同时还提供了接口使用示例说明，开发者可以调用该SDK方便快捷的生成星火链网公私钥对和BID地址，实现BID标识合法性的校验及主链的快速接入。中国信通院秉持开源开放的理念，将星火“BID-SDK”面向社区和公众完全开源，助力全行业伙伴提升数据价值流通的效率，实现数据价值转化。

![image](https://github.com/CAICT-DEV/BID-SDK-JAVA/blob/master/image/sdk.jpg)

## 环境要求
- BID-SDK运行依赖 commons-codec-1.11.jar、eddsa-0.1.0.jar。 
- BID-SDK需要JDK1.8以上的版本。

## BID-SDK使用  
- BID-SDK目录：BID-SDK开源代码  
- BID-SDK-DEMO目录：BID-SDK开发包演示demo  
- BID 命令行工具，使用该工具通过命令行的方式使用BID-SDK提供的接口功能

详见[BID-SDK开发指南](./BID-SDK开发指南.md)、[BID-TOOL使用指南](./BID-TOOL使用指南.md)

## 文档

- [CHANGELOG](./CHANGELOG.md)
- [LICENSE](./LICENSE)
- [BID-SDK开发指南](./BID-SDK开发指南.md)
- [BID-TOOL使用指南](./BID-TOOL使用指南.md)

## 问题反馈

欢迎参与BID应用的生态建设：

1. 如项目对您有帮助，欢迎点亮我们的小星星(点击项目左上方Star按钮)。

2. 欢迎提交代码(Pull requests)。

3. 提问和提交BUG。

4. 邮件反馈：zhangbo3@caict.ac.cn

   我们将尽快给予回复。
   
## 发行说明

各版本更新将记录在[CHANGELOG](./CHANGELOG.md)中。

## 许可证

[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)

版权所有 2021 中国信息通信研究院工业互联网与物联网研究所
