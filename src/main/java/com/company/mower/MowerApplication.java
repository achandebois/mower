package com.company.mower;

import com.company.mower.service.MowerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MowerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MowerApplication.class, args);
        final MowerService mowerService = new MowerService();
        mowerService.run(args[0]);
    }

}
