package com.kingston.ball;

import java.util.Map;

import io.netty.buffer.ByteBuf;

import com.kingston.net.Packet;
import com.kingston.net.PacketType;

public class ClientSyncPos extends Packet{

	private int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	private int[] oppPos = null;

	public ClientSyncPos(){

	}

	public ClientSyncPos(int userId,int[] oppPos){
		this.userId = userId;
		this.oppPos = oppPos;
	}

	@Override
	public void writePacketMsg(ByteBuf buf) {
		buf.writeInt(userId);
		buf.writeInt(oppPos[0]);
		buf.writeInt(oppPos[1]);

	}

	@Override
	public void readFromBuff(ByteBuf buf) {
		this.userId = buf.readInt();
		int[] pos = new int[2];
		pos[0] = buf.readInt();
		pos[1] = buf.readInt();
		this.oppPos = pos;
	}

	@Override
	public PacketType getPacketType() {
		// TODO Auto-generated method stub
		return PacketType.ClientSyncPos;
	}

	@Override
	public void execPacket() {

	}

}
