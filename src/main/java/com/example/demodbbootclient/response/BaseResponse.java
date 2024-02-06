package com.example.demodbbootclient.response;

import lombok.Data;

@Data
public class BaseResponse {
    private boolean success;
    private String message;
}
