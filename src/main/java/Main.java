import entity.Match;
import entity.Team;
import service.MatchService;
import service.TeamService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        TeamService teamService = new TeamService();
        MatchService matchService = new MatchService();
        Team team = new Team(2,"shahta", "rebrov");
        Team team1 = new Team(1,"dynamo", "rebrov");
        Team team2 = new Team(3,"real", "rebrov");
        Match match = new Match(2,team,team1,"0:0");
        Match match1 = new Match(2,team,team2,"3:2");
        teamService.createTeam(team);
        System.out.println(teamService.getTeamById(2));
        System.out.println(teamService.getAllTeam());
        teamService.deleteTeamById(2);
        matchService.createMatch(match);
        teamService.createTeam(team2);
        System.out.println(matchService.getMatchById(1));
        System.out.println(matchService.getAllMatch());
        matchService.deleteMatchById(1);
        matchService.updateMatch(match);
    }
}
