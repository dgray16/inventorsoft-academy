package service;

import entity.Team;
import interfaces.TeamServiceImpl;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class TeamService implements TeamServiceImpl {
    private static final String CREATE_TEAM_SQL = "INSERT INTO team" + "  (id, name_team, coach) VALUES " +
            " (?, ?, ?);";

    private static final String GET_TEAM_BY_ID = "select id,name_team,coach from team where id =?";
    private static final String GET_ALL_TEAM = "select * from team";

    private static final String DELETE_TEAM_SQL = "delete from team where id = ?";
    private static final String UPDATE_TEAM_SQL = "update team set name_team = ?,coach = ? where id = ?";

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
    public void createTeam(Team team) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TEAM_SQL)) {
            preparedStatement.setInt(1, team.getId());
            preparedStatement.setString(2, team.getNameTeam());
            preparedStatement.setString(3, team.getCoach());
            System.out.println(preparedStatement);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("succes");
            }
        } catch (SQLException e) {

        }
    }

    @Override
    public Team getTeamById(int id) {
        Team team = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_TEAM_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name_team");
                String coach = rs.getString("coach");
                team = new Team(id, name, coach);
            }
        } catch (SQLException e) {

        }
        return team;
    }

    @Override
    public List<Team> getAllTeam() {
        List<Team> teams = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TEAM);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name_team");
                String coach = rs.getString("coach");
                teams.add(new Team(id, name, coach));
            }
        } catch (SQLException e) {
        }
        return teams;
    }

    @Override
    public void deleteTeamById(int id) throws SQLException {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TEAM_SQL)) {
            statement.setInt(1, id);
        }
    }


    @Override
    public void updateTeam(Team team) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TEAM_SQL)) {
            statement.setString(1, team.getNameTeam());
            statement.setString(2, team.getCoach());
            statement.setInt(3, team.getId());
        }
    }
}

