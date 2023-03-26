package com.ttpfx.living.commodity.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.ttpfx.living.commodity.dao.AttrAttrgroupRelationDao;
import com.ttpfx.living.commodity.dao.AttrDao;
import com.ttpfx.living.commodity.entity.AttrAttrgroupRelationEntity;
import com.ttpfx.living.commodity.entity.AttrEntity;
import com.ttpfx.living.commodity.service.AttrAttrgroupRelationService;
import com.ttpfx.living.commodity.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.Query;

import com.ttpfx.living.commodity.dao.AttrgroupDao;
import com.ttpfx.living.commodity.entity.AttrgroupEntity;
import com.ttpfx.living.commodity.service.AttrgroupService;
import org.springframework.util.StringUtils;
import sun.awt.util.IdentityArrayList;

import javax.annotation.Resource;


@Service("attrgroupService")
public class AttrgroupServiceImpl extends ServiceImpl<AttrgroupDao, AttrgroupEntity> implements AttrgroupService {

    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Resource
    private AttrDao attrDao;
    @Resource
    private AttrAttrgroupRelationService relationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrgroupEntity> page = this.page(
                new Query<AttrgroupEntity>().getPage(params),
                new QueryWrapper<AttrgroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
        QueryWrapper<AttrgroupEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.hasText(key)) {
            wrapper.and(obj -> obj.eq("id", key).or().like("name", key));
        }
        if (categoryId != 0) wrapper.eq("category_id", categoryId);

        IPage<AttrgroupEntity> page = this.page(
                new Query<AttrgroupEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCategoryId(Long categoryId) {
        List<AttrGroupWithAttrsVo> vos = list(new QueryWrapper<AttrgroupEntity>()
                .eq("category_id", categoryId))
                .stream()
                .map(attrGroupEntity -> {
                    AttrGroupWithAttrsVo vo = new AttrGroupWithAttrsVo();
                    BeanUtils.copyProperties(attrGroupEntity, vo);
                    List<AttrEntity> attrEntities = attrAttrgroupRelationDao.selectList(
                                    new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupEntity.getId()))
                            .stream()
                            .map(relationEntity -> {
                                return attrDao.selectById(relationEntity.getAttrId());
                            }).collect(Collectors.toList());
                    vo.setAttrs(attrEntities);
                    return vo;
                }).collect(Collectors.toList());
        return vos;
    }


}