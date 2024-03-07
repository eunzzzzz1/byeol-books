package com.eunzzzzzi.byeolbooks.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    // 유저 정보가 담긴 Entity를 의존성 주입을 통해 불러와준다.
    private Users users;

    // 권한 관련 작업을 하기 위한 role을 return 하면 된다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return users.getUserPassword();
    }

    @Override
    public String getUsername() {
        return users.getUserId();
    }

    // 계정이 만료되었는지?
    // 만료계정 / 만료X 계정을 표기하는 컬럼을 DB에 추가해 활용하면 될 듯 하다.
    // 만료된 계정이라는게 따로 없으므로 true로 설정해준다.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겼는지?
    // 마찬가지로 이 프로젝트에서는 잠긴 계정 / 잠기지 않은 계정이 따로 있지 않으므로
    // true(잠기지 않음)으로 설정해준다.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 true - 만료 X
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화(사용가능) 여부 true - 활성화
    @Override
    public boolean isEnabled() {
        return true;
    }
}
