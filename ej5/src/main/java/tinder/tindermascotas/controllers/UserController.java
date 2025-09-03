package tinder.tindermascotas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tinder.tindermascotas.repositories.ZoneRepository;
import tinder.tindermascotas.service.UserService;

@Controller
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ZoneRepository zoneRepository;

}
