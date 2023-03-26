package com.ttpfx.living.commodity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ttpfx.living.commodity.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * @author ttpfx
 * @date 2023/2/26
 */
@Data
public class AttrGroupWithAttrsVo {
    private Long id;
    /**
     * 组名
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 说明
     */
    private String description;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类 id
     */
    private Long categoryId;

    private List<AttrEntity> attrs;
}
