package com.kingston.ball;

import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.kingston.base.ServerManager;
import com.kingston.net.Packet;
import com.kingston.net.PacketType;
import com.kingston.util.StringUtil;

public class ServerSyncPos extends Packet {

	private int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	private int[] myPos = null;

	public ServerSyncPos(){

	}

	public ServerSyncPos(int userId,int[] myPos){
		this.userId = userId;
		this.myPos = myPos;
	}

	@Override
	public void writePacketMsg(ByteBuf buf) {
		buf.writeInt(userId);
		buf.writeInt(myPos[0]);
		buf.writeInt(myPos[1]);

	}

	@Override
	public void readFromBuff(ByteBuf buf) {
		this.userId = buf.readInt();
		int[] pos = new int[2];
		pos[0] = buf.readInt();
		pos[1] = buf.readInt();
		this.myPos = pos;

	}

	@Override
	public PacketType getPacketType() {
		// TODO Auto-generated method stub
		return PacketType.ServerSyncPos;
	}

	@Override
	public void execPacket() {
		ClientSyncPos syncPact = new ClientSyncPos(userId,myPos);
//		ServerManager.sendPacketToAllUsers(syncPact);
		SyncPosManager.INSTANCE.receiveMessage(syncPact);

	}

}
