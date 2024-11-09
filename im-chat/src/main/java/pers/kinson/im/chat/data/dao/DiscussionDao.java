package pers.kinson.im.chat.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.kinson.im.chat.data.model.Discussion;
import pers.kinson.im.chat.data.view.DiscussionGroupView;

import java.util.List;

@Repository
public interface DiscussionDao extends BaseMapper<Discussion> {

    List<DiscussionGroupView> getMyGroups(long userId);

}
