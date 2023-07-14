package com.works.demo.services;

import com.works.demo.projections.IProCat;
import com.works.demo.repositories.BackUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BackService {


    final BackUpRepository backUpRepository;

    public HashMap<String, Object> backup() {

        List<IProCat> ls1 = new ArrayList<>();
        List<IProCat> ls2 = new ArrayList<>();


        HashMap<String, Object> hm = null;
        for (IProCat backUp : backUpRepository.BackProCat()) {
            hm = new LinkedHashMap<>();
            if (backUp.getTEAMNAME().equals("B")) {

                ls2.add(backUp);
            }
            hm.put("B", ls2);
        }
        for (IProCat backUp : backUpRepository.BackProCat()) {
            if (backUp.getTEAMNAME().equals("A")) {
                ls1.add(backUp);
            }
            hm.put("A", ls1);
        }
        return hm;
    }

}
