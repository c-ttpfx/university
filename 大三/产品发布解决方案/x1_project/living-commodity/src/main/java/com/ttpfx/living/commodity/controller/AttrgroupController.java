package com.ttpfx.living.commodity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttpfx.living.commodity.entity.AttrAttrgroupRelationEntity;
import com.ttpfx.living.commodity.entity.AttrEntity;
import com.ttpfx.living.commodity.service.AttrAttrgroupRelationService;
import com.ttpfx.living.commodity.service.AttrService;
import com.ttpfx.living.commodity.service.CategoryService;
import com.ttpfx.living.commodity.vo.AttrGroupWithAttrsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttpfx.living.commodity.entity.AttrgroupEntity;
import com.ttpfx.living.commodity.service.AttrgroupService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.R;

import javax.annotation.Resource;


/**
 * 家居商品属性分组
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-16 00:45:50
 */
@RestController
@RequestMapping("commodity/attrgroup")
public class AttrgroupController {
    @Autowired
    private AttrgroupService attrgroupService;

    @Autowired
    private CategoryService categoryService;

    @Resource
    private AttrService attrService;

    @Resource
    private AttrAttrgroupRelationService relationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrgroupService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/{groupId}/attr/relation")
    public R attrRelation(@PathVariable("groupId") Long groupId) {
        List<AttrEntity> relation = attrService.relationByGroupId(groupId);

        return R.ok().put("data", relation);
    }

    @RequestMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrAttrgroupRelationEntity[] relationEntities) {
        attrService.deleteRelation(relationEntities);
        return R.ok();
    }

    @RequestMapping("/{attrGroupId}/noattr/relation")
    public R getValidRelation(Map<String, Object> params, @PathVariable("attrGroupId") Long attrGroupId) {
        PageUtils page = attrService.getAllowRelationAttr(params, attrGroupId);
        return R.ok().put("page", page);
    }

    @RequestMapping("/attr/relation")
    public R addRelation(@RequestBody AttrAttrgroupRelationEntity[] entities){
        relationService.saveBatch(Arrays.asList(entities));
        return R.ok();
    }

    @RequestMapping("/{catalogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catalogId") Long catalogId){
        List<AttrGroupWithAttrsVo> vos = attrgroupService.getAttrGroupWithAttrsByCategoryId(catalogId);
        return R.ok().put("data", vos);
    }

    @RequestMapping("/list/{categoryId}")
//    @RequiresPermissions("commodity:attrgroup:list")
    public R listByCategoryId(@RequestParam Map<String, Object> params, @PathVariable("categoryId") Long categoryId) {
        PageUtils page = attrgroupService.queryPage(params, categoryId);
        return R.ok().put("page", page);
    }

    @RequestMapping("/list/retrieval")
//    @RequiresPermissions("commodity:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params, Long categoryId) {
        PageUtils page = attrgroupService.queryPage(params, categoryId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:attrgroup:info")
    public R info(@PathVariable("id") Long id) {
        AttrgroupEntity attrgroup = attrgroupService.getById(id);
        try {
            Long categoryId = attrgroup.getCategoryId();
            Long[] cascadedCategoryId = categoryService.getCascadedCategoryId(categoryId);
            attrgroup.setCascadedCategoryId(cascadedCategoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok().put("attrgroup", attrgroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:attrgroup:save")
    public R save(@RequestBody AttrgroupEntity attrgroup) {
        attrgroupService.save(attrgroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:attrgroup:update")
    public R update(@RequestBody AttrgroupEntity attrgroup) {
        attrgroupService.updateById(attrgroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:attrgroup:delete")
    public R delete(@RequestBody Long[] ids) {
        attrgroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
