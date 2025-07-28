package com.spring.school.service;

import com.spring.school.exception.UserNotFoundException;
import com.spring.school.model.Address;
import com.spring.school.model.Profile;
import com.spring.school.model.User;
import com.spring.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    private final UserRepository userRepository;

    @Autowired
    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Profile getProfile(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found: " + authentication.getName()));

        Profile profile = new Profile();
        profile.setUsername(user.getUsername());
        profile.setEmail(user.getEmail());
        profile.setPhone(user.getPhone());

        if (user.getAddress() != null) {
            profile.setAddress1(user.getAddress().getAddress1());
            profile.setAddress2(user.getAddress().getAddress2());
            profile.setCity(user.getAddress().getCity());
            profile.setState(user.getAddress().getState());
            profile.setZipCode(user.getAddress().getZipCode());
        }

        return profile;
    }

    @Transactional
    public boolean updateProfile(Profile profile, Authentication authentication) {
        try {
            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("User not found: "));
            user.setEmail(profile.getEmail());
            user.setPhone(profile.getPhone());
            user.setUsername(profile.getUsername());

            Address address = user.getAddress();
            if (address == null) {
                address = new Address();
            }

            address.setAddress1(profile.getAddress1());
            address.setAddress2(profile.getAddress2());
            address.setCity(profile.getCity());
            address.setState(profile.getState());
            address.setZipCode(profile.getZipCode());

            user.setAddress(address);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
