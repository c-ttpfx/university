package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.AttrAttrgroupRelationEntity;
import com.ttpfx.living.commodity.entity.AttrgroupEntity;
import com.ttpfx.living.commodity.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

/**
 * 家居商品属性分组
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-16 00:45:50
 */
public interface AttrgroupService extends IService<AttrgroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params,Long categoryId);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCategoryId(Long categoryId);
}

