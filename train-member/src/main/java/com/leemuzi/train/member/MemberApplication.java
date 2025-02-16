package com.leemuzi.train.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Author 李Muzi
 * @Date 2025/2/15 17:10
 * @Description
 */
@SpringBootApplication
public class MemberApplication {

    private static final Logger LOG = LoggerFactory.getLogger(MemberApplication.class);

    public static void main(String[] args) {
//        SpringApplication.run(MemberApplication.class, args);
        ConfigurableEnvironment env = new SpringApplication(MemberApplication.class).run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("地址:\thttp:127.0.0.1:{}", env.getProperty("server.port"));
    }
}
