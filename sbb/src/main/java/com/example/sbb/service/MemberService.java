package com.example.sbb.service;

import com.example.sbb.dto.MemberDto;
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
    public MemberDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if(member == null) return null;
        return MemberDto.toDto(member);
    }
    public MemberDto findByUsername(String name) {
        Member member = memberRepository.findByUsername(name).orElse(null);
        if(member == null) return null;
        return MemberDto.toDto(member);
    }
}
