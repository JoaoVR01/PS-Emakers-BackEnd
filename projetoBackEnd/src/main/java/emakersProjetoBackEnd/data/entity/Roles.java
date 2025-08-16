package emakersProjetoBackEnd.data.entity;

import lombok.Getter;

@Getter
public enum Roles {

    ADMIN("admin"),

    USER("user");

    private String role;

    Roles(String roles) {
        this.role = roles;
    }
}
