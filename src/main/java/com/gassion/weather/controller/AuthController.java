package com.gassion.weather.controller;

import com.gassion.weather.dto.UserRegisterRequestDTO;
import com.gassion.weather.service.RegisterService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final RegisterService registerService;

    @Autowired
    public AuthController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            if (userDetail != null) {
                model.addAttribute("user", userDetail);
            }
        }

        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserRegisterRequestDTO());
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserRegisterRequestDTO userRegisterRequestDTO, BindingResult validateResult, Model model,
                               HttpServletRequest request) throws ServletException {

        if(validateResult.hasErrors()){
            model.addAttribute("user", userRegisterRequestDTO);
            return "/register";
        }

        registerService.saveUserOrSetResult(userRegisterRequestDTO, validateResult);

        if(validateResult.hasErrors()){
            model.addAttribute("user", userRegisterRequestDTO);
            return "/register";
        }

        request.login(userRegisterRequestDTO.getEmail(), userRegisterRequestDTO.getPassword());

        return "redirect:/";
    }
}
