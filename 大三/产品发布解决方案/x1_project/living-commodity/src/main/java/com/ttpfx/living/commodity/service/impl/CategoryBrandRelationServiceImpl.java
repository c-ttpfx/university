package com.ttpfx.living.commodity.service.impl;

import com.ttpfx.living.commodity.entity.BrandEntity;
import com.ttpfx.living.commodity.service.BrandService;
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

import com.ttpfx.living.commodity.dao.CategoryBrandRelationDao;
import com.ttpfx.living.commodity.entity.CategoryBrandRelationEntity;
import com.ttpfx.living.commodity.service.CategoryBrandRelationService;

import javax.annotation.Resource;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Resource
    private BrandService brandService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryBrandRelationEntity> queryAllByBrandId(Long brandId) {
        List<CategoryBrandRelationEntity> list = list(new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));
        return list;
    }

    @Override
    public List<BrandEntity> getBrandListByCategoryId(Long categoryId) {
        List<BrandEntity> brandEntityList = list(new QueryWrapper<CategoryBrandRelationEntity>()
                .eq("category_id", categoryId))
                .stream()
                .map(relation -> brandService.getById(relation.getBrandId()))
                .collect(Collectors.toList());
        return brandEntityList;
    }

}