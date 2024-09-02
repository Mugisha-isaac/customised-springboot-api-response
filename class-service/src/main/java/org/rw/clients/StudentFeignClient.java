package org.rw.clients;

import org.rw.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(configuration = FeignConfig.class, value = "studentClient")
public interface StudentFeignClient {
}
