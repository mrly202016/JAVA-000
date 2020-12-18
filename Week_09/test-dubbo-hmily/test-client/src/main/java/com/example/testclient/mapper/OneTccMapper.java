package com.example.testclient.mapper;

import com.example.testapi.domain.AccountDTO;
import org.apache.ibatis.annotations.Update;

/**
 *
 */
public interface OneTccMapper {
    @Update("update `account` set cny_balance = cny_balance - #{cnyAmount} ,usd_balance =usd_balance + #{usdAmount}," +
            " cny_freeze_amount= cny_freeze_amount + #{cnyAmount} ,update_time = now()" +
            " where user_id =#{userId}  and  cny_balance > 0  ")
    int update(AccountDTO accountDTO);

    @Update("update `account` set " +
            " cny_freeze_amount= cny_freeze_amount - #{cnyAmount}" +
            " where user_id =#{userId}  and cny_freeze_amount >0 ")
    int confirm(AccountDTO accountDTO);

    @Update("update `account` set cny_balance = cny_balance + #{cnyAmount} ,usd_balance =usd_balance - #{usdAmount}," +
            " cny_freeze_amount= cny_freeze_amount -  #{cnyAmount} " +
            " where user_id =#{userId}  and cny_freeze_amount >0")
    int cancel(AccountDTO accountDTO);

}
