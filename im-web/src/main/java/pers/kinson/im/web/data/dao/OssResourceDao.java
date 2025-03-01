package pers.kinson.im.web.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.kinson.business.entity.OssResource;

import java.util.List;

@Repository
public interface OssResourceDao extends BaseMapper<OssResource> {


    List<OssResource> selectByType(String type);

    List<OssResource> clearExpired();

}
