package com.iquestgroup.model;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model class which models clients of the book store.
 *
 * @author Stefan Pamparau
 */
public class Client {
    private Integer id;

    @NotNull
    @Size(min = 3, max = 20, message = "Should contain a minimum of 3 character and a maximum of 20 characters")
    private String name;

    @NotNull
    @Size(min = 3, max = 20, message = "Should contain a minimum of 3 character and a maximum of 20 characters")
    private String address;

    @NotNull
    private String serial_id;

    private Set<ClientAccount> clientAccounts;

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

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
    }

    public Set<ClientAccount> getClientAccounts() {
        return clientAccounts;
    }

    public void setClientAccounts(Set<ClientAccount> clientAccount) {
        this.clientAccounts = clientAccount;
    }
}
