package com.ttpfx.living.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.ttpfx.common.valid.EnumValidate;
import com.ttpfx.common.valid.SaveGroup;
import com.ttpfx.common.valid.UpdateGroup;
import com.ttpfx.common.valid.UpdateIsShowGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 家居品牌
 * 
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-10 00:32:46
 */
@Data
@TableName("commodity_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@NotNull(groups = {UpdateGroup.class, UpdateIsShowGroup.class})
	@Null(groups = {SaveGroup.class})
	@TableId
	private Long id;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名不能为空",groups = {SaveGroup.class,UpdateGroup.class})
	private String name;
	/**
	 * logo
	 */
	@NotBlank(message = "logo不能为空",groups = {SaveGroup.class,UpdateGroup.class})
	@URL(message = "logo不是合法的url",groups = {SaveGroup.class,UpdateGroup.class})
	private String logo;
	/**
	 * 说明
	 */
	@NotBlank(message = "描述不能为空",groups = {SaveGroup.class,UpdateGroup.class})
	private String description;
	/**
	 * 显示
	 */
	@NotNull(message = "显示值不能为空",groups = {SaveGroup.class,UpdateGroup.class,UpdateIsShowGroup.class})
	@EnumValidate(value = {0,1},groups = {SaveGroup.class,UpdateGroup.class,UpdateIsShowGroup.class})
	private Integer isshow;
	/**
	 * 检索首字母
	 */
	@NotBlank(message = "检索搜首字母不能为空",groups = {SaveGroup.class,UpdateGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须是一个字母",groups = {SaveGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(message = "排序值不能为空",groups = {SaveGroup.class,UpdateGroup.class})
	@Min(value = 0,message = "排序值必须大于0",groups = {SaveGroup.class,UpdateGroup.class})
	private Integer sort;

}
