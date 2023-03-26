package com.ttpfx.living.commodity.dao;

import com.ttpfx.living.commodity.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类表
 * 
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-03 03:01:01
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
