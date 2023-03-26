package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.CategoryEntity;
import com.ttpfx.living.commodity.vo.Catalog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-03 03:01:01
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
    Long[] getCascadedCategoryId(Long categoryId);


    List<CategoryEntity> getCategoryLevel(int level);

    Map<String, List<Catalog2Vo>> getCatalogJson();
}

