package com.pbd.PBD.Project.Controller;

import com.pbd.PBD.Project.DTO.BestPlayerDTO;
import com.pbd.PBD.Project.DTO.PlayerWithMostGamesDTO;
import com.pbd.PBD.Project.Entity.Player;
import com.pbd.PBD.Project.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {

    private final GameService gameService;

    @Autowired
    public PlayerController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/best-player", produces = "application/json")
    public ResponseEntity<BestPlayerDTO> getBestPlayer() {

        try {
            return ResponseEntity.ok(gameService.findBestPlayerWithWinCount());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @GetMapping(value = "/most-games-player", produces = "application/json")
    public ResponseEntity<PlayerWithMostGamesDTO> findPlayerWithMostGames() {

        try {
            return ResponseEntity.ok(gameService.findPlayerWithMostGamesPlayed());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
