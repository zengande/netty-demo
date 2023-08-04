package io.zengande.nettydemo.server.messagehandler;

import io.netty.channel.Channel;
import io.zengande.nettydemo.common.codec.Invocation;
import io.zengande.nettydemo.common.dispatcher.MessageHandler;
import io.zengande.nettydemo.common.message.heartbeat.HeartbeatRequest;
import io.zengande.nettydemo.common.message.heartbeat.HeartbeatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HeartbeatMessageHandler implements MessageHandler<HeartbeatRequest> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, HeartbeatRequest message) {
        logger.info("[execute][收到连接({}) 的心跳请求]", channel.id());
        // 响应心跳
        HeartbeatResponse response = new HeartbeatResponse();
        channel.writeAndFlush(new Invocation(HeartbeatResponse.TYPE, response));
    }

    @Override
    public String getType() {
        return HeartbeatRequest.TYPE;
    }

}