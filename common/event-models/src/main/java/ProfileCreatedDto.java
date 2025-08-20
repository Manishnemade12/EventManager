package com.quantilearn.eventmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCreatedDto{
    private String email;
    private String firstName;
    private String lastName;
}