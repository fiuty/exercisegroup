package com.dayue.demo.spring.boot.auto.assemble;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhengdayue
 */
@ConfigurationProperties(prefix = "assemble.auto")
@Data
public class AssembleProperties {

    private String name = "defalutName";

    private String symbol = "assemble";

}
