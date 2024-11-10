package pers.kinson.im.chat.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.kinson.im.chat.data.model.Message;

import java.util.List;

@Repository
public interface MessageDao extends BaseMapper<Message> {

    List<Message> fetchNew(byte channelType, String receiver, long maxSeq);
}
