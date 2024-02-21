package com.example.demodbbootclient.response;

import com.example.demodbbootclient.entity.Register;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class RefreshResponse extends BaseResponse {
/*    private long id;

    public RefreshResponse(boolean success, String message, long id) {
        super(success, message);
        this.id = id;
    }*/

    private Register reg;
    public RefreshResponse(Boolean success, String message, Register reg) {
        super(success, message);
        this.reg = reg;
    }
}
