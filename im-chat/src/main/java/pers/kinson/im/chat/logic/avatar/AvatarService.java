package pers.kinson.im.chat.logic.avatar;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.kinson.im.chat.data.dao.OssResourceDao;
import pers.kinson.im.chat.data.model.OssResource;
import pers.kinson.im.chat.logic.emoji.EmojiVo;
import pers.kinson.im.oss.OssService;

import java.util.List;

@Service
public class AvatarService {

    @Autowired
    OssResourceDao ossResourceDao;

    @Autowired
    OssService ossService;

    public List<EmojiVo> listAllAvatar() {
        List<OssResource> resources = ossResourceDao.selectList(new LambdaQueryWrapper<OssResource>().eq(OssResource::getType, "avatar"));
        return resources.stream().filter(e -> "official".equals(e.getSource())).map(e -> {
            EmojiVo vo = new EmojiVo();
            vo.setLabel(e.getLabel());
            vo.setUrl(ossService.fullOssPath(e.getUrl()));
            return vo;
        }).toList();
    }
}
