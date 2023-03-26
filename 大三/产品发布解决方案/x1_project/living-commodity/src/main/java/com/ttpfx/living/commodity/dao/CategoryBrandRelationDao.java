package com.ttpfx.living.commodity.dao;

import com.ttpfx.living.commodity.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌分类关联表
 * 
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-19 10:29:13
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {
	
}
