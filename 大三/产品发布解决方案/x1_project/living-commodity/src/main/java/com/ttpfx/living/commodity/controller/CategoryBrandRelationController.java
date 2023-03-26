package com.ttpfx.living.commodity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.ttpfx.living.commodity.entity.BrandEntity;
import com.ttpfx.living.commodity.service.BrandService;
import com.ttpfx.living.commodity.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttpfx.living.commodity.entity.CategoryBrandRelationEntity;
import com.ttpfx.living.commodity.service.CategoryBrandRelationService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.R;

import javax.annotation.Resource;


/**
 * 品牌分类关联表
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-19 10:29:13
 */
@RestController
@RequestMapping("commodity/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Resource
    private CategoryService categoryService;
    @Resource
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:categorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/list/brandAllCategory/{brandId}")
//    @RequiresPermissions("commodity:categorybrandrelation:list")
    public R queryAllByBrandId(@PathVariable("brandId") Long brandId){
        List<CategoryBrandRelationEntity> categoryBrandRelationEntities = categoryBrandRelationService.queryAllByBrandId(brandId);
        return R.ok().put("data", categoryBrandRelationEntities);
    }

    @RequestMapping("/brands/list")
    public R brandListByCategoryId(@RequestParam("catId") Long categoryId){
        List<BrandEntity> brandEntityList = categoryBrandRelationService.getBrandListByCategoryId(categoryId);
        return R.ok().put("data", brandEntityList);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:categorybrandrelation:info")
    public R info(@PathVariable("id") Long id){
		CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.save(categoryBrandRelation);

        return R.ok();
    }

    @RequestMapping("/saveAll")
//    @RequiresPermissions("commodity:categorybrandrelation:save")
    public R saveAll(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
        String brandName = brandService.getById(categoryBrandRelation.getBrandId()).getName();
        String categoryName = categoryService.getById(categoryBrandRelation.getCategoryId()).getName();
        categoryBrandRelation.setBrandName(brandName);
        categoryBrandRelation.setCategoryName(categoryName);
        categoryBrandRelationService.save(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:categorybrandrelation:update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids){
		categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
