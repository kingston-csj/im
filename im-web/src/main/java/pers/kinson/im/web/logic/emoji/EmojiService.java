package pers.kinson.im.web.logic.emoji;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.kinson.business.entity.OssResource;
import pers.kinson.im.oss.OssService;
import pers.kinson.im.web.data.dao.OssResourceDao;

import java.util.List;

@Service
public class EmojiService {

    @Autowired
    OssResourceDao ossResourceDao;

    @Autowired
    OssService ossService;

    public List<EmojiVo> listAllEmoji() {
        List<OssResource> resources = ossResourceDao.selectList(new LambdaQueryWrapper<OssResource>().eq(OssResource::getType, "emoji"));
        return resources.stream().map(e -> {
            EmojiVo vo = new EmojiVo();
            vo.setLabel(e.getLabel());
            vo.setUrl(ossService.fullOssPath(e.getUrl()));
            return vo;
        }).toList();
    }
}
