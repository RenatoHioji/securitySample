package com.pb.week11.service.register;

import com.pb.week11.DTO.request.LoginDTORequest;
import com.pb.week11.DTO.request.RegisterDTORequest;
import com.pb.week11.security.JwtTokenProvider;
import com.pb.week11.entity.security.Role;
import com.pb.week11.entity.security.User;
import com.pb.week11.exception.UserAlreadyExistsException;
import com.pb.week11.repository.RoleRepository;
import com.pb.week11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDTORequest request) {
        if(userRepository.existsByUsername((request.getUsername()))){
            throw new UserAlreadyExistsException("Username is already exists!");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("Email is already exists!");
        }
        RegisterDTORequest requestEncoded = new RegisterDTORequest(request.getUsername(), request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        com.pb.week11.entity.security.User user = new User(requestEncoded);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return  "User registered sucessfully";
    }
    @Override
    public String login(LoginDTORequest loginDTORequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTORequest.getUsernameOrEmail(), loginDTORequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }




}
