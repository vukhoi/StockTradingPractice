package com.example.stocktrainingpractice.View;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.stocktrainingpractice.Model.Account.Account;
import com.example.stocktrainingpractice.Model.Account.AccountManager;
import com.example.stocktrainingpractice.Model.Account.AccountManagerImplementation;
import com.example.stocktrainingpractice.R;
import com.example.stocktrainingpractice.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CreateNewAccActivity extends AppCompatActivity {
    private static final String ACC_NAME_SIZE_ERROR_MSG = "Account name length has to be between 1 and 30 characters, inclusive";
    private static final String STARTING_CASH_AMT_ERROR_MSG = "Starting amount has to be larger than 100";
    private static final String TRANSACTION_FEE_ERROR_MSG = "Transaction fee has to be between 0 and 20, inclusive";
    Unbinder unbinder;
    @BindView(R.id.et_acc_name) EditText etAccName;
    @BindView(R.id.et_cash_amt) EditText etCashAmt;
    @BindView(R.id.et_transaction_fee) EditText etTransactionFee;
    @BindView(R.id.rg_day_trade) RadioGroup rgDayTradeRestriction;
    @BindView(R.id.create_new_acc_root_layout) ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_acc);

        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_submit)
    void processUserInput() {
        Account newAccount = createNewAccount();
        if (newAccount == null) {
            startPortfolioActivity(newAccount);
        }
    }

    private Account createNewAccount() {
        String accName = etAccName.getText().toString();
        Double transactionFee = Double.parseDouble(etTransactionFee.getText().toString());
        Double startingCashAmt = Double.parseDouble(etCashAmt.getText().toString());
        Boolean dayTradeRestriction = false;

        RadioButton selectedRB = findViewById(rgDayTradeRestriction.getCheckedRadioButtonId());
        if (selectedRB.getText().toString().equals("yes")) {
            dayTradeRestriction = true;
        }

        if (accName.length() == 0 || accName.length() > 30 || accName == null) {
            displayErrorMessage(ACC_NAME_SIZE_ERROR_MSG);
            return null;
        } else if (startingCashAmt < 100) {
            displayErrorMessage(STARTING_CASH_AMT_ERROR_MSG);
            return null;
        } else if (transactionFee < 0 || transactionFee > 20) {
            displayErrorMessage(TRANSACTION_FEE_ERROR_MSG);
            return null;
        } else {
            AccountManager accountManager = AccountManagerImplementation.getInstance();
            Account newAccount = new Account(accName,startingCashAmt, transactionFee, dayTradeRestriction);
            accountManager.createNew(newAccount);
            return newAccount;
        }
    }


    private void displayErrorMessage(String createNewAccErrorMsg) {
        Snackbar.make(rootLayout, createNewAccErrorMsg, Snackbar.LENGTH_LONG).show();
    }

    private void startPortfolioActivity(Account account) {
        Intent intent = new Intent(this, PortfolioActivity.class);
        intent.putExtra(Util.ACC_SERIALIZABLE_KEY, account);
        startActivity(intent);
    }
}

