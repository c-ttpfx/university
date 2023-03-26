package com.ttpfx.living.commodity.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ttpfx.common.valid.SaveGroup;
import com.ttpfx.common.valid.UpdateGroup;
import com.ttpfx.common.valid.UpdateIsShowGroup;
import com.ttpfx.living.commodity.entity.CategoryBrandRelationEntity;
import com.ttpfx.living.commodity.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttpfx.living.commodity.entity.BrandEntity;
import com.ttpfx.living.commodity.service.BrandService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.R;

import javax.annotation.Resource;


/**
 * 家居品牌
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-10 00:32:46
 */
@RestController
@RequestMapping("commodity/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:brand:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:brand:info")
    public R info(@PathVariable("id") Long id) {
        BrandEntity brand = brandService.getById(id);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:brand:save")
    public R save(@Validated({SaveGroup.class}) @RequestBody BrandEntity brand) {
        brandService.save(brand);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:brand:update")
    @Transactional
    public R update(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);
        categoryBrandRelationService.update(new UpdateWrapper<CategoryBrandRelationEntity>()
                .eq("brand_id", brand.getId())
                .set(true, "brand_name", brand.getName()));
        return R.ok();
    }

    @RequestMapping("/update/isshow")
//    @RequiresPermissions("commodity:brand:save")
    public R changeIsshow(@Validated({UpdateIsShowGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:brand:delete")
    public R delete(@RequestBody Long[] ids) {
        brandService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
