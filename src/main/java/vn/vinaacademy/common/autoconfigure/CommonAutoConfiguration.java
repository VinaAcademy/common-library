package vn.vinaacademy.common.autoconfigure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@AutoConfiguration
@ComponentScan(basePackages = {"vn.vinaacademy.common"})
@RequiredArgsConstructor
public class CommonAutoConfiguration {

}
