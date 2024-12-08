package pers.kinson.im.chat.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.kinson.im.chat.data.model.OssResource;

import java.util.List;

@Repository
public interface OssResourceDao extends BaseMapper<OssResource> {

    List<OssResource> clearExpired();
}
