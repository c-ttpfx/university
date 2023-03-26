package com.ttpfx.living.commodity.service.impl;

import com.ttpfx.living.commodity.dao.SpuInfoDao;
import com.ttpfx.living.commodity.entity.SpuInfoEntity;
import com.ttpfx.living.commodity.vo.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ttpfx.common.utils.PageUtils;
import com.ttpfx.common.utils.Query;

import com.ttpfx.living.commodity.dao.SkuInfoDao;
import com.ttpfx.living.commodity.entity.SkuInfoEntity;
import com.ttpfx.living.commodity.service.SkuInfoService;

import javax.annotation.Resource;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Resource
    private SpuInfoDao spuInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {

        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();

        Object key = params.get("key");
        if (key != null) {
            queryWrapper.and(w -> {
                w.eq("sku_id", key).or().like("sku_name", key);
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

        //价格范围
        String min = (String) params.get("min");
        if (!StringUtils.isEmpty(min)) {
            queryWrapper.ge("price", min);
        }
        String max = (String) params.get("max");
        //校验传递的价格范围合理性, 如果 max 有值,并且大于 0，
        //才有必要封装到查询条件
        if (!StringUtils.isEmpty(max)) {
            try {
                BigDecimal bigDecimal = new BigDecimal(max);
                if (bigDecimal.compareTo(new BigDecimal("0")) == 1) {
                    queryWrapper.le("price", max);
                }
            } catch (Exception e) {
            }
        }
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params), queryWrapper
        );

        return new PageUtils(page);
    }


    //返回购买用户检索的结果 PageUtils-> SearchResult
    @Override
    public SearchResult querySearchPageByCondition(Map<String, Object> params) {

        //先查询到已经上架的spuInfoEntity
        List<SpuInfoEntity> spuInfoEntities =
                spuInfoDao.selectList(new QueryWrapper<SpuInfoEntity>().eq("publish_status", 1));

        //收集上架的spuInfoEntity的id到集合-> 使用Stream API 流式计算完成
        List<Long> spuInfoEntitiesIds = spuInfoEntities.stream().map(SpuInfoEntity::getId).collect(Collectors.toList());


        //构建QueryWrapper , 根据检索条件，返回结果
        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();

        //将spuInfoEntitiesIds 加入到分页查询条件
        if (spuInfoEntitiesIds.size() > 0) {
            queryWrapper.in("spu_id", spuInfoEntitiesIds);
        } else {
            //如果没有上架的商品, 就构建一个SearchResult返回即可
            SearchResult searchResult = new SearchResult();
            searchResult.setPageNum(1);
            searchResult.setTotal(0l);
            searchResult.setTotalPages(0);
            searchResult.setPageNavs(new ArrayList<>());
            searchResult.setCommodity(new ArrayList<>());
            //设置keyword
            searchResult.setKeyword
                    (params.get("keyword") == null ? "" : params.get("keyword").toString());
            return searchResult;
        }

        //检索条件-keyword
        String keyword = (String) params.get("keyword");
        if (StringUtils.isNotBlank(keyword)) {
            //说明： 如果 keyword 视为 sku_id 则是eq, 如果 keyword 视为 sku_name 则是 like
            queryWrapper.and(wrapper -> {
                wrapper.eq("sku_id", keyword).or().like("sku_name", keyword);
            });
        }

        //检索条件-分类
        String catalog3Id = (String) params.get("catalog3Id");
        if (StringUtils.isNotBlank(catalog3Id) && !"0".equalsIgnoreCase(catalog3Id)) {
            queryWrapper.eq("catalog_id", catalog3Id);
        }

        //检索条件-品牌id
        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotBlank(brandId) && !"0".equalsIgnoreCase(brandId)) {
            queryWrapper.eq("brand_id", brandId);
        }

        //如果还有其它的检索条件，这里继续处理处理...
        params.put("limit", "2"); //每页显示两条记录
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                queryWrapper
        );

        PageUtils pageUtils = new PageUtils(page);

        //将pageUtils(PageUtils) 相关的数据取出，根据业务需求进行二次封装成 SearchResult

        SearchResult searchResult = new SearchResult();

        searchResult.setCommodity((List<SkuInfoEntity>) pageUtils.getList());
        searchResult.setPageNum(pageUtils.getCurrPage());
        searchResult.setTotal((long) pageUtils.getTotalCount());
        int totalPage = pageUtils.getTotalPage();
        searchResult.setTotalPages(totalPage);


        List<Integer> pageNavs = new ArrayList<>();
        //遍历总的页数，得到 pageNavs
        for (int i = 1; i <= totalPage; i++) {
            pageNavs.add(i);
        }
        searchResult.setPageNavs(pageNavs);

        searchResult.setKeyword
                (params.get("keyword") == null ? "" : params.get("keyword").toString());

        return searchResult;

    }
}