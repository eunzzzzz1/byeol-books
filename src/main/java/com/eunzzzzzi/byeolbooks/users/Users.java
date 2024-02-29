package com.eunzzzzzi.byeolbooks.users;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Users {

    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_nickname")
    private String userNickname;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_sex")
    private int userSex;
    private String postcode;
    @Column(name = "user_addr1")
    private String userAddr1;
    @Column(name = "user_addr2")
    private String userAddr2;
    @Column(name = "user_addr3")
    private String userAddr3;
    private String provider;
    @Column(name = "provider_token")
    private String providerToken;
    @Column(name = "create_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createDate;

}
