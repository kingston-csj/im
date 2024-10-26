package pers.kinson.im.chat.logic.friend;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.kinson.im.chat.data.dao.FriendApplyDao;
import pers.kinson.im.chat.data.model.FriendApply;
import pers.kinson.im.common.constants.I18nConstants;

import java.sql.Date;
import java.util.List;

@Service
public class ApplyService {

    final byte APPLY_STATUS_REJECTED = 1;
    final byte APPLY_STATUS_AGREED = 2;

    @Autowired
    FriendApplyDao friendApplyDao;

    @Autowired
    FriendService friendService;

    public int applyNewFriend(Long from, Long to, String remark) {
        List<FriendApply> existedApply = friendApplyDao.selectList(new LambdaQueryWrapper<FriendApply>().eq(FriendApply::getFromId, from)
                .eq(FriendApply::getToId, to)
                .eq(FriendApply::getStatus, 0));
        if (!CollectionUtils.isEmpty(existedApply)) {
            return I18nConstants.FRIEND_APPLY_ALREADY;
        }
        if (friendService.isMyFriend(from, to)) {
            return I18nConstants.FRIEND_ALREADY;
        }

        FriendApply newApply = new FriendApply();
        newApply.setFromId(from);
        newApply.setToId(to);
        newApply.setRemark(remark);
        newApply.setDate(new Date(System.currentTimeMillis()));
        friendApplyDao.insert(newApply);
        return 0;
    }

}
