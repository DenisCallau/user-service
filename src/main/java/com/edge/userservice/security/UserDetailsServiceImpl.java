package com.edge.userservice.security;


import com.edge.userservice.dto.NewPasswordDTO;
import com.edge.userservice.exception.PasswordIncorrectException;
import com.edge.userservice.model.User;
import com.edge.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = repo.findByEmail(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return optional.get();
        }
    }

    public void changePassword(NewPasswordDTO dto) throws Exception {
        User currentUser = getCurrentUser();
        if (validateCurrentPassword(dto.getCurrentPassword(), currentUser) &&
                validateNewPassword(dto.getNewPassword(), dto.getNewPasswordConfirmation()) &&
                validateSamePassword(dto.getCurrentPassword(), dto.getNewPassword(), dto.getNewPasswordConfirmation())) {
            currentUser.changePassword(dto.getNewPassword());
            repo.save(currentUser);
        }
    }

    private boolean validateCurrentPassword(String password, User currentUser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, currentUser.getPassword())) {
            throw new PasswordIncorrectException("Current password is incorrect");
        } else {
            return true;
        }
    }

    private boolean validateNewPassword(String newPassword, String newPasswordConfirmation) throws Exception {
        if (!newPassword.equals(newPasswordConfirmation)) {
            throw new PasswordIncorrectException("New passwords don't match");
        } else {
            return true;
        }
    }

    private boolean validateSamePassword(String currentPassword, String newPassword, String newPasswordConfirmation) {
        if (newPassword.equals(newPasswordConfirmation) && currentPassword.equals(newPassword)) {
            throw new PasswordIncorrectException("New password cannot be the current password");
        } else {
            return true;
        }
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Optional<User> optional = repo.findByEmail(user.getUsername());
        return optional.orElseThrow();
    }

}
