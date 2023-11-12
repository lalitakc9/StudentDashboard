package com.student.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResDto {
    private Long userId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String status;
}
