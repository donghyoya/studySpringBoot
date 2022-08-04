package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeamRepository {

    private final EntityManager em;

    public Team save(Team team){
        em.persist(team);
        return team;
    }

    public void delect(Team team){
        em.remove(team);
    }

    public List<Team> findAll(){
        List<Team> teams = em.createQuery("select t from Team t", Team.class)
                .getResultList();
        return teams;
    }

    public Optional<Team> findById(Long id){
        Team team = em.find(Team.class, id);
        return Optional.ofNullable(team);
    }

    public Long Count(){
        Long cnt = em.createQuery("select count(t) from Team t", Long.class)
                .getSingleResult();
        return cnt;
    }
}
