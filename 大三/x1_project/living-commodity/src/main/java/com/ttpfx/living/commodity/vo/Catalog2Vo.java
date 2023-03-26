package com.ttpfx.living.commodity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ttpfx
 * @date 2023/3/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalog2Vo {
    /**
     * 一级父分类的 id
     */
    private String catalog1Id;
    /**
     * 三级子分类
     */
    private List<Category3Vo> catalog3List;
    private String id;
    private String name;

    /**
     * 三级分类 vo
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category3Vo {
        /**
         * 父分类、二级分类 id
         */
        private String catalog2Id;
        private String id;
        private String name;
    }
}

