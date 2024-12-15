package com.pbd.PBD.Project.Controller;

import com.pbd.PBD.Project.DTO.BestPlayerDTO;
import com.pbd.PBD.Project.DTO.PlayerWithMostGamesDTO;
import com.pbd.PBD.Project.Entity.Joc;
import com.pbd.PBD.Project.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @GetMapping(value = "/get-games-by-id", produces = "application/json")
    public ResponseEntity<List<Joc>> findPlayerById(@Param(value = "id") Long id) {

        try {
            return ResponseEntity.ok(gameService.findGamesByPlayerId(id));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @GetMapping(value = "get-games-by-player-id", produces = "application/json")
    public ResponseEntity<List<Joc>> findGamesByPlayerId(@RequestParam(value = "id") Long id) {
        try {
            List<Joc> games = gameService.findGamesByPlayerId(id);
            return ResponseEntity.ok(games);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404 if no games found
        }
    }
}
