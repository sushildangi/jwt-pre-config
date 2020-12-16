package com.sushildangi.model;

public enum RoleName {
    RoleName("ROLE_USER", "ROLE_ADMIN");
    private final String roleUser;
    private final String roleAdmin;

    RoleName(String roleUser, String roleAdmin) {
        this.roleUser = roleUser;
        this.roleAdmin = roleAdmin;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public String getRoleAdmin() {
        return roleAdmin;
    }
}
