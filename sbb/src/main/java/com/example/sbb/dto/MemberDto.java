package com.example.sbb.dto;

import com.example.sbb.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String email;

    public static MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .build();
    }
}
