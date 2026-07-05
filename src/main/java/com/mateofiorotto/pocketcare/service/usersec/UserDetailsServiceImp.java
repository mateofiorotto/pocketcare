package com.mateofiorotto.pocketcare.service.usersec;

import com.mateofiorotto.pocketcare.entity.UserSec;
import com.mateofiorotto.pocketcare.repository.IUserSecRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private IUserSecRepository userSecRepository;

    public UserDetailsServiceImp(IUserSecRepository userSecRepository) {
        this.userSecRepository = userSecRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserSec userSec = userSecRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //Convert simple ROLE (enum) to List. Spring Security needs a list of GrantedAuthority
        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + userSec.getRole().name()));

        return new User(userSec.getEmail(),
                userSec.getPassword(),
                userSec.getEnabled(),
                true,
                true,
                true,
                authorities);
    }
}
