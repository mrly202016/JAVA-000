package com.example.testclient.service;

import com.example.testapi.domain.AccountDTO;

/**
 *
 */
public interface OneTccService {
    int update(AccountDTO accountDTO) throws Exception;
    int confirm(AccountDTO accountDTO);
    int cancel(AccountDTO accountDTO);
}
