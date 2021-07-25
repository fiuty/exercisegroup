package com.dayue.spring.pattern.templates.jdbctemplate;

import com.dayue.spring.pattern.templates.jdbctemplate.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengdayue
 */
@RestController
@RequestMapping("/api")
public class JdbcController {

    @Autowired
    private JdbcService jdbcService;

    @GetMapping("/originalJdbc")
    public void originalJdbc() {
        jdbcService.originalJdbc();
    }

    @GetMapping("/springJdbcTemplate")
    public void springJdbcTemplate() {
        jdbcService.jdcbTemplate();
    }

    @GetMapping("/defineJcbcTemplate")
    public void defineJcbcTemplate() {
        jdbcService.defineJcbcTemplate();
    }
}
