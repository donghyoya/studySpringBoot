package study.querydsl.domain.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.domain.team.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
