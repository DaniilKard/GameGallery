package com.kard.controller;

import com.kard.entity.Game;
import com.kard.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/game")
    public String postGame(@RequestBody Game game, Model model) {
        Game savedGame = gameService.saveGame(game);
        model.addAttribute("game", game);
        return "game";
    }

    @GetMapping("/game/{id}")
    public String getGame(@PathVariable("id") String id, Model model) throws Exception {
            Game game = gameService.getGame(Integer.parseInt(id));
            model.addAttribute("game", game);
        return "game";
    }

    @PutMapping("/game/{id}")
    public String putGame(@RequestBody Game game, @PathVariable("id") long id, Model model) {
        Game currentGame = null;
        try {
            currentGame = gameService.getGame(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (currentGame != null) {
            if (game.getDescription() != null) {
                currentGame.setDescription(game.getDescription());
            }
            if (game.getId() != 0) {
                currentGame.setId(game.getId());
            }
            if (game.getName() != null) {
                currentGame.setName(game.getName());
            }
        }
        Game savedGame = gameService.saveGame(currentGame);
        model.addAttribute("game", currentGame);
        return "game";
    }

    @DeleteMapping("/game/{id}")
    public String deleteGame(@PathVariable("id") long id, Model model) throws Exception {
        gameService.deleteGame(id);
        return "game";
    }
}