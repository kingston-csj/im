package pers.kinson.im.chat.logic.friend.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.kinson.business.entity.FriendApply;
import pers.kinson.im.chat.data.dao.FriendApplyDao;
import pers.kinson.im.chat.logic.friend.message.vo.FriendApplyVo;
import pers.kinson.im.chat.logic.user.UserService;
import pers.kinson.im.chat.mapstruct.FriendApplyMapper;
import pers.kinson.im.common.constants.I18nConstants;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Service
public class ApplyService  {

    final byte APPLY_STATUS_REJECTED = 1;
    final byte APPLY_STATUS_AGREED = 2;

    @Autowired
    FriendApplyDao friendApplyDao;

    @Autowired
    FriendService friendService;

    @Autowired
    UserService userService;

    public int applyNewFriend(Long from, Long to, String remark) {
        List<FriendApply> existedApply = friendApplyDao.selectList(new LambdaQueryWrapper<FriendApply>().eq(FriendApply::getFromId, from)
                .eq(FriendApply::getToId, to)
                .eq(FriendApply::getStatus, 0));
        if (!CollectionUtils.isEmpty(existedApply)) {
            return I18nConstants.FRIEND_APPLY_ALREADY;
        }
//        if (friendService.isMyFriend(from, to)) {
//            return I18nConstants.FRIEND_ALREADY;
//        }

        FriendApply newApply = new FriendApply();
        newApply.setFromId(from);
        newApply.setToId(to);
        newApply.setRemark(remark);
        newApply.setDate(new Date(System.currentTimeMillis()));
        friendApplyDao.insert(newApply);
        return 0;
    }

    /**
     * 获取申请列表
     *
     * @param userId  目标用户id
     * @param checked 是否检查过，如果为true代表查询所有;如果为false,代表只要状态为0的记录
     * @return
     */
    public List<FriendApplyVo> fetchApplyRecords(Long userId, boolean checked) {
        LambdaQueryWrapper<FriendApply> queryWrapper = new LambdaQueryWrapper<FriendApply>()
                .and(wrapper -> wrapper.eq(FriendApply::getToId, userId).or().eq(FriendApply::getFromId, userId))
                .eq(FriendApply::getStatus, 0);
        if (checked) {
            queryWrapper = new LambdaQueryWrapper<FriendApply>().eq(FriendApply::getToId, userId).or().eq(FriendApply::getFromId, userId);
        }
        List<FriendApply> friendApplies = friendApplyDao.selectList(queryWrapper);
        if (friendApplies.isEmpty()) {
            return Collections.emptyList();
        }
        return friendApplies.stream().map(e -> {
            FriendApplyVo vo = FriendApplyMapper.INSTANCE.toVo(e);
            vo.setFromName(userService.getUserName(e.getFromId()));
            vo.setToName(userService.getUserName(e.getToId()));
            return vo;
        }).toList();
    }


//    @Override
//    public void register(Long userId, Map<Integer, RedPoint> points) {
//        List<FriendApplyVo> applyVos = fetchApplyRecords(userId, false);
//        if (!CollectionUtils.isEmpty(applyVos)) {
//            points.put(RedPointId.FRIEND_APPLY, RedPoint.builder().id(RedPointId.FRIEND_APPLY).build());
//        }
//    }
}
