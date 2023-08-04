package io.zengande.nettydemo.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.zengande.nettydemo.common.codec.InvocationDecoder;
import io.zengande.nettydemo.common.codec.InvocationEncoder;
import io.zengande.nettydemo.common.dispatcher.MessageDispatcher;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class NettyServerHandlerInitializer extends ChannelInitializer<Channel> {
    /**
     * 心跳超时时间
     */
    private static final Integer READ_TIMEOUT_SECONDS = 3 * 60;

    private final MessageDispatcher messageDispatcher;
    private final NettyServerHandler nettyServerHandler;

    public NettyServerHandlerInitializer(MessageDispatcher messageDispatcher, NettyServerHandler nettyServerHandler) {
        this.messageDispatcher = messageDispatcher;
        this.nettyServerHandler = nettyServerHandler;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline
                // 空闲检测
                .addLast(new ReadTimeoutHandler(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS))
                // 编码器
                .addLast(new InvocationEncoder())
                // 解码器
                .addLast(new InvocationDecoder())
                // 消息分发器
                .addLast(messageDispatcher)
                // 服务端处理器
                .addLast(nettyServerHandler);
    }
}
