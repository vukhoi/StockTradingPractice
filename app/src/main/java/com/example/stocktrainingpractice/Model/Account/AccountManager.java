package com.example.stocktrainingpractice.Model.Account;

import java.util.List;

public interface AccountManager {
    void createNew(Account account);

    void updateSettings(String accName, Double transactionFee, Boolean dayTradeRestriction);

    void updateStock(String accName, Double cashAmt, Double equityAmt, List<Stock> stockList);

    void deleteAll();

    void delete(String accName);

    List<Account> getAllAccount();

    List<Stock> getStockList(String accName);
}
