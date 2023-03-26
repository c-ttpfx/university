package com.ttpfx.living.commodity.controller;

import com.ttpfx.common.utils.R;
import com.ttpfx.living.commodity.entity.CategoryEntity;
import com.ttpfx.living.commodity.service.CategoryService;
import com.ttpfx.living.commodity.vo.Catalog2Vo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ttpfx
 * @date 2023/3/1
 */
@Controller
public class IndexController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = {"/","/index"})
    public String index(Model model){
        List<CategoryEntity> categoryEntityList = categoryService.getCategoryLevel(1);
        model.addAttribute("categories", categoryEntityList);
        return "index";
    }

    @RequestMapping("/index/catalog.json")
    @ResponseBody
    public Map<String, List<Catalog2Vo>> getCatalogJson(){
        Map<String, List<Catalog2Vo>> catalogJson =
                categoryService.getCatalogJson();
        return catalogJson;
    }
}
