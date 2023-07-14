package com.works.demo.restcontrollers;

import com.works.demo.configs.Rest;
import com.works.demo.entities.Player;
import com.works.demo.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/footballer")
public class PlayerRestController {

    final PlayerService playerService;
    final HttpServletRequest req;

    @PostMapping("/footballerRegister")
    public ResponseEntity Register(@Valid @RequestBody Player player) {

        return playerService.save(player);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Player player) {

        Player ply1 = playerService.login(player);

        if (ply1 != null) {

            req.getSession().setAttribute("player", ply1.getFid());
            Rest rest = new Rest(true, ply1);
            return new ResponseEntity(rest, HttpStatus.OK);

        } else {

            Rest rest = new Rest(false, "YANLIŞ GİRİŞ!");
            return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);

        }

    }

}
