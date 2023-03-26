package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.BrandEntity;
import com.ttpfx.living.commodity.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联表
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-19 10:29:13
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryBrandRelationEntity> queryAllByBrandId(Long brandId);

   List<BrandEntity> getBrandListByCategoryId(Long categoryId);
}

