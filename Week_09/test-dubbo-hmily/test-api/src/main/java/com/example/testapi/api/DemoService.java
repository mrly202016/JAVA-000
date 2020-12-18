package com.example.testapi.api;

import com.example.testapi.domain.AccountDTO;
import org.dromara.hmily.annotation.Hmily;

public interface DemoService {

    @Hmily
    void tccDeal(AccountDTO accountDTO);

}
