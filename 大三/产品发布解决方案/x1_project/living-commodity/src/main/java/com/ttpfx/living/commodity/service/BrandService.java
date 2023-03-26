package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.BrandEntity;

import java.util.Map;

/**
 * 家居品牌
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-10 00:32:46
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

