package com.works.demo.repositories;

import com.works.demo.entities.Teams;
import com.works.demo.projections.IProCat2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamsRepository extends JpaRepository<Teams, Long> {
    @Query(value = "select p.name,p.surname,t1.TeamName from player as p inner join Teams t on f.fid = t.fid\n" +
            "inner join team t1 on t.tid = t1.tid order by age asc ",nativeQuery = true)
    List<IProCat2> TeamsProCat();

    List<Teams> findByTid(Long tid);


}