package com.ttpfx.living.commodity.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ttpfx.living.commodity.entity.*;
import com.ttpfx.living.commodity.service.*;
import com.ttpfx.living.commodity.vo.BaseAttrs;
import com.ttpfx.living.commodity.vo.Images;
import com.ttpfx.living.commodity.vo.Skus;
import com.ttpfx.living.commodity.vo.SpuSaveVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.Query;

import com.ttpfx.living.commodity.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Resource
    private SpuInfoDescService spuInfoDescService;
    @Resource
    private SpuImagesService spuImagesService;
    @Resource
    private ProductAttrValueService productAttrValueService;
    @Resource
    private AttrService attrService;
    @Resource
    private SkuInfoService skuInfoService;
    @Resource
    private SkuImagesService skuImagesService;
    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveSpuInfo(SpuSaveVO spuSaveVO) {

        // 保存基本信息 SpuInfoEntity
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVO, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        save(spuInfoEntity);


        // 保存图片描述信息SpuInfoDescEntity，多个图片使用,进行分隔。没有图片就保存一个默认值
        SpuInfoDescEntity spuInfoDesc = new SpuInfoDescEntity();
        spuInfoDesc.setSpuId(spuInfoEntity.getId());
        spuInfoDesc.setDecript(String.join(",", spuSaveVO.getDecript()));
        if (StringUtils.isEmpty(spuInfoDesc.getDecript())) spuInfoDesc.setDecript("default.png");
        spuInfoDescService.save(spuInfoDesc);


        // 保存 spu 图片集信息 SpuImagesEntity
        List<String> images = spuSaveVO.getImages();
        spuImagesService.saveImages(spuInfoEntity.getId(), images);


        // 保存基本属性
        List<BaseAttrs> baseAttrs = spuSaveVO.getBaseAttrs();
        List<ProductAttrValueEntity> productAttrValueEntities = baseAttrs.stream()
                .map(baseAttr -> {
                    ProductAttrValueEntity productAttrValue = new ProductAttrValueEntity();
                    productAttrValue.setSpuId(spuInfoEntity.getId());
                    productAttrValue.setAttrId(baseAttr.getAttrId());
                    String attrName = attrService.getById(baseAttr.getAttrId()).getAttrName();
                    productAttrValue.setAttrName(attrName);
                    productAttrValue.setQuickShow(baseAttr.getShowDesc());
                    productAttrValue.setAttrValue(baseAttr.getAttrValues());
                    return productAttrValue;
                }).collect(Collectors.toList());
        productAttrValueService.saveBatch(productAttrValueEntities);


        // 保存 sku 的基本信息
        List<Skus> skus = spuSaveVO.getSkus();
        skus.forEach(sku -> {
            String defaultImage = "default2.png";
            for (Images image : sku.getImages()) {
                if (image.getDefaultImg() == 1) {
                    defaultImage = image.getImgUrl();
                    break;
                }
            }
            SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
            BeanUtils.copyProperties(sku, skuInfoEntity);
            skuInfoEntity.setSpuId(spuInfoEntity.getId());
            skuInfoEntity.setCatalogId(spuSaveVO.getCatalogId());
            skuInfoEntity.setBrandId(spuSaveVO.getBrandId());
            skuInfoEntity.setSaleCount(0L);
            skuInfoEntity.setSkuDefaultImg(defaultImage);
            skuInfoService.save(skuInfoEntity);

            // 保存图片信息
            List<SkuImagesEntity> skuImagesEntities = sku.getImages()
                    .stream()
                    .map(image -> {
                        SkuImagesEntity skuImages = new SkuImagesEntity();
                        skuImages.setSkuId(skuInfoEntity.getSkuId());
                        skuImages.setImgUrl(image.getImgUrl());
                        skuImages.setDefaultImg(image.getDefaultImg());
                        return skuImages;
                    }).filter(skuImagesEntity -> !StringUtils.isBlank(skuImagesEntity.getImgUrl())).collect(Collectors.toList());
            skuImagesService.saveBatch(skuImagesEntities);

            // 保存销售属性
            List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = sku.getAttr().stream().map(saleAttr -> {
                SkuSaleAttrValueEntity skuSaleAttrValue = new SkuSaleAttrValueEntity();
                skuSaleAttrValue.setSkuId(skuInfoEntity.getSkuId());
                BeanUtils.copyProperties(saleAttr, skuSaleAttrValue);
                return skuSaleAttrValue;
            }).collect(Collectors.toList());
            skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);
        });

    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> queryWrapper = new QueryWrapper<>();
        Object status = params.get("status");
        if (status != null) queryWrapper.in("publish_status", status);
        Object key = params.get("key");
        if (key != null) {
            queryWrapper.and(w -> {
                w.eq("id", key).or().like("spu_name", key);
            });
        }
        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(brandId)) {
            queryWrapper.eq("brand_id", brandId);
        }
        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {
            queryWrapper.eq("catalog_id", catelogId);
        }
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params), queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void changeStatus(Long id, Long status) {
        update(new UpdateWrapper<SpuInfoEntity>().eq("id", id).set("publish_status", status));
    }

}