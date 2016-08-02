package com.iquestgroup.database.builder;

import com.iquestgroup.model.User;

/**
 * Builder used to create {@link com.iquestgroup.model.User} instances.
 *
 * @author Stefan Pamparau
 */
public class UserBuilder {
    private Integer id;
    private String name;
    private String address;
    private String serialId;

    public UserBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setSerialId(String serialId) {
        this.serialId = serialId;
        return this;
    }

    public User build() {
        User user = new User();

        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        user.setSerialId(serialId);

        return user;
    }

    public User build(Integer id, String name, String address, String serialId) {
        User user = new User();

        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        user.setSerialId(serialId);

        return user;
    }
}
