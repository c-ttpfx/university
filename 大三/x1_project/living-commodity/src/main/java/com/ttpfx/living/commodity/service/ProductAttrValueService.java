package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.ProductAttrValueEntity;

import java.util.Map;

/**
 * spu 基本属性值
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-28 11:07:57
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

