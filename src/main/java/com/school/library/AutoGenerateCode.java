package com.school.library;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class AutoGenerateCode {
    public static void main(String[] args) throws InterruptedException {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置定义
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir("E:\\ws_idea\\library\\src\\main\\java\\"); // 设置存储路径
//        gc.setOutputDir("F:\\ws_idea\\temp\\"); // 设置存储路径
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("shadow"); // 作者信息

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sDao");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc); // 设置全局配置

        // 数据源配置定义
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        /*dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });*/
//        dsc.setDriverName("com.mysql.jdbc.Driver");

        dsc.setDriverName("com.mysql.cj.jdbc.Driver");//mysql8.0以上
        dsc.setUrl("jdbc:mysql://localhost:3306/lib?useUnicode=true&characterEncoding=UTF-8&generateSimpleParameterMetadata=true&useSSL=false&serverTimezone=UTC");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc); // 设置数据源

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
         strategy.setInclude(new String[] { "admin","book","student","borrow"}); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.school");
        pc.setModuleName("library");
        pc.setController("controller");//默认为web
        pc.setEntity("bean");
        pc.setMapper("dao");
        pc.setXml("dao.mapper");

        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }
}