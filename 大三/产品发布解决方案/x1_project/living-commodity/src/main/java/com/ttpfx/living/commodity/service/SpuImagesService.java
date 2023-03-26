package com.ttpfx.living.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.living.commodity.entity.SpuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * spu 图片集
 *
 * @author ttpfx
 * @email ttpfx@gmail.com
 * @date 2023-02-28 10:52:03
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveImages(Long spuId, List<String> images);
}

