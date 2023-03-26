package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.SpuInfoEntity;
import com.ttpfx.living.commodity.vo.SpuSaveVO;

import java.util.Map;

/**
 * 商品 spu 信息
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-28 10:08:28
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVO spuSaveVO);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void changeStatus(Long id,Long status);
}

