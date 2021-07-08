package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private int id;
    private Team team1;
    private Team team2;
    private String score;


}
