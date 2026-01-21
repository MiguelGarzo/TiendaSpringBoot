package com.tiendaonline.tienda.users.service;

import com.tiendaonline.tienda.security.JWTUtil;
import com.tiendaonline.tienda.users.dto.UserLoginDTO;
import com.tiendaonline.tienda.users.dto.UserRegisterDTO;
import com.tiendaonline.tienda.users.dto.UserResponseDTO;
import com.tiendaonline.tienda.users.entity.User;
import com.tiendaonline.tienda.users.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserResponseDTO register(UserRegisterDTO dto) {
        String role = dto.getRole() == null ? "USER" : dto.getRole();

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        User uSaved = repository.save(user);

        UserResponseDTO uResponse = new UserResponseDTO();
        uResponse.setId(uSaved.getId());
        uResponse.setEmail(uSaved.getEmail());
        uResponse.setRole(user.getRole());

        return uResponse;
    }

    public String login(UserLoginDTO dto) {
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return JWTUtil.generateToken(user.getEmail(), user.getRole());
    }
}
