package com.br.completedToDo.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(ApplicationUserPermission.USER_WRITE)),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.ADMIN_READ, ApplicationUserPermission.ADMIN_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
