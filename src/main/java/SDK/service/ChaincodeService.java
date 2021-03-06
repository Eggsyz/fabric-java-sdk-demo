package SDK.service;

import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.security.CryptoSuite;

import java.io.File;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *    实现链码的查询和调用
 */

public class ChaincodeService {

    private  HFClient hfClient;
    private  Channel channel;

    // 基于配置文件初始化客户端以及通道
     public ChaincodeService(String channelId, String configPath) {
            try {
                File f = new File(configPath);
                NetworkConfig config = NetworkConfig.fromYamlFile(f);
                this.hfClient =HFClient.createNewInstance();
                this.hfClient.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
                // 需要动态选择user，可以实现user接口，然后通过读取该客户的配置文件路径进行查询或者通过不同的配置文件来实现
                this.hfClient.setUserContext(config.getPeerAdmin());
//              System.out.println(config.getPeerAdmin().getEnrollSecret());
                this.channel=this.hfClient.loadChannelFromConfig(channelId,config);
                // 通道初始化
                this.channel.initialize();

            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public void event() {
        // 监听事件
        try{
            //监听待支付事件
            channel.registerChaincodeEventListener(Pattern.compile("ccbcc3"),
                Pattern.compile("PendingPaidEvent"),
                (handle, blockEvent, chaincodeEvent) -> {
                    String es = blockEvent.getPeer() != null ? blockEvent.getPeer().getName() : blockEvent.getEventHub().getName();
                    System.out.printf("RECEIVED Chaincode event with handle: %s, chaincode Id: %s, chaincode event name: %s, "
                                    + "transaction id: %s, event payload: \"%s\", from eventhub: %s \n",
                            handle, chaincodeEvent.getChaincodeId(),
                            chaincodeEvent.getEventName(),
                            chaincodeEvent.getTxId(),
                            new String(chaincodeEvent.getPayload()),
                            es);
                });
            // 监听已支付事件
            channel.registerChaincodeEventListener(Pattern.compile("ccbcc3"),
                    Pattern.compile("PaidEvent"),
                    (handle, blockEvent, chaincodeEvent) -> {
                        String es = blockEvent.getPeer() != null ? blockEvent.getPeer().getName() : blockEvent.getEventHub().getName();
                        System.out.printf("RECEIVED Chaincode event with handle: %s, chaincode Id: %s, chaincode event name: %s, "
                                        + "transaction id: %s, event payload: \"%s\", from eventhub: %s \n",
                                handle, chaincodeEvent.getChaincodeId(),
                                chaincodeEvent.getEventName(),
                                chaincodeEvent.getTxId(),
                                new String(chaincodeEvent.getPayload()),
                                es);
                    });
            // 监听待兑回事件
            channel.registerChaincodeEventListener(Pattern.compile("ccbcc3"),
                    Pattern.compile("PendingRedeemedEvent"),
                    (handle, blockEvent, chaincodeEvent) -> {
                        String es = blockEvent.getPeer() != null ? blockEvent.getPeer().getName() : blockEvent.getEventHub().getName();
                        System.out.printf("RECEIVED Chaincode event with handle: %s, chaincode Id: %s, chaincode event name: %s, "
                                        + "transaction id: %s, event payload: \"%s\", from eventhub: %s \n",
                                handle, chaincodeEvent.getChaincodeId(),
                                chaincodeEvent.getEventName(),
                                chaincodeEvent.getTxId(),
                                new String(chaincodeEvent.getPayload()),
                                es);
                    });
            // 监听已兑回事件
            channel.registerChaincodeEventListener(Pattern.compile("ccbcc3"),
                    Pattern.compile("RedeemedEvent"),
                    (handle, blockEvent, chaincodeEvent) -> {
                        String es = blockEvent.getPeer() != null ? blockEvent.getPeer().getName() : blockEvent.getEventHub().getName();
                        System.out.printf("RECEIVED Chaincode event with handle: %s, chaincode Id: %s, chaincode event name: %s, "
                                        + "transaction id: %s, event payload: \"%s\", from eventhub: %s \n",
                                handle, chaincodeEvent.getChaincodeId(),
                                chaincodeEvent.getEventName(),
                                chaincodeEvent.getTxId(),
                                new String(chaincodeEvent.getPayload()),
                                es);
                    });
            // 监听待退款事件
            channel.registerChaincodeEventListener(Pattern.compile("ccbcc3"),
                    Pattern.compile("PendingRefundEvent"),
                    (handle, blockEvent, chaincodeEvent) -> {
                        String es = blockEvent.getPeer() != null ? blockEvent.getPeer().getName() : blockEvent.getEventHub().getName();
                        System.out.printf("RECEIVED Chaincode event with handle: %s, chaincode Id: %s, chaincode event name: %s, "
                                        + "transaction id: %s, event payload: \"%s\", from eventhub: %s \n",
                                handle, chaincodeEvent.getChaincodeId(),
                                chaincodeEvent.getEventName(),
                                chaincodeEvent.getTxId(),
                                new String(chaincodeEvent.getPayload()),
                                es);
                    });
            // 监听已退款事件
            channel.registerChaincodeEventListener(Pattern.compile("ccbcc3"),
                    Pattern.compile("RefundEvent"),
                    (handle, blockEvent, chaincodeEvent) -> {
                        String es = blockEvent.getPeer() != null ? blockEvent.getPeer().getName() : blockEvent.getEventHub().getName();
                        System.out.printf("RECEIVED Chaincode event with handle: %s, chaincode Id: %s, chaincode event name: %s, "
                                        + "transaction id: %s, event payload: \"%s\", from eventhub: %s \n",
                                handle, chaincodeEvent.getChaincodeId(),
                                chaincodeEvent.getEventName(),
                                chaincodeEvent.getTxId(),
                                new String(chaincodeEvent.getPayload()),
                                es);
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

        // 调用该通道链码invoke方法, 并打印交易id以及交易是否有效
    public void invoke( String chainCodeName, String funcName,String... args){
        try {

            // 初始化invoke请求
            TransactionProposalRequest request = this.hfClient.newTransactionProposalRequest();
            ChaincodeID chaincodeID=ChaincodeID.newBuilder().setName(chainCodeName).build();
            // 设置链码id,调用方法，参数
            request.setChaincodeID(chaincodeID);
            request.setFcn(funcName);
            request.setArgs(args);

            Collection<Peer> peers = channel.getPeers(EnumSet.of(Peer.PeerRole.ENDORSING_PEER));
            System.out.println(peers);
            Collection<ProposalResponse> responses = this.channel.sendTransactionProposal(request);
            // 发送交易
            BlockEvent.TransactionEvent event = this.channel.sendTransaction(responses).get();
            System.out.format("txid: %s\n", event.getTransactionID());
            System.out.format("valid: %b\n", event.isValid());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 调用该通道链码query查询方法，并打印响应结果
    public void query( String chainCodeName, String funcName,String... args){
        try {
            //查询链码
            QueryByChaincodeRequest req = this.hfClient.newQueryProposalRequest();
            ChaincodeID cid = ChaincodeID.newBuilder().setName(chainCodeName).build();
            req.setChaincodeID(cid);
            req.setFcn(funcName);
            req.setArgs(args);
            ProposalResponse[] rsp = this.channel.queryByChaincode(req).toArray(new ProposalResponse[0]);
            System.out.format("message => %s\n",rsp[0].getProposalResponse().getResponse().getPayload().toStringUtf8());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}