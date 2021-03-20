package pers.kinson.im.chat.dispatch;

import pers.kinson.im.chat.net.IoSession;
import pers.kinson.im.chat.net.message.AbstractPacket;

public abstract class MessageHandler<E extends AbstractPacket> {

    public abstract void action(IoSession session, E req);
}
