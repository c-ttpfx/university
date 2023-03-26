package com.ttpfx.living.commodity.controller;

import java.util.*;
import java.util.stream.Collectors;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttpfx.living.commodity.entity.CategoryEntity;
import com.ttpfx.living.commodity.service.CategoryService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.R;



/**
 * 商品分类表
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-03 03:01:01
 */
@RestController
@RequestMapping("commodity/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 树形结构
     */
    @RequestMapping("/list/tree")
    public R tree(){
        List<CategoryEntity> all = categoryService.list();
        List<CategoryEntity> tree = all.stream().filter(categoryEntity -> categoryEntity.getParentId() == 0).collect(Collectors.toList());
        HashMap<Long,CategoryEntity> hashMap = new HashMap<>();
        all.forEach(categoryEntity -> hashMap.put(categoryEntity.getId(), categoryEntity));
        all.forEach(categoryEntity -> {
            if (categoryEntity.getParentId() != 0){
                CategoryEntity parent = hashMap.get(categoryEntity.getParentId());
                if (parent.getChildrenCategories() == null)parent.setChildrenCategories(new TreeSet<>((o1, o2) -> (int)(o1.getId()-o2.getId())));
                parent.getChildrenCategories().add(categoryEntity);
            }
        });
        return R.ok().put("tree", tree);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:category:info")
    public R info(@PathVariable("id") Long id){
		CategoryEntity category = categoryService.getById(id);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:category:save")
    public R save( @RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:category:delete")
    public R delete(@RequestBody Long[] ids){
		categoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


}
