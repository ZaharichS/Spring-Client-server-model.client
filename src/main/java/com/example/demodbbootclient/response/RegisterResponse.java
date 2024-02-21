package com.example.demodbbootclient.response;

import com.example.demodbbootclient.entity.Register;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Data
public class RegisterResponse extends BaseResponse {
    public  RegisterResponse(Boolean success,String message, Iterable<Register> data) {
        super(success,message);
        this.data = data;
    }

    private Iterable<Register> data;
}