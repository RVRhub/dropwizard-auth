package com.test.auth;

import com.google.common.base.MoreObjects;
import com.test.auth.entities.AbstractEntity;

import java.security.Principal;
import java.util.Objects;

public class User extends AbstractEntity implements Principal{
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final User principal = (User) o;
        return Objects.equals(this.name, principal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name",  name).toString();
    }
}
