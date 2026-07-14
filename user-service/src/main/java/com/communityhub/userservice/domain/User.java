package com.communityhub.userservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tb_user")
public class User {
    @Id
    private String id;

    private String username;

    private String email;

    private String password;

}
