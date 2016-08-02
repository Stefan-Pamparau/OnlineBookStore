package com.iquestgroup.database.builder;

import com.iquestgroup.model.AccountType;
import com.iquestgroup.model.UserAccount;

import java.sql.Timestamp;

/**
 * Builder used to create {@link com.iquestgroup.model.UserAccount} instances.
 *
 * @author Stefan Pamparau
 */
public class UserAccountBuilder {
    private Integer id;
    private String email;
    private String password;
    private Timestamp creationDate;
    private AccountType accountType;

    public UserAccountBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserAccountBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserAccountBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserAccountBuilder setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public UserAccountBuilder setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public UserAccount build() {
        UserAccount userAccount = new UserAccount();

        userAccount.setId(id);
        userAccount.setEmail(email);
        userAccount.setPassword(password);
        userAccount.setCreationDate(creationDate);
        userAccount.setAccountType(accountType);

        return userAccount;
    }

    public UserAccount build(Integer id, String email, String password, Timestamp creationDate, AccountType accountType) {
        UserAccount userAccount = new UserAccount();

        userAccount.setId(id);
        userAccount.setEmail(email);
        userAccount.setPassword(password);
        userAccount.setCreationDate(creationDate);
        userAccount.setAccountType(accountType);

        return userAccount;
    }
}
