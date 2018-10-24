package com.inventorsoft.domain.service;

import com.inventorsoft.domain.model.Member;
import com.inventorsoft.domain.repository.MemberRepository;
import com.inventorsoft.domain.service.base.GeneralService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberService extends GeneralService<Member, Integer> {

    MemberRepository memberRepository;

    MemberService(MemberRepository repository) {
        super(repository);
        this.memberRepository = repository;
    }

    @Transactional
    public Member createTestMember() {
        Member member = new Member();

        member.setFirstName("Svyatoslav");
        member.setLastName("Vakarchuk");

        return memberRepository.save(member);
    }
}
