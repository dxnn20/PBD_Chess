package com.pbd.PBD.Project.Repository;

import com.pbd.PBD.Project.Entity.Joc;
import com.pbd.PBD.Project.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {


}
