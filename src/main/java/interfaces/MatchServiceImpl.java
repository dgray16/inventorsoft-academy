package interfaces;

import entity.Match;
import entity.Team;

import java.sql.SQLException;
import java.util.List;

public interface MatchServiceImpl {
    void createMatch(Match match);

    Match getMatchById(int id);

    List<Match> getAllMatch();

    void deleteMatchById(int id) throws SQLException;

    void updateMatch(Match match) throws SQLException;
}
