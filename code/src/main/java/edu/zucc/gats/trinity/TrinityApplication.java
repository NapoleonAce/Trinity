package edu.zucc.gats.trinity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("edu.zucc.gats.trinity.mapper")
@EnableCaching
public class TrinityApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrinityApplication.class,args);
    }
}
