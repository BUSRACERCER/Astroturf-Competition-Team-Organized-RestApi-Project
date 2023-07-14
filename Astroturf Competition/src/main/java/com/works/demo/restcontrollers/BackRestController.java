package com.works.demo.restcontrollers;

import com.works.demo.configs.Rest;
import com.works.demo.services.BackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back")
public class BackRestController {

    final BackService backService;

    @GetMapping("/backUpCreate")
    public ResponseEntity backUpCreate() {

        if (backService.backup() != null) {

            Rest rest = new Rest(true, backService.backup());
            return new ResponseEntity(rest, HttpStatus.OK);

        } else {
            Rest rest = new Rest(false, "Yedek Yok!");
            return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
        }
    }
}

