package com.ttpfx.living.commodity.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.Query;

import com.ttpfx.living.commodity.dao.SpuImagesDao;
import com.ttpfx.living.commodity.entity.SpuImagesEntity;
import com.ttpfx.living.commodity.service.SpuImagesService;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(params),
                new QueryWrapper<SpuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveImages(Long spuId, List<String> images) {
        if (images == null || images.size() == 0) {
            SpuImagesEntity imagesEntity = new SpuImagesEntity();
            imagesEntity.setImgUrl("default1.png");
            imagesEntity.setSpuId(spuId);
            imagesEntity.setDefaultImg(1);
            save(imagesEntity);

        } else {
            List<SpuImagesEntity> spuImagesEntities = images.stream()
                    .map(image -> {
                        SpuImagesEntity imagesEntity = new SpuImagesEntity();
                        imagesEntity.setImgUrl(image);
                        imagesEntity.setSpuId(spuId);
                        return imagesEntity;
                    }).collect(Collectors.toList());
            saveBatch(spuImagesEntities);
        }
    }

}