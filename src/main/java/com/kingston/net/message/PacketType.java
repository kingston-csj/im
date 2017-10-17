package com.kingston.net.message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.kingston.logic.chat.message.ReqChatPacket;
import com.kingston.logic.chat.message.RespChatPacket;
import com.kingston.logic.friend.message.RespFriendListPacket;
import com.kingston.logic.login.message.ReqHeartBeatPacket;
import com.kingston.logic.login.message.ReqUserLoginPacket;
import com.kingston.logic.login.message.RespHeartBeatPacket;
import com.kingston.logic.login.message.RespUserLoginPacket;
import com.kingston.logic.user.message.ReqUserRegisterPacket;
import com.kingston.logic.user.message.ResUserRegisterPacket;

public enum PacketType {

	//业务上行数据包


	//链接心跳包
	ReqHeartBeat((short)0x0001, ReqHeartBeatPacket.class),
	//新用户注册
	ReqUserRegister((short)0x0100, ReqUserRegisterPacket.class),
	//用户登陆
	ReqUserLogin((short)0x0101, ReqUserLoginPacket.class),
	//聊天
	ReqChat((short)0x0102, ReqChatPacket.class),


	//业务下行数据包


	RespHeartBeat((short)0x2001, RespHeartBeatPacket.class),

	//新用户注册
	ResUserRegister((short)0x2100, ResUserRegisterPacket.class),

	RespLogin((short)0x2102, RespUserLoginPacket.class),

	RespChat((short)0x2103, RespChatPacket.class),
	/** 好友列表 */
	RespFriendList((short)0x2104, RespFriendListPacket.class);

	;

	private short type;
	private Class<? extends AbstractPacket> packetClass;
	private static Map<Short,Class<? extends AbstractPacket>> PACKET_CLASS_MAP = new HashMap<Short,Class<? extends AbstractPacket>>();

	public static void initPackets() {
		Set<Short> typeSet = new HashSet<Short>();
		Set<Class<?>> packets = new HashSet<>();
		for(PacketType p:PacketType.values()){
			Short type = p.getType();
			if(typeSet.contains(type)){
				throw new IllegalStateException("packet type 协议类型重复"+type);
			}
			Class<?> packet = p.getPacketClass();
			if (packets.contains(packet)) {
				throw new IllegalStateException("packet定义重复"+p);
			}
			PACKET_CLASS_MAP.put(type,p.getPacketClass());
			typeSet.add(type);
			packets.add(packet);
		}
	}

	PacketType(short type,Class<? extends AbstractPacket> packetClass){
		this.setType(type);
		this.packetClass = packetClass;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public Class<? extends AbstractPacket> getPacketClass() {
		return packetClass;
	}

	public void setPacketClass(Class<? extends AbstractPacket> packetClass) {
		this.packetClass = packetClass;
	}


	public static  Class<? extends AbstractPacket> getPacketClassBy(short packetType){
		return PACKET_CLASS_MAP.get(packetType);
	}

	public static void main(String[] args) {
		for(PacketType p:PacketType.values()){
			System.err.println(p.getPacketClass().getSimpleName());
		}
	}

}
