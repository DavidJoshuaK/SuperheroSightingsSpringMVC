/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.model.User;
import com.sg.supersightings.servicelayer.UserServiceLayer;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DavidKing
 */
@Controller
public class UserController {

    private UserServiceLayer service;
    private PasswordEncoder encoder;

    @Inject
    public UserController(UserServiceLayer service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List users = service.getAllUsers();
        model.put("users", users);
        return "displayUserList";
    }

    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Map<String, Object> model) {
        return "addUserForm";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest req) {
        User newUser = new User();

        newUser.setUsername(req.getParameter("username"));
        String clearPw = req.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);

        newUser.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }

        service.addUser(newUser);

        return "redirect:displayUserList";
    }

    @RequestMapping(value = "/displayEditUserForm", method = RequestMethod.GET)
    public String displayEditUserForm(@RequestParam("userid") String user, Model model) {
        int userId = Integer.parseInt(user);
        User users = service.getUserById(userId);
        model.addAttribute("user", users);
        return "editUserForm";
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editUser(HttpServletRequest req, @Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "editUserForm";
        }
        User oldUser = service.getUserById(user.getId());
        service.deleteAuthority(oldUser.getUsername());
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setId(user.getId());
        String clearPw = user.getPassword();
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);

        newUser.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }

        service.updateUser(newUser);

        return "redirect:displayUserList";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deletUser(@RequestParam("username") String username,
            Map<String, Object> model) {
        service.deleteUser(username);
        return "redirect:displayUserList";
    }
}
