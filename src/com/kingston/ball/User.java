package com.kingston.ball;

import javax.swing.JPanel;

import com.kingston.base.ServerManager;
import com.kingston.net.Packet;

/**
 * Created by chenshujin on 2016/5/25.
 */
public class User extends JPanel implements MessageObserver {

	public int userId;
	public int x=200;
	public int y=100;

	public User()
	{
	}

	public User(int userId){
		this.userId = userId;
	}
	
	public User(int userId,int x,int y)
	{
		this.userId = userId;
		this.x  = x;
		this.y = y;
	}

	public void TurnLeft()
	{
		x=x-5;
		repaint();

	}
	public void TurnRight()
	{
		x=x+5;
		repaint();
	}
	public void turnup()
	{
		y=y-5;
		repaint();
	}
	public void TurnDown()
	{
		y=y+5;
		repaint();
	}

	@Override
	public void receiverMessage(Packet message) {
		ServerManager.INSTANCE.sendPacketTo(message, this.userId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		return true;
	}


}
