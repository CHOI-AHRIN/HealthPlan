package com.healthplan.work.dto;

import lombok.Getter;
import lombok.Setter;

public class LoginDTO {


    // DTO는 입력받은 값을 따로 저장하는 곳임


    private String uuid;
    private String upw;
    private boolean useCookie;

    // @Getter
    public String getuuid() {
        return uuid;
    }

    // @Setter
    public void setuuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUpw() {
        return upw;
    }
    public void setUpw(String upw) {
        this.upw = upw;
    }

    public boolean isUseCookie() {
        return useCookie;
    }

    public void setUseCookie(boolean useCookie) {
        this.useCookie = useCookie;
    }

    @Override
    public String toString() {
        return "LoginDTO [uuid=" + uuid + ", upw=" + upw + ", useCookie="
                + useCookie + "]";
    }



}
