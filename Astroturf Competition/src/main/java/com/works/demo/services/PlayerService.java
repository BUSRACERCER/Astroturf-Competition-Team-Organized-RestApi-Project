package com.works.demo.services;

import com.works.demo.configs.Rest;
import com.works.demo.entities.Player;
import com.works.demo.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    final PlayerRepository playerRepository;
    final TinkEncDec tinkEncDec;

    public ResponseEntity save(Player player) {

        String chipherText = tinkEncDec.encrypt(player.getPassword());
        player.setPassword(chipherText);

        try {

            playerRepository.save(player);
            Rest rest = new Rest(true, player);
            return new ResponseEntity(rest, HttpStatus.OK);

        } catch (Exception ex) {

            Rest rest = new Rest(false, ex.getMessage());
            return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
        }
    }

    public Player login(Player player) {

        List<Player> players = playerRepository.findAll();

        for (Player player1 : players) {

            if (player.getEmail().equals(player.getEmail())) {

                String plainText = tinkEncDec.decrypt(player.getPassword());
                if (player.getPassword().equals(plainText)) {

                    return player1;
                }
            }
        }
        return null;
    }


}
