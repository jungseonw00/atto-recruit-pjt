package atto.recruit.pjt.host.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HostController {

	private final HostService hostService;
}
