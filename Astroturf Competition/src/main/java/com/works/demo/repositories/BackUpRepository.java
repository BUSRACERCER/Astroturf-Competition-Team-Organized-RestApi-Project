package com.works.demo.repositories;

import com.works.demo.entities.BackUp;
import com.works.demo.projections.IProCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BackUpRepository extends JpaRepository<BackUp, Long> {

    @Query(value = "select p.name,p.surname,t.TeamName from Player as p inner join BackUp b on f.fid = b.fid\n" +
            "inner join Team t on b.tid = t.tid order by age asc", nativeQuery = true)
    List<IProCat> BackProCat();

    List<BackUp> findByTid(Long tid);

}

