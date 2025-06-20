package vn.vinaacademy.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "grpc.server")
public class GrpcServerProperties {
    private int port = 9090;
}
