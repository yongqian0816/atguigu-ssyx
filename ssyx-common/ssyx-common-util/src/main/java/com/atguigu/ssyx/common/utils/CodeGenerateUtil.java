package com.atguigu.ssyx.common.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-09 18:11
 **/
public class CodeGenerateUtil {

    private static final String url = "jdbc:mysql://localhost:3306/shequ-sys?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai";
    private static final String username = "root";
    private static final String password = "123456";
    public static void main(String[] args) {
        FastAutoGenerator generator = FastAutoGenerator.create(url, username, password)
            .globalConfig(builder -> builder.author("yong.qian")
                .fileOverride()
                .outputDir("/Users/qianyong/person/aboutJava/guigu-ssyx-parent/ssyx-service/ssyx-service-sys/src/main/java")
                .enableSwagger())
            .packageConfig(builder -> {
                builder.parent("com.atguigu.ssyx")
                    .moduleName("sys")
                    .service("service")
                    .controller("controller")
                    .mapper("mapper")
                ;
            })
            .strategyConfig(builder ->
                builder.addInclude("region")
                    .addInclude("region_ware")
                    .addInclude("ware"));
        generator.execute();
    }
}
