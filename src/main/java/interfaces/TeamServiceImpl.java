package interfaces;

import entity.Team;

import java.sql.SQLException;
import java.util.List;

public interface TeamServiceImpl {
    void createTeam(Team team);

    Team getTeamById(int id);

    List<Team> getAllTeam();

    void deleteTeamById(int id) throws SQLException;

    void updateTeam(Team team) throws SQLException;
}
