package br.com.priscilasanfer.zedelivery.utils.exception;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
