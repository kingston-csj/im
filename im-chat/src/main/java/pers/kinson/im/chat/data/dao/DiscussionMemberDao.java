package pers.kinson.im.chat.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.kinson.im.chat.data.model.Discussion;
import pers.kinson.im.chat.data.model.DiscussionMember;

@Repository
public interface DiscussionMemberDao extends BaseMapper<DiscussionMember> {


}
