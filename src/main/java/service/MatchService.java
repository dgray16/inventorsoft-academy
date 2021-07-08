package service;

import entity.Match;
import entity.Team;
import interfaces.MatchServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchService implements MatchServiceImpl {
    TeamService teamService = new TeamService();

    private static final String CREATE_MATCH_SQL = "INSERT INTO `match`" + "  (id, first_team_id,second_team_id, score) VALUES " +
            " (?, ?, ?, ?)";

    //private static final String GET_MATCH_BY_ID = "select id,first_team_id,second_team_id,score from `match` where id =?";
    private static final String GET_MATCH_BY_ID = "select `match`.id,first_team_id,second_team_id,score from `match`  where id =?";

    private static final String GET_ALL_MATCH = "select * from `match`\n" +
            " join team t on `match`.first_team_id = t.id\n" +
            " join team p on `match`.second_team_id = p.id";
    private static final String DELETE_MATCH_SQL = "delete from `match` where id = ?";
    private static final String UPDATE_MATCH_SQL = "update `match` set first_team_id = ?,second_team_id = ?, score = ? where id = ?";

    private String jdbcURL = "jdbc:mysql://localhost:3306/tournament";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void createMatch(Match match) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MATCH_SQL)) {
            preparedStatement.setInt(1, match.getId());
            preparedStatement.setInt(2, match.getTeam1().getId());
            preparedStatement.setInt(3, match.getTeam2().getId());
            preparedStatement.setString(4, match.getScore());
            System.out.println(preparedStatement);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("succes");
            }
        } catch (SQLException e) {

        }
    }

    @Override
    public Match getMatchById(int id) {
        Match match = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MATCH_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Team firstTeam = teamService.getTeamById(rs.getInt("first_team_id"));
                Team secondTeam = teamService.getTeamById(rs.getInt("second_team_id"));
                String score = rs.getString("score");
                match = new Match(id, firstTeam, secondTeam, score);
            }
        } catch (SQLException e) {

        }
        return match;
    }

    @Override
    public List<Match> getAllMatch() {
        List<Match> matches = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MATCH)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Team firstTeam = new Team(rs.getInt("t.id"), rs.getString("name_team"), rs.getString("coach"));
                Team secondTeam = new Team(rs.getInt("p.id"), rs.getString("name_team"), rs.getString("coach"));
                String score = rs.getString("score");
                matches.add(new Match(id, firstTeam, secondTeam, score));
            }
        } catch (SQLException e) {
        }
        return matches;
    }

    @Override
    public void deleteMatchById(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MATCH_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void updateMatch(Match match) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_MATCH_SQL)) {
            statement.setInt(1, match.getTeam1().getId());
            statement.setInt(2, match.getTeam2().getId());
            statement.setString(3, match.getScore());
            statement.setInt(4, match.getId());
            statement.executeUpdate();
        }
    }
}
