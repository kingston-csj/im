package pers.kinson.im.chat.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pers.kinson.im.chat.data.view.DiscussionGroupView;
import pers.kinson.im.chat.logic.discussion.message.vo.DiscussionGroupVo;

@Mapper
public interface DiscussionMapper {

    DiscussionMapper INSTANCE = Mappers.getMapper(DiscussionMapper.class);

    DiscussionGroupVo toVo(DiscussionGroupView from);
}