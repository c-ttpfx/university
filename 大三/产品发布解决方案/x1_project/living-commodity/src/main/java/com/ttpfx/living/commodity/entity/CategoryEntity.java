package com.ttpfx.living.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.Data;

/**
 * 商品分类表
 * 
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-03 03:01:01
 */
@Data
@TableName("commodity_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父分类 id
	 */
	private Long parentId;
	/**
	 * 层级
	 */
	private Integer catLevel;
	/**
	 * 0 不显示，1 显示]
	 */
	@TableLogic
	private Integer isShow;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 统计单位
	 */
	private String proUnit;
	/**
	 * 商品数量
	 */
	private Integer proCount;

	/**
	 * 子节点
	 */
	@TableField(exist = false)
	private Set<CategoryEntity> childrenCategories;
}
