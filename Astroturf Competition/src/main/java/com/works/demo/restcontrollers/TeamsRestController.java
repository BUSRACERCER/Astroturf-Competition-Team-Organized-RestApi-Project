package com.works.demo.restcontrollers;

import com.works.demo.configs.Rest;
import com.works.demo.services.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamsRestController {

    final TeamsService teamsService;

    @PostMapping("/teamInsert/{tid}")
    public ResponseEntity Insert(@PathVariable Long tid) {
        return teamsService.teamInsert(tid);
    }

    @GetMapping("/teamCreate")
    public ResponseEntity Create() {
        if (teamsService.teamCreate() != null) {
            Rest rest = new Rest(true, teamsService.teamCreate());
            return new ResponseEntity(rest, HttpStatus.OK);
        } else {
            Rest rest = new Rest(false, "Takım Yok!");
            return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
        }
    }
}

