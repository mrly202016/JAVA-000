package com.example.testclient.service.impl;

import com.example.testapi.api.DemoService;
import com.example.testapi.domain.AccountDTO;
import com.example.testclient.mapper.OneTccMapper;
import com.example.testclient.service.OneTccService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 *
 */
@Service
public class OneTccServiceImpl implements OneTccService {
    @Autowired
    DemoService demoService;

    @Autowired
    OneTccMapper oneTccMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public int update(AccountDTO accountDTO) throws Exception {
        int count=oneTccMapper.update(accountDTO);
        AccountDTO b=new AccountDTO();
        b.setUserId("1");
        b.setUsdAmount(new BigDecimal(1));
        b.setCnyAmount(new BigDecimal(7));
        demoService.tccDeal(b);
        if("1".equals(accountDTO.getUserId())) throw new Exception("e");
        return count;
    }

    @Override
    public int confirm(AccountDTO accountDTO) {
        return oneTccMapper.confirm(accountDTO);
    }

    @Override
    public int cancel(AccountDTO accountDTO) {
        return oneTccMapper.cancel(accountDTO);
    }
}
