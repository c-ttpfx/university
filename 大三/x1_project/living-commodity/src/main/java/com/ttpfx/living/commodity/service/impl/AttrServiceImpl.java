package com.ttpfx.living.commodity.service.impl;

import com.ttpfx.living.commodity.dao.AttrAttrgroupRelationDao;
import com.ttpfx.living.commodity.dao.AttrgroupDao;
import com.ttpfx.living.commodity.entity.AttrAttrgroupRelationEntity;
import com.ttpfx.living.commodity.entity.AttrgroupEntity;
import com.ttpfx.living.commodity.service.AttrAttrgroupRelationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.Query;

import com.ttpfx.living.commodity.dao.AttrDao;
import com.ttpfx.living.commodity.entity.AttrEntity;
import com.ttpfx.living.commodity.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Resource
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Resource
    private AttrAttrgroupRelationDao relationDao;
    @Resource
    private AttrgroupDao attrgroupDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.hasText(key)) {
            wrapper.and(obj -> obj.eq("attr_id", key).or().like("attr_name", key));
        }
        Object baseAttr = params.get("isBaseAttr");
        if (baseAttr != null) {
            if (Boolean.parseBoolean((String) baseAttr)) wrapper.eq("attr_type", 1);
            else wrapper.eq("attr_type", 0);
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttrAndRelation(AttrEntity attr) {
        this.save(attr);
        if (attr.getAttrType() == 1 && attr.getAttrGroupId() != null) {
            AttrAttrgroupRelationEntity relationEntity = new
                    AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());
            attrAttrgroupRelationDao.insert(relationEntity);
        }
    }

    @Override
    public void removeByIdsAndRelation(Long[] attrIds) {
        removeByIds(Arrays.asList(attrIds));
        attrAttrgroupRelationService.remove(
                new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_id", attrIds));
    }

    @Override
    public List<AttrEntity> relationByGroupId(Long groupId) {
        QueryWrapper<AttrAttrgroupRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_group_id", groupId);
        List<Long> attrIds = attrAttrgroupRelationDao.selectList(queryWrapper).stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        if (attrIds.size() == 0) return null;
        Collection<AttrEntity> attrEntities = this.listByIds(attrIds);
        return (List<AttrEntity>) attrEntities;
    }

    @Override
    public void deleteRelation(AttrAttrgroupRelationEntity[] entities) {
        relationDao.deleteBatchRelation
                (Arrays.asList(entities));
    }

    @Override
    public PageUtils getAllowRelationAttr(Map<String, Object> params, Long attrGroupId) {
        Long categoryId = attrgroupDao.selectById(attrGroupId).getCategoryId();

        List<AttrgroupEntity> group = attrgroupDao.selectList(new
                QueryWrapper<AttrgroupEntity>().eq("category_id", categoryId));
        List<Long> collect = group.stream()
                .map(AttrgroupEntity::getId)
                .collect(Collectors.toList());

        List<Long> attrIds = attrAttrgroupRelationDao.selectList(new
                        QueryWrapper<AttrAttrgroupRelationEntity>()
                        .in("attr_group_id", collect))
                .stream().map(AttrAttrgroupRelationEntity::getAttrId)
                .collect(Collectors.toList());


        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.eq("attr_type", 1);
        if (attrIds.size() != 0) queryWrapper.notIn("attr_id", attrIds);

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((w) -> {
                w.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page =
                this.page(new Query<AttrEntity>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public PageUtils getSaleAttrByCategory(Map<String, Object> params, Long categoryId) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_type", 0);
        wrapper.eq("category_id", categoryId);
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}