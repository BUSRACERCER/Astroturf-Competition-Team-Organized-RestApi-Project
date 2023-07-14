package com.works.demo.services;

import com.works.demo.configs.Rest;
import com.works.demo.entities.BackUp;
import com.works.demo.entities.Teams;
import com.works.demo.projections.IProCat2;
import com.works.demo.repositories.BackUpRepository;
import com.works.demo.repositories.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsService {

    final TeamsRepository teamsRepository;
    final BackUpRepository backUpRepository;
    final HttpServletRequest req;


    public ResponseEntity teamInsert(Long tid) {
        Teams teams = new Teams();
        List<Teams> ls = teamsRepository.findByTid(tid);
        try {
            teams.setTid(tid);
            Long fid = (Long) req.getSession().getAttribute("player");
            teams.setFid(fid);

            if (ls.size() < 6) {
                teamsRepository.save(teams);

                Rest rest = new Rest(true, teams);
                return new ResponseEntity(rest, HttpStatus.OK);
            } else {
                BackUp backup = new BackUp();
                backup.setTid(tid);
                backup.setFid(fid);
                if (backUpRepository.findByTid(tid).size() < 3) {
                    backUpRepository.save(backup);
                }
            }
        } catch (Exception ex) {
            Rest rest = new Rest(false, teams);
            return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
        }
        return null;
    }


    public HashMap<String, Object> teamCreate() {
        List<IProCat2> ls1 = new ArrayList<>();
        List<IProCat2> ls2 = new ArrayList<>();
        HashMap<String, Object> hm = null;
        for (IProCat2 items1 : teamsRepository.TeamsProCat()) {

            hm = new LinkedHashMap<>();
            if (items1.getTEAMNAME().equals("B")) {
                ls1.add(items1);
            }
            hm.put("B", ls1);

        }
        for (IProCat2 items2 : teamsRepository.TeamsProCat()) {
            if (items2.getTEAMNAME().equals("A")) {
                ls2.add(items2);
            }
            hm.put("A", ls2);
        }
        return hm;
    }
}

