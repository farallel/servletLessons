package com.lessons.model;

public enum Role {
    ADMIN(1),
    USER(2),
    UNKNOWN(3);

    private Integer roleId;

    Role(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return roleId;
    }
}
