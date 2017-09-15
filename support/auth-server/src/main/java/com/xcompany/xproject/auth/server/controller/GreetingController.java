package com.xcompany.xproject.auth.server.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xcompany.xproject.auth.server.model.Greeting;
import com.xcompany.xproject.auth.server.model.User;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@AuthenticationPrincipal User user) {
		return new Greeting(counter.incrementAndGet(),
				String.format(template, user.getLogin()));
	}

}
