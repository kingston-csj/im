package pers.kinson.im.chat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ServerConfigs {

	/** 服务器端口 */
	@Value("${socket.port}")
	private int socketPort;

}