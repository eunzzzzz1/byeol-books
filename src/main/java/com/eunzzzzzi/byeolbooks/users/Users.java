package com.eunzzzzzi.byeolbooks.users;

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
    private String user_id;
    private String user_password;
    private String user_nickname;
    private String user_email;
    private int user_sex;
    private String user_addr1;
    private String user_addr2;
    private String provider;
    private String provider_token;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime create_date;

}
