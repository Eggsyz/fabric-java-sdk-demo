package SDK;

import SDK.service.ChaincodeService;
import org.junit.Test;

/**
 * 基于fabric-java-sdk测试合约查询和调用
 *
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
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

        /* 自测结果，具体数值可能不同，但是两次肯定差距200
         * message => -540
         * txid: 6750c373658547800c92ef6d13e05443a9ccc6170066d86fc60419c8f83c0ea3
         * valid: true
         * message => -740
         * === Fabric Demo End ===
         */
    }
}
