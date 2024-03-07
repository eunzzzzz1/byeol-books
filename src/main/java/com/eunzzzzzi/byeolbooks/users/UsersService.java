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

    // DB에 유저 데이터 추가
    public Users addUser(String user_id,
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
        users.setUserId(user_id);
        users.setUserPassword(passwordEncoder.encode(user_password));
        users.setUserNickname(user_nickname);
        users.setUserEmail(user_email);
        users.setUserSex(user_sex);
        users.setPostcode(postcode);
        users.setUserAddr1(user_addr1);
        users.setUserAddr2(user_addr2);
        users.setUserAddr3(user_addr3);
        users.setProvider(provider);
        users.setProviderToken(provider_token);
        users.setCreateDate(LocalDateTime.now());
        userRepository.save(users);

        return users;
    }

    // DB에서 매개변수와 동일한 아이디 찾기
    public Users idDuplicationCheck(String userId) {
        return userRepository.findByUserId(userId);
    }

    // DB에서 매개변수와 동일한 이메일 찾기
    public boolean emailDuplicationCheck(String userEmail) {
        // 있으면 true 없으면 false
        return userRepository.findByUserEmail(userEmail) != null;
    }

    // 로그인한 유저의 Users 객체를 가지고 오는 메소드
    public Users getLoginUserByLoginId(String loginUserId) {
        return userRepository.findByUserId(loginUserId);
    }
}

