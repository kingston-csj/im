package pers.kinson.im.chat.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;
import pers.kinson.im.chat.data.model.Message;

import java.util.List;

@Repository
public interface MessageDao extends BaseMapper<Message> {

    List<Message> fetchNew(byte channelType, long receiver, long maxSeq);

    List<Message> fetchNewPersonal(long receiver, long maxSeq);

    @Delete("DELETE FROM message WHERE date < NOW() - INTERVAL 7 DAY")
    int clearExpiredMessage();
}
