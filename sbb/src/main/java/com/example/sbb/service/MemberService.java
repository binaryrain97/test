package com.example.sbb.service;

import com.example.sbb.entity.Member;
import com.example.sbb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(String username, String password, String email) {
        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        Member created = memberRepository.save(member);
        return created;
    }
    public Member getMember(String name) {
        return this.memberRepository.findByUsername(name).orElse(null);
    }
}
