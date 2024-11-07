package com.pbd.PBD.Project.Repository;

import com.pbd.PBD.Project.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("SELECT p FROM Player p")
    List<Player> getAllPlayers();

}
