package com.sushildangi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiResponse {

    private String timestamp;
    private Integer status;
    private Boolean success;
    private String message;
    private String path;
    private String error;


}
