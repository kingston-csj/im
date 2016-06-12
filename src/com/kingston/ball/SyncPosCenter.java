package com.kingston.ball;

import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kingston.base.ServerManager;
import com.kingston.net.Packet;
import com.kingston.util.StringUtil;

public class SyncPosCenter implements MessageSubject{
	private List<User> users = new ArrayList<User>();
	private Packet message;

	@Override
	public void addTeamMember(User user) {
		if(user!= null && !users.contains(user)){
			this.users.add(user);
		}
	}

	@Override
	public void notifyMembers() {
		if(message != null){
			users.stream()
//			.filter(user -> user.userId == message.getUserId())
			.forEach(user->user.receiverMessage(message));
		}
	}

	@Override
	public void receiveMessage(Packet message) {
//		Map<Integer,ChannelHandlerContext> contextMap  = ServerManager.INSTANCE.getAllOnlineContext();
//		if(StringUtil.isEmpty(contextMap)) return;
//		
//		for(Map.Entry<Integer,ChannelHandlerContext> aEntry :contextMap.entrySet() ){
//			if(aEntry.getKey()  != syncPact.getUserId()){
//				ServerManager.INSTANCE.sendPacketTo(syncPact, aEntry.getKey());
//			}
//		}
		this.message = message;
		notifyMembers();
	}
	

}
