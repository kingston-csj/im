package com.kingston.ball;

import com.kingston.net.Packet;

public interface MessageSubject {
	
	public void addTeamMember(User user);
	
	public void notifyMembers();
	
	public void receiveMessage(Packet message);

}
