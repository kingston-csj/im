package pers.kinson.im.chat.net;

import jforgame.codec.MessageCodec;
import jforgame.codec.struct.StructMessageCodec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketAutoConfiguration {

    @Bean
    public MessageCodec createMessageCodec() {
        return new StructMessageCodec();
    }
}
