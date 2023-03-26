package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.SkuInfoEntity;
import com.ttpfx.living.commodity.vo.SearchResult;

import java.util.Map;

/**
 * sku 信息
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-28 11:21:48
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPageByCondition(Map<String, Object> params);


    SearchResult querySearchPageByCondition
            (Map<String, Object> params);
}

