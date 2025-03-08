package pers.kinson.im.chat.base;

import io.netty.channel.Channel;
import jforgame.socket.share.IdSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.kinson.business.entity.User;
import pers.kinson.im.chat.net.ChannelUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum SessionManager {

    INSTANCE;

    private Logger logger = LoggerFactory.getLogger(SessionManager.class);

    /**
     * 缓存通信上下文环境对应的登录用户（主要用于服务）
     */
    private Map<IdSession, Long> session2UserIds = new ConcurrentHashMap<>();

    /**
     * 缓存用户id与对应的会话
     */
    private ConcurrentMap<Long, IdSession> userId2Sessions = new ConcurrentHashMap<>();

    /**
     * 向单一在线用户发送数据包
     */
    public void sendPacketTo(IdSession session, Object pact) {
        if (pact == null || session == null) return;
        session.send(pact);
    }

    public void sendPacketTo(User user, Object pact) {
        sendPacketTo(user.getUserId(), pact);
    }

    public void sendPacketTo(Long userId, Object pact) {
        if (pact == null || userId <= 0) return;

        IdSession session = userId2Sessions.get(userId);
        if (session != null) {
            session.send(pact);
        }
    }

    /**
     * 向所有在线用户发送数据包
     */
    public void notifyToAllOnlineUsers(Object pact) {
        if (pact == null) return;

        userId2Sessions.values().forEach(session -> session.send(pact));
    }

    public IdSession getSessionBy(long userId) {
        return this.userId2Sessions.get(userId);
    }

    public void registerSession(User user, IdSession session) {
        session.setAttribute("ID", user.getUserId());
        userId2Sessions.put(user.getUserId(), session);
        session2UserIds.put(session, user.getUserId());

        logger.info("[{}] registered...", user.getUserId());
    }

    /**
     * 注销用户通信渠道
     */
    public void unregisterUserContext(Channel context) {
        if (context == null) {
            return;
        }
        IdSession session = ChannelUtils.getSessionBy(context);
        Long userId = session2UserIds.remove(session);
        userId2Sessions.remove(userId);
        if (session != null) {
            try {
                session.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
