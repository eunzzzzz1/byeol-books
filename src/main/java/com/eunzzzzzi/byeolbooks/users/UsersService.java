package com.eunzzzzzi.byeolbooks.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Users create(String user_id,
                        String user_password,
                        String user_nickname,
                        String user_email,
                        int user_sex,
                        String postcode,
                        String user_addr1,
                        String user_addr2,
                        String user_addr3,
                        String provider,
                        String provider_token) {
        Users users = new Users();
        users.setUser_id(user_id);
        users.setUser_password(passwordEncoder.encode(user_password));
        users.setUser_nickname(user_nickname);
        users.setUser_email(user_email);
        users.setUser_sex(user_sex);
        users.setPostcode(postcode);
        users.setUser_addr1(user_addr1);
        users.setUser_addr2(user_addr2);
        users.setUser_addr3(user_addr3);
        users.setProvider(provider);
        users.setProvider_token(provider_token);
        users.setCreate_date(LocalDateTime.now());
        userRepository.save(users);

        return users;
    }
}

/*
    private String user_id;
    private String user_password;
    private String user_name;
    private String user_nickname;
    private String create_date;
    private String user_email;
    private int user_sex;
    private String user_birth;
    private String user_addr1;
    private String user_addr2;
    private String provider;
    private String provider_token;

 */