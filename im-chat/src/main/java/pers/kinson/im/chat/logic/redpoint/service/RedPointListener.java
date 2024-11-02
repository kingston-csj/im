package pers.kinson.im.chat.logic.redpoint.service;

import pers.kinson.im.chat.logic.redpoint.message.vo.RedPoint;

import java.util.Map;

public interface RedPointListener {

    void register(Long userId, Map<Integer, RedPoint> points);
}
