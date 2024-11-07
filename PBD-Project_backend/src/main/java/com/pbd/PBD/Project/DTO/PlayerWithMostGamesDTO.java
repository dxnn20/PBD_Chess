package com.pbd.PBD.Project.DTO;


import com.pbd.PBD.Project.Entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerWithMostGamesDTO {

    Player player;
    Long nrOfWins;

}
