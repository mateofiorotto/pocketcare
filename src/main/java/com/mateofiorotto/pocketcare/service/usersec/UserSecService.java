package com.mateofiorotto.pocketcare.service.usersec;

import com.mateofiorotto.pocketcare.entity.UserSec;
import com.mateofiorotto.pocketcare.repository.IUserSecRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSecService implements IUserSecService {
    private IUserSecRepository userSecRepository;

    public UserSecService(IUserSecRepository userSecRepository) {
        this.userSecRepository = userSecRepository;
    }

    @Override
    public int usersCount() {
        return 0;
    }

    @Override
    public UserSec findAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();

        UserSec currentUser = userSecRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        return currentUser;
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
