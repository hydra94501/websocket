package com.gallery.websoket.gen;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;


public class CodeGenerator {

    public static void main(String[] args) {

        //1、全局配置
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .outputDir(projectPath + "/websoket-service/src/main/java/") //生成路径
                .author("xwj") //设置作者
                .disableOpenDir() //禁止打开输出目录
                .dateType(DateType.ONLY_DATE)//设置日期为Date
                //.enableSwagger() //启用swagger
                .build();
        //2、数据源配置，修改为自己的数据库
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(
                "jdbc:mysql://34.150.92.75:3306/message_websoket?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true"
                , "root", "Xw%N0wd2e@7F.V")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        // 3、包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .moduleName("")
                .parent("com.gallery.websoket") //设置父包
                .entity("model") //设置实体类包名,默认entity
                .service("service")//设置service包名
                .mapper("mapper")//设置mapper包名
                .serviceImpl("service.impl")//设置serviceImpl包名
//                .controller("web")//设置controller包名
                .pathInfo(Collections.singletonMap(OutputFile.controller,
                        System.getProperty("user.dir") + "/websoket-web/src/main/java/web"))
                //.xml("/resources/mapper") //设置xml文件路径
                //自定以xxMapper.xml文件路径
                .pathInfo(Collections.singletonMap(OutputFile.xml,
                        System.getProperty("user.dir") + "/websoket-service/src/main/resources/mapper"))
                .build();


        //List<String> tables = new ArrayList<>();
        //tables.add("article");
        //tables.add("user");
        //tables.add("category");
        // 4、策略配置
        StrategyConfig strategy = new StrategyConfig.Builder()
                .addInclude("ws_topic_group")//设置表名称，不设置读数据库所有表，设置可以是单表名，也可以是多表名逗号隔开，或者是集合列表
                //.addExclude("system_user")//增加表排除匹配(内存过滤)	include 与 exclude 只能配置一项
//                .addTablePrefix("event_")//设置表前缀过滤，如event_user，设置以后生成的实体类名会是User
                .mapperBuilder()
                .enableMapperAnnotation()//开启mapper注解
                .enableBaseResultMap()//启用xml文件中的BaseResultMap 生成
                .enableBaseColumnList()//启用xml文件中的BaseColumnList
                .formatMapperFileName("%sMapper")//格式化mapper类名称
                .formatXmlFileName("%sMapper")//格式化xml文件名称
                //.enableFileOverride()//mapper文件夹中覆盖已有文件
                .serviceBuilder()
                .formatServiceFileName("%sService")//格式化 service 接口文件名称
                .formatServiceImplFileName("%sServiceImpl")//格式化 service 接口文件名称
                //.enableFileOverride()//service文件夹中覆盖已有文件
                .controllerBuilder()
                .enableRestStyle()
                //.enableFileOverride()//controller文件夹中覆盖已有文件
                .entityBuilder()
                .naming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略
                .enableLombok()//使用lombok
                //.enableFileOverride()//entity文件夹中覆盖已有文件
                .build();
        // 5、模板配置
//        TemplateConfig templateConfig = new TemplateConfig.Builder()
//                .controller("com.ls.controller")
//                .xml(System.getProperty("user.dir") + "/src/main/resources/mapper")
//                .build();
        ConfigBuilder configBuilder = new ConfigBuilder(
                packageConfig,
                dataSourceConfig,
                strategy,
                null,
                globalConfig,
                null
        );

        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig).config(configBuilder);
        //6、执行
        autoGenerator.execute();

    }
}

