package ru.otus.dto;

import lombok.*;
import ru.otus.domain.Role;

import java.util.Set;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private long id;

    private String username;

    private String password;

    private Set<Role> roles;
}
