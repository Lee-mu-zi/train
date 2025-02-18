package com.leemuzi.train.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Hello world!
 */
@SpringBootApplication
@ComponentScan("com.leemuzi.train")
public class GateApplication {

    private static final Logger LOG = LoggerFactory.getLogger(GateApplication.class);

    public static void main(String[] args) {
//        SpringApplication.run(MemberApplication.class, args);
        ConfigurableEnvironment env = new SpringApplication(GateApplication.class).run(args).getEnvironment();
        LOG.info("网管启动成功！！");
        LOG.info("网关地址地址:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}
