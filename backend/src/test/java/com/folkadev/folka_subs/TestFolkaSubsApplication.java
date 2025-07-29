package com.folkadev.folka_subs;

import org.springframework.boot.SpringApplication;

public class TestFolkaSubsApplication {

	public static void main(String[] args) {
		SpringApplication.from(FolkaSubsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
