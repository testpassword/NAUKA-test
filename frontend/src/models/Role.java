package models;

/**
 * Список доступных профессий для работников.
 * @author Артемий Кульбако
 * @version 1.1
 */
public enum Role {
    EMPTY(false),
    TIMEKEEPER(false),
    DEPARTMENT_ADMIN(false),
    EMPLOYEE_ADMIN(false);

    private boolean isRemote;

    Role(boolean isRemote) {this.isRemote = isRemote; }

    public boolean isRemote() { return isRemote; }

    public void setRemote(boolean remote) { isRemote = remote; }
}
