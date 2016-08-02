package com.iquestgroup.database.builder;

import com.iquestgroup.model.AccountType;
import com.iquestgroup.model.ClientAccount;

import java.sql.Timestamp;

/**
 * Builder user to create {@link com.iquestgroup.model.ClientAccount} instances.
 *
 * @author Stefan Pamparau
 */
public class ClientAccountBuilder {
    private Integer id;
    private String email;
    private String password;
    private Timestamp creationDate;
    private AccountType accountType;
    private Integer balance;

    public ClientAccountBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ClientAccountBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientAccountBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public ClientAccountBuilder setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ClientAccountBuilder setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public ClientAccountBuilder setBalance(Integer balance) {
        this.balance = balance;
        return this;
    }

    public ClientAccount build() {
        ClientAccount clientAccount = new ClientAccount();

        clientAccount.setId(id);
        clientAccount.setEmail(email);
        clientAccount.setPassword(password);
        clientAccount.setCreationDate(creationDate);
        clientAccount.setAccountType(accountType);
        clientAccount.setBalance(balance);

        return clientAccount;
    }

    public ClientAccount build(Integer id, String email, String password, Timestamp creationDate, AccountType accountType, Integer balance) {
        ClientAccount clientAccount = new ClientAccount();

        clientAccount.setId(id);
        clientAccount.setEmail(email);
        clientAccount.setPassword(password);
        clientAccount.setCreationDate(creationDate);
        clientAccount.setAccountType(accountType);
        clientAccount.setBalance(balance);

        return clientAccount;
    }
}
