package com.anhtester.model.data;

import com.anhtester.globals.ConfigsGlobal;
import com.anhtester.model.LoginPOJO;

public class LoginPOJO_Builder {

    public static LoginPOJO getDataLogin(){
        return LoginPOJO.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();
    }

}
