package com.spring.school.controller;

import com.spring.school.model.Profile;
import com.spring.school.service.ProfileService;
import com.spring.school.validation.ValidationGroups;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller("profileControllerBean")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/displayProfile")
    public String displayProfile(Model model, Authentication authentication) {
        Profile profile = profileService.getProfile(authentication);
        model.addAttribute("profile", profile);
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(
            @Validated(ValidationGroups.ProfileUpdate.class) @ModelAttribute("profile")
            Profile profile,
            BindingResult bindingResult, Authentication authentication,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "profile";
        }

        boolean updated = profileService.updateProfile(profile, authentication);

        if (updated) {
            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update profile");
        }
        return "redirect:/displayProfile";
    }
}