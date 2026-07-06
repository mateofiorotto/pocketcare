package com.mateofiorotto.pocketcare.service.usersec;

import com.mateofiorotto.pocketcare.dto.auth.AuthLoginRequestDTO;
import com.mateofiorotto.pocketcare.dto.auth.AuthRegisterRequestDTO;
import com.mateofiorotto.pocketcare.dto.auth.AuthResponseDTO;
import com.mateofiorotto.pocketcare.entity.Role;
import com.mateofiorotto.pocketcare.entity.UserSec;
import com.mateofiorotto.pocketcare.repository.IUserSecRepository;
import com.mateofiorotto.pocketcare.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private IUserSecRepository userSecRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;

    public UserDetailsServiceImp(IUserSecRepository userSecRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userSecRepository = userSecRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
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

    public AuthResponseDTO loginUser (AuthLoginRequestDTO authLoginRequest){
        String email = authLoginRequest.email();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate (email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(email, "login ok", accessToken, true);
        return authResponseDTO;

    }

    public String registerUser (AuthRegisterRequestDTO authRegisterRequest){

        String email = authRegisterRequest.email();
        String password = authRegisterRequest.password();

        UserSec userSec = new UserSec();

        userSec.setEmail(email);
        userSec.setPassword(passwordEncoder.encode(password));
        userSec.setRole(Role.USER);
        userSec.setEnabled(true);

        userSecRepository.save(userSec);

        this.loginUser(new AuthLoginRequestDTO(email, password));

        return "User registered successfully";

    }

    public Authentication authenticate (String email, String password) {

        UserDetails userDetails = this.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
    }


}
