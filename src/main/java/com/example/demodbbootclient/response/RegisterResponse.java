package com.example.demodbbootclient.response;

import com.example.demodbbootclient.entity.Register;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Data
public class RegisterResponse extends BaseResponse {
    public RegisterResponse(Iterable<Register> data) {
        super(true, "Полёты");
        this.data = data;
    }
    private Iterable<Register> data;
}