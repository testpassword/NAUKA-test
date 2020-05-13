package com.kulbako.backend.models;

public enum Role {
    ADMIN(false), DEVELOPER(false), MANAGER(false);

    private boolean isRemote;

    Role(boolean isRemote) {this.isRemote = isRemote; }

    public boolean isRemote() { return isRemote; }

    public void setRemote(boolean remote) { isRemote = remote; }
}
