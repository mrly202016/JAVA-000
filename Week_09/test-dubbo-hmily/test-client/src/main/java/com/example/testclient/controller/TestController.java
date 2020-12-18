package com.example.testclient.controller;

import com.example.testapi.domain.AccountDTO;
import com.example.testclient.service.OneTccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * T
 */
@RestController
@RequestMapping("/tcc")
public class TestController {
    @Autowired
    OneTccService oneTccService;

    @GetMapping(value = "/deal")
    public String deal() throws Exception {
        AccountDTO a=new AccountDTO();
        a.setUserId("1");
        a.setCnyAmount(new BigDecimal(7));
        a.setUsdAmount(new BigDecimal(1));
        oneTccService.update(a);
        return "hehe";
    }

}
