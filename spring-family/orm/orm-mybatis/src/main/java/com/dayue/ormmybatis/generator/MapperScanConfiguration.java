package com.dayue.ormmybatis.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * @author zhengdayue
 * @time 2022/3/31 0:29
 */
@Component
@MapperScan({"com.dayue.ormmybatis.generator.mapper","com.dayue.ormmybatis.mapper"})
public class MapperScanConfiguration {
}
