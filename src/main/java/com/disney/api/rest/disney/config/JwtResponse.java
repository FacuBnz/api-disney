package com.disney.api.rest.disney.config;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class JwtResponse implements Serializable {
    private String token;
    private String message;
}
