package com.playground.admin_page.main.user.controller;
import com.playground.admin_page.main.user.model.dto.InfoDto;
import com.playground.admin_page.main.user.model.dto.UserEmailAble;
import com.playground.admin_page.main.user.model.dto.UserPet;
import com.playground.admin_page.main.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@SessionAttributes({"adminAccount"})
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model) {
        log.info("Get /user/list");
        List<InfoDto> users = userService.findAll();
        log.debug("users = {}", users);
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/findById")
    public String findById(@RequestParam("userId") Long userId, Model model) {
        log.info("Get /user/findById");
        List<InfoDto> users = userService.findById(userId);
        log.debug("users = {}", users);
        model.addAttribute("users", users);
        return "user/searchList";
    }

    @GetMapping("/findByPet")
    public String findByPet(@RequestParam("userPet") UserPet userPet, Model model) {
        log.info("Get /user/findByPet");
        List<InfoDto> users = userService.findByPet(userPet);
        log.debug("users = {}", users);
        model.addAttribute("users", users);
        return "list/searchList";
    }

    @GetMapping("/findByEmailAble")
    public String findByEmailAbleStatus(@RequestParam("userEmailAble") UserEmailAble userEmailAble, Model model) {
        log.info("Get /user/findByEmailAbleStatus");
        List<InfoDto> users = userService.findByEmailAble(userEmailAble);
        log.debug("users = {}", users);
        model.addAttribute("users", users);
        return "user/sendEmail";
    }

    @GetMapping("/setDormantUsers")
    public String setDormantUsers() {
        userService.setDormant();
        return "redirect:/user/list";
    }
}


