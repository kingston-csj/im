package pers.kinson.im.chat.config;

import jforgame.codec.struct.StructMessageCodec;
import jforgame.socket.netty.support.server.TcpSocketServerBuilder;
import jforgame.socket.share.DispatchThreadModel;
import jforgame.socket.share.HostAndPort;
import jforgame.socket.share.ServerNode;
import jforgame.socket.share.ThreadModel;
import jforgame.socket.share.message.MessageFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.kinson.im.chat.ServerConfigs;
import pers.kinson.im.chat.net.BaseMessageFactory;
import pers.kinson.im.chat.net.MessageIoDispatcher;

@Configuration
public class SocketServerAutoConfiguration {


    @Bean
    public ServerNode createNetty(MessageFactory messageFactory, ServerConfigs serverConfigs) {
        return TcpSocketServerBuilder.newBuilder()
                .bindingPort(HostAndPort.valueOf(serverConfigs.getSocketPort()))
                .setMessageFactory(messageFactory)
                .setMessageCodec(new StructMessageCodec())
                .setSocketIoDispatcher(new MessageIoDispatcher())
                .build();
    }

    @Bean
    public BaseMessageFactory createMessageFactory() {
        return new BaseMessageFactory("pers.kinson.im.chat");
    }

    @Bean
    public ThreadModel createThreadModel() {
        return new DispatchThreadModel();
    }


}