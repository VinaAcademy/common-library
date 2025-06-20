package vn.vinaacademy.common.utils;

import lombok.experimental.UtilityClass;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import vn.vinaacademy.common.dto.GrpcServer;

import java.util.List;

@UtilityClass
public class GrpcUtils {

    public static final String DISCOVERY_METADATA_GRPC_PORT = "grpc-port";

    public static ServiceInstance getServiceInstance(DiscoveryClient discoveryClient, String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances.isEmpty()) {
            throw new RuntimeException("No grpc-user-service instances found");
        }

        return instances.get(0);
    }

    public static GrpcServer getGrpcServer(DiscoveryClient discoveryClient, String serviceName) {
        ServiceInstance serviceInstance = getServiceInstance(discoveryClient, serviceName);
        String grpcPort = serviceInstance.getMetadata().get(DISCOVERY_METADATA_GRPC_PORT);
        if (grpcPort == null) {
            throw new RuntimeException("gRPC port not found in service instance metadata");
        }
        return GrpcServer.builder()
                .host(serviceInstance.getHost())
                .port(Integer.parseInt(grpcPort))
                .build();
    }
}
