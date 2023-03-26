package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.AttrAttrgroupRelationEntity;
import com.ttpfx.living.commodity.entity.AttrEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品属性表
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-20 19:22:18
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttrAndRelation(AttrEntity attr);

    void removeByIdsAndRelation(Long[] ids);

     List<AttrEntity> relationByGroupId(Long groupId) ;

    void deleteRelation(AttrAttrgroupRelationEntity[] entities);

    public PageUtils getAllowRelationAttr(Map<String, Object> params, Long attrGroupId);

    PageUtils getSaleAttrByCategory(Map<String, Object> params,Long categoryId);
}

