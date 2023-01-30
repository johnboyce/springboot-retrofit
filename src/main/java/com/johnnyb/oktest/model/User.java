package com.johnnyb.oktest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private long id;
    private String first_name;
    private String last_name;
    private String email;

    private String avatar;
}
