package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku 的销售属性/值表
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-28 12:59:52
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

