package io.zengande.nettydemo.common.dispatcher;

import io.netty.channel.Channel;
import io.zengande.nettydemo.common.message.Message;

public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     *
     * @param channel 通道
     * @param message 消息
     */
    void execute(Channel channel, T message);

    /**
     * @return 消息类型，即每个 Message 实现类上的 TYPE 静态字段
     */
    String getType();

}
