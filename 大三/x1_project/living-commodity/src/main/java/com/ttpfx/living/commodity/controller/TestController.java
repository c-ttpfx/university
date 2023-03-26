package com.ttpfx.living.commodity.controller;

import com.aliyun.oss.OSS;
import com.ttpfx.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author ttpfx
 * @date 2023/2/13
 */
@RestController
public class TestController {

    @Resource
    private OSS ossClint;

    @RequestMapping("/t1")
    public R t1() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("D:\\代码\\hsp培训\\分布式微服务\\oss.txt");
        ossClint.putObject("ttpfx-study-1", "oss.txt", inputStream);
        ossClint.shutdown();
        System.out.println("ok");
        return R.ok();
    }
}
