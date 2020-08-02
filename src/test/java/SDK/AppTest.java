package SDK;

import SDK.service.ChaincodeService;
import org.junit.Test;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;

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
        String filePath="/Users/eggsy/java/src/javasdkdemo/src/main/java/SDK/configs/network-config-baas2-admin.yaml";
        ChaincodeService service=new ChaincodeService(channelName,filePath);
        service.event();
//        try {
//            Thread.sleep(200000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        String chaincodeName="ccbcc";
        // 创建收单行
        //service.invoke(chaincodeName,"createBank","{\"bankId\":\"0000000002\",\"walletId\":\"0000002\",\"orgName\":\"baas3\"}");
        // 创建订单
        // long num=Calendar.getInstance().getTimeInMillis();
        // System.out.println(num);
        // String str="{\"serialNumber\":\""+num + "\",\"timestamp\":1596014051,\"acquiringBankWalletId\":\"0000002\",\"merchantId\":\"001\",\"consumerWalletId\":\"2000\",\"consumerWalletBankNo\":\"test\",\"amount\":\"2000.001\",\"status\":1}";
        //service.invoke(chaincodeName,"createOrder",str);
        service.query(chaincodeName,"getOrderList","{\"pageSize\":10,\"pageNum\":1,\"orgName\":\"baas2\"}");
        // 创建支付流水
        //service.invoke(chaincodeName,"uploadOrderCoinStr","{\"serialNumber\":\""+num+"\",\"timestamp\":1596014051,\"orderOrRefundSerialNumber\":\""+num+"\",\"coinStr\":{\"coinNo\":\"101\",\"amount\":\"200.001\",\"status\":1},\"acquiringBankWalletId\":\"0000002\",\"consumerWalletId\":\"2000\",\"amount\":\"200.001\",\"status\":2}");
        // 创建币串兑回
        //service.invoke(chaincodeName,"exchangeCoinStr","{\"walletId\":\"0000001\",\"serialNumber\":\""+num+"\"}");
        // 根据兑回结果更新币串状态
        //service.invoke(chaincodeName,"updateCoinStrState","{\"walletId\":\"0000001\",\"coinstrs\":[{\"coinNo\":\"101\",\"amount\":\"200.001\",\"status\":2}],\"totalAmount\":\"200.001\",\"totalBalance\":\"200.001\",\"redeemedRequestSerialNumber\":\""+num+"\",\"redeemedResponseSerialNumber\":\""+num+"\"}");
        // 退货申请
        // service.invoke(chaincodeName,"createReturnGood","{\"serialNumber\":\""+num+"\",\"orderSerialNumber\":\""+num+"\",\"paymentSerialNumber\":\""+num+"\",\"amount\":\"200.001\",\"merchantId\":\"001\",\"consumerWalletId\":\"2000\",\"acquiringBankWalletId\":\"0000001\",\"timestamp\":1596014051,\"status\":1,\"coinStr\":{\"coinNo\":\"101\",\"amount\":\"200.001\",\"status\":1},\"consumerWalletBankNo\":\"test\"}");
        // 退货
        //service.invoke(chaincodeName,"uploadReturnCoinStr","{\"serialNumber\":\""+num+"\",\"timestamp\":1596014051,\"orderOrRefundSerialNumber\":\""+num+"\",\"coinStr\":{\"coinNo\":\"101\",\"amount\":\"200.001\",\"status\":2},\"acquiringBankWalletId\":\"0000001\",\"consumerWalletId\":\"2000\",\"amount\":\"200.001\",\"status\":2}");

//        try {
//            Thread.sleep(200000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        //service.query(chaincodeName,"getCoinStr","0000001");
    }
}
