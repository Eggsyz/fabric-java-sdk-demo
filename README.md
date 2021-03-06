## fabric-java-sdk 教程

本项目主要介绍如何基于fabric-java-sdk与链码交互。主要是query/invoke两种方式。
+ query  查询操作
+ invoke 调用操作，该操作会上链

项目主要代码都在src/main/java/SDK/service目录下，其中主要实现了链码调用和链码查询。

在具体使用过程中，需要进行如下操作：
1. 克隆Fabric源码编译镜像
```bash
git clone https://github.com/hyperledger/fabric.git
cd fabric
make all
```
2. 克隆Fabric样例项目fabric-sample
```bash
git clone https://github.com/hyperledger/fabric-samples.git
```
3. 启动一个Fabric网络
```bash
cd fabric-samples/first-network
./byfn.sh up

或者直接基于云象内网fabric启动
```
4. 根据网络配置修改配置文件
```bash
SDK/configs/network-config-tls.yaml
```
修改事项：
+ 通道名
+ 节点名称及tlsCa证书
+ 组织Admin私钥和证书

5. 调用合约mycc invoke/query方法


```java
    System.out.println( "=== Fabric Demo Start ===" );
    String channelName="mychannel";
    String filePath="/Users/eggsy/java/src/javasdkdemo/src/main/java/SDK/configs/network-config-tls.yaml";
    ChaincodeService service=new ChaincodeService(channelName,filePath);
    String chaincodeName="mycc";
    // 调用mycc的查询方法，查询a的值
    String fcn="query";
    String[] arguments = new String[]{"a"};
    service.query(chaincodeName,fcn,arguments);
    // 调用mycc的invoke方法，a转给b200
    fcn="invoke";
    arguments = new String[]{"a","b","200"};
    service.invoke(chaincodeName,fcn,arguments);
    // 调用mycc的查询方法，查询转账后a的值
    fcn="query";
    arguments = new String[]{"a"};
    service.query(chaincodeName,fcn,arguments);
    System.out.println( "=== Fabric Demo End ===" );
```