package vn.vinaacademy.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrpcServer {
    private String host;
    private int port;
}
