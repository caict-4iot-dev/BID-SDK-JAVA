# BID-TOOL使用指南

### 基本概念介绍：  
BID命令行工具，主要是为方便开发者使用命令行的方式完成BID标识的生成验证等，有以下功能：  

- 获取版本号：获取BID-SDK版本号、获取BID版本号。
- BID工具：生成BID标识和验证BID地址格式的合法性。  
- 公私钥工具：生成星火格式的公私钥、使用星火格式的私钥生成签名、使用星火格式的公钥生成签名。    

### 快速使用
- jar包获取：下载开源项目BID-SDK，在BID-TOOL下导出jar包，并从该项目中获取依赖jar包，commons-codec-1.11.jar、eddsa-0.1.0.jar、commons-cli-1.4.jar。
- 执行 java -jar bid-jar.jar --help ,根据提示信息传递需要的参数。
- 环境要求：jdk1.8或以上。

