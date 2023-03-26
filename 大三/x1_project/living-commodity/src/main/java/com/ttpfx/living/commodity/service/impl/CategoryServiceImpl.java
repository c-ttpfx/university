package com.ttpfx.living.commodity.service.impl;

import com.ttpfx.living.commodity.vo.Catalog2Vo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.Query;

import com.ttpfx.living.commodity.dao.CategoryDao;
import com.ttpfx.living.commodity.entity.CategoryEntity;
import com.ttpfx.living.commodity.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long[] getCascadedCategoryId(Long categoryId) {
        List<Long> list = new ArrayList<>();
        Long pid;
        do {
            pid = this.getById(categoryId).getParentId();
            list.add(categoryId);
            categoryId = pid;
        } while (pid != 0);
        Collections.reverse(list);
        return list.toArray(new Long[list.size()]);
    }

    @Override
    public List<CategoryEntity> getCategoryLevel(int level) {
        return list(new QueryWrapper<CategoryEntity>().eq("cat_level", level));
    }

    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        List<CategoryEntity> categoryLevel1 = getCategoryLevel(1);
        List<CategoryEntity> categoryLevel2 = getCategoryLevel(2);
        List<CategoryEntity> categoryLevel3 = getCategoryLevel(3);
        Map<String, List<Catalog2Vo>> collect = categoryLevel1
                .stream()
                .collect(Collectors.toMap(k -> k.getId().toString(), v -> {
                    List<Catalog2Vo> catalog2Vos = categoryLevel2
                            .stream()
                            .filter(c -> Objects.equals(c.getParentId(), v.getId()))
                            .map(categoryEntity2 -> {
                                List<Catalog2Vo.Category3Vo> collectLevel3 = categoryLevel3
                                        .stream()
                                        .filter(categoryEntity3 -> Objects.equals(categoryEntity3.getParentId(), categoryEntity2.getId()))
                                        .map(categoryEntity3 -> {
                                            Catalog2Vo.Category3Vo category3Vo = new Catalog2Vo.Category3Vo();
                                            category3Vo.setCatalog2Id(categoryEntity3.getParentId().toString());
                                            category3Vo.setId(categoryEntity3.getId().toString());
                                            category3Vo.setName(categoryEntity3.getName());
                                            return category3Vo;
                                        })
                                        .collect(Collectors.toList());
                                Catalog2Vo catalog2Vo = new Catalog2Vo();
                                catalog2Vo.setCatalog3List(collectLevel3);
                                catalog2Vo.setCatalog1Id(v.getId().toString());
                                catalog2Vo.setId(categoryEntity2.getId().toString());
                                catalog2Vo.setName(categoryEntity2.getName());
                                return catalog2Vo;
                            }).collect(Collectors.toList());
                    return catalog2Vos;
                }));
        return collect;
    }


}