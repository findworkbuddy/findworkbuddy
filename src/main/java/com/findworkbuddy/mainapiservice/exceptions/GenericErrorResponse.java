package com.findworkbuddy.mainapiservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

}
