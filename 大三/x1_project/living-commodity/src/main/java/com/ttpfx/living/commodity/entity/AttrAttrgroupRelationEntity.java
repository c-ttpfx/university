package com.ttpfx.living.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品属性和商品属性组的关联表
 * 
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-20 21:25:11
 */
@Data
@TableName("commodity_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 属性 id
	 */
	private Long attrId;
	/**
	 * 属性分组 id
	 */
	private Long attrGroupId;
	/**
	 * 属性组内排序
	 */
	private Integer attrSort;

}
