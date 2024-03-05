package com.eunzzzzzi.byeolbooks.users;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
    public Users findByUserId(String userId);
    public Users findByUserEmail(String userEmail);
}
