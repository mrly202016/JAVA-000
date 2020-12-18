package com.example.testserver.impl;

import com.example.testapi.api.DemoService;
import com.example.testapi.domain.AccountDTO;
import com.example.testserver.service.TwoTccService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {
    @Autowired
    TwoTccService twoTccService;

    @Override
    @HmilyTCC(confirmMethod = "tccConfirm", cancelMethod = "tccCancel")
    public void tccDeal(AccountDTO accountDTO) {
        twoTccService.update(accountDTO);
    }

    public void tccConfirm(AccountDTO accountDTO) {
        twoTccService.confirm(accountDTO);
    }

    public void tccCancel(AccountDTO accountDTO) {
        twoTccService.cancel(accountDTO);
    }
}
