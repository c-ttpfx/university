package com.ttpfx.living.commodity.controller;

import java.util.Arrays;
import java.util.Map;

import com.ttpfx.living.commodity.vo.SpuSaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttpfx.living.commodity.entity.SpuInfoEntity;
import com.ttpfx.living.commodity.service.SpuInfoService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.R;


/**
 * 商品 spu 信息
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-28 10:08:28
 */
@RestController
@RequestMapping("commodity/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:spuinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        // PageUtils page = spuInfoService.queryPage(params);
        PageUtils page = spuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/{id}/up")
    public R up(@PathVariable("id") Long id){
        spuInfoService.changeStatus(id, 1L);
        return R.ok();
    }

    @RequestMapping("{id}/down")
    public R down(@PathVariable("id") Long id){
        spuInfoService.changeStatus(id, 2L);
        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:spuinfo:info")
    public R info(@PathVariable("id") Long id) {
        SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:spuinfo:save")
    public R save(@RequestBody SpuSaveVO spuSaveVO) {
        // System.out.println(spuSaveVO);
        spuInfoService.saveSpuInfo(spuSaveVO);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:spuinfo:update")
    public R update(@RequestBody SpuInfoEntity spuInfo) {
        spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:spuinfo:delete")
    public R delete(@RequestBody Long[] ids) {
        spuInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
