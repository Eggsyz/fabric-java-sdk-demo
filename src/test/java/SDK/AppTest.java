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
        String channelName="testchannel";
        String filePath="/Users/eggsy/java/src/javasdkdemo/src/main/java/SDK/configs/network-config-baas3-user.yaml";
        ChaincodeService service=new ChaincodeService(channelName,filePath);
        service.event();
        String chaincodeName="ccbcc1";
        // 创建收单行
        service.invoke(chaincodeName,"createBank","{\"bankId\":\"0000000001\",\"walletId\":\"0000001\",\"orgName\":\"baas2\"}");
        // 创建订单
        // service.invoke(chaincodeName,"createOrder","{\"serialNumber\":\"2001\",\"timestamp\":1596014051,\"acquiringBankWalletId\":\"0000001\",\"merchantId\":\"001\",\"consumerWalletId\":\"2000\",\"consumerWalletBankNo\":\"test\",\"amount\":\"2000.001\",\"status\":1}");
        // 创建支付流水
        // service.invoke(chaincodeName,"uploadOrderCoinStr","{\"serialNumber\":\"2003\",\"timestamp\":1596014051,\"orderOrRefundSerialNumber\":\"2001\",\"coinStr\":{\"coinNo\":\"101\",\"amount\":\"200.001\",\"status\":1},\"acquiringBankWalletId\":\"0000001\",\"consumerWalletId\":\"2000\",\"amount\":\"200.001\",\"status\":2}");
        // 创建币串兑回
        // service.invoke(chaincodeName,"exchangeCoinStr","{\"walletId\":\"0000001\",\"serialNumber\":\"20000002\"}");
        // 根据兑回结果更新币串状态
        // service.invoke(chaincodeName,"updateCoinStrState","{\"walletId\":\"0000001\",\"coinstrs\":[{\"coinNo\":\"101\",\"amount\":\"200.001\",\"status\":2}],\"redeemedAmount\":\"200.001\",\"totalBalance\":\"200.001\",\"redeemedRequestSerialNumber\":\"10000001\",\"redeemedResponseSerialNumber\":\"10000002\"}");
        // 退货申请
        //  service.invoke(chaincodeName,"createReturnGood","{\"serialNumber\":\"2002\",\"orderSerialNumber\":\"1001\",\"paymentSerialNumber\":\"1002\",\"amount\":\"200.001\",\"merchantId\":\"001\",\"consumerWalletId\":\"2000\",\"acquiringBankWalletId\":\"0000001\",\"timestamp\":1596014051,\"status\":1,\"coinStr\":{\"coinNo\":\"101\",\"amount\":\"200.001\",\"status\":1},\"consumerWalletBankNo\":\"test\"}");
        // 统一退货
        //service.invoke(chaincodeName,"uploadReturnCoinStr","{\"serialNumber\":\"2003\",\"timestamp\":1596014051,\"orderOrRefundSerialNumber\":\"2002\",\"coinStr\":{\"coinNo\":\"101\",\"amount\":\"200.001\",\"status\":2},\"acquiringBankWalletId\":\"0000001\",\"consumerWalletId\":\"2000\",\"amount\":\"200.001\",\"status\":2}");

    }
}
