package vn.vinaacademy.common.config;

import lombok.Getter;

@Getter
public enum UrlPath {
    RESET_PASSWORD("/reset-password"),
    VERIFY_ACCOUNT("/verify-account"),
    EXPLORE("/explore"),
    ;

    private final String path;

    UrlPath(String path) {
        this.path = path;
    }

}
