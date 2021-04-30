package com.Pandya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerUpdateDto {
    private Long id;
    private String lastName;
    private String firstName;
    private  String dateOfBirth;
}
