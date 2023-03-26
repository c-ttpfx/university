package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.AttrAttrgroupRelationEntity;

import java.util.Map;

/**
 * 商品属性和商品属性组的关联表
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-20 21:25:11
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    AttrAttrgroupRelationEntity getByAttrId(Long attrId);
}

