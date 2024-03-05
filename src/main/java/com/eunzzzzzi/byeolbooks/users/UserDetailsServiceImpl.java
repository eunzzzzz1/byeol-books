package com.eunzzzzzi.byeolbooks.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        Users users = usersRepository.findByUserId(user_id);
        return toUserDetails(users);
    }

    private UserDetails toUserDetails(Users users) {
        return User.builder()
                .username(users.getUserId())
                .password(users.getUserPassword())
                .build();
    }
}
