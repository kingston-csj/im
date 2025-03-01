package pers.kinson.im.chat.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pers.kinson.business.entity.FriendApply;
import pers.kinson.im.chat.logic.friend.message.vo.FriendApplyVo;

@Mapper
public interface FriendApplyMapper {

    FriendApplyMapper INSTANCE = Mappers.getMapper(FriendApplyMapper.class);

    FriendApplyVo toVo(FriendApply from);
}
