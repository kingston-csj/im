package com.kingston.ball;

import com.kingston.net.Packet;

public enum SyncPosManager {
	INSTANCE;
	private SyncPosCenter center = new SyncPosCenter();
	
	public void addTeamMember(User user){
		this.center.addTeamMember(user);
	}
	
	public void receiveMessage(Packet message){
		
		this.center.receiveMessage(message);
	}
}
