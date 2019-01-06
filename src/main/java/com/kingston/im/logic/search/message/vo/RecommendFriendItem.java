package com.kingston.im.logic.search.message.vo;


import com.kingston.im.net.message.ByteBufBean;

import io.netty.buffer.ByteBuf;

public class RecommendFriendItem extends ByteBufBean {

	private long userId;

	private String nickName;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeLong(userId);
		writeUTF8(buf, nickName);
	}

	@Override
	public void readBody(ByteBuf buf) {
		userId = buf.readLong();
		nickName = readUTF8(buf);
	}

}