package com.ttpfx.living.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 品牌分类关联表
 * 
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-19 10:29:13
 */
@Data
@TableName("commodity_category_brand_relation")
public class CategoryBrandRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 品牌 id
	 */
	private Long brandId;
	/**
	 * 分类 id
	 */
	private Long categoryId;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 分类名称
	 */
	private String categoryName;

}
