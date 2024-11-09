package pers.kinson.im.chat.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pers.kinson.im.chat.data.model.DiscussionMember;
import pers.kinson.im.chat.data.view.DiscussionGroupView;
import pers.kinson.im.chat.logic.discussion.message.vo.DiscussionGroupVo;
import pers.kinson.im.chat.logic.discussion.message.vo.DiscussionMemberVo;

@Mapper
public interface DiscussionMemberMapper {

    DiscussionMemberMapper INSTANCE = Mappers.getMapper(DiscussionMemberMapper.class);

    DiscussionMemberVo toVo(DiscussionMember from);
}