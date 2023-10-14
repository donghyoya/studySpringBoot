package study.querydsl.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.domain.member.dto.MemberSearchCondition;
import study.querydsl.domain.member.dto.MemberTeamDto;
import study.querydsl.domain.member.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom{

    public List<MemberTeamDto> search(MemberSearchCondition condition);
}
