package com.mii.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ServerAplikasiPengajuanCutiDanIzinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerAplikasiPengajuanCutiDanIzinApplication.class, args);
                System.out.println("==========RUNNING==========");
	}

}
