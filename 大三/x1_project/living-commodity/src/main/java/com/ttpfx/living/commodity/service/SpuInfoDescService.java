package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.SpuInfoDescEntity;

import java.util.Map;

/**
 * 商品 spu 信息介绍
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-28 10:29:33
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

