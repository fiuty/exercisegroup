package com.dayue.userservice.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


/**
 * @author zhengdayue
 */
@Data
public class AmapVO {

    private Integer status;

    @JsonProperty("lives")
    private List<WeatherVO> weathers;
}
