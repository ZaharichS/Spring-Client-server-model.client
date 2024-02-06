package com.example.demodbbootclient.response;

import com.example.demodbbootclient.entity.Register;

public class RegisterResponse extends BaseResponse {
    public RegisterResponse(Iterable<Register> data) {
        super(true, "Полёты");
        this.data = data;
    }

    private Iterable<Register> data;
}


