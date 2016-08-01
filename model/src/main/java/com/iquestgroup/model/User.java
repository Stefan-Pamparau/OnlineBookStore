package com.iquestgroup.model;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model class which models clients of the book store.
 *
 * @author Stefan Pamparau
 */
public class User {
    private Integer id;

    @NotNull
    @Size(min = 3, max = 20, message = "Should contain a minimum of 3 character and a maximum of 20 characters")
    private String name;

    @NotNull
    @Size(min = 3, max = 20, message = "Should contain a minimum of 3 character and a maximum of 20 characters")
    private String address;

    @NotNull
    private String serialId;

    private Set<UserAccount> userAccounts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(Set<UserAccount> userAccount) {
        this.userAccounts = userAccount;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null)
            return false;
        if (name != null ? !name.equals(user.name) : user.name != null)
            return false;
        if (address != null ? !address.equals(user.address) : user.address != null)
            return false;
        return serialId != null ? serialId.equals(user.serialId) : user.serialId == null;

    }

    @Override public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (serialId != null ? serialId.hashCode() : 0);
        return result;
    }
}
