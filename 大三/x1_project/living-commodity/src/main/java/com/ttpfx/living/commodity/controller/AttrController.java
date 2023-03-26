package com.ttpfx.living.commodity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttpfx.living.commodity.entity.AttrAttrgroupRelationEntity;
import com.ttpfx.living.commodity.service.AttrAttrgroupRelationService;
import com.ttpfx.living.commodity.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttpfx.living.commodity.entity.AttrEntity;
import com.ttpfx.living.commodity.service.AttrService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.R;

import javax.annotation.Resource;


/**
 * 商品属性表
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-20 19:22:18
 */
@RestController
@RequestMapping("commodity/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:attr:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
//    @RequiresPermissions("commodity:attr:info")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrEntity attr = attrService.getById(attrId);
        try {
            Long categoryId = attr.getCategoryId();
            Long[] cascadedCategoryId = categoryService.getCascadedCategoryId(categoryId);
            attr.setCascadedCategoryId(cascadedCategoryId);
            attr.setAttrGroupId(attrAttrgroupRelationService.getByAttrId(attrId).getAttrGroupId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok().put("attr", attr);
    }

    @RequestMapping("/sale/list/{catalogId}")
    public R saleAttrByCategoryId(@RequestParam Map<String, Object> params,@PathVariable("catalogId") Long categoryId) {
        PageUtils pageUtils = attrService.getSaleAttrByCategory(params,categoryId);
        return R.ok().put("page", pageUtils);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:attr:save")
    public R save(@RequestBody AttrEntity attr) {
//		attrService.save(attr);
        attrService.saveAttrAndRelation(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:attr:update")
    public R update(@RequestBody AttrEntity attr) {
        attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:attr:delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIdsAndRelation(attrIds);

        return R.ok();
    }

}
