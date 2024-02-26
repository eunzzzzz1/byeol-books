package com.eunzzzzzi.byeolbooks.users;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Users {

    @Id
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

}
