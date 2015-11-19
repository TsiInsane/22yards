import player.Attributes.Player;
import team.Attributes.Team;
import matches.Match;
 

class Engine
{	
	public static void main(String args[]) throws InterruptedException
	{
		Player batsman1 = misc.DbConn.get_player_info(1);
		Player batsman2 = misc.DbConn.get_player_info(2);
		Player bowler = misc.DbConn.get_player_info(5);
		int aft = 200;

		Match match1 = new Match();
		
		Team team1 = new Team();
		team1.setTeamId(101);
		Team team2 = new Team();
		team2.setTeamId(102);
		
		// After toss
		
		team1.setinnings(1);
		team2.setinnings(2);
		
		
		int firstInningSocre=Simulator.simulation(batsman1,batsman2,bowler,aft,team1, match1); 
		match1.setFirstInningScore(firstInningSocre); 
	
		Thread.sleep(1000);
		
		
		int secondInningScore=Simulator.simulation(batsman1,batsman2,bowler,(match1.getFirstInningScore()+20),team2, match1); 
		match1.setSecondInningScore(secondInningScore);
	}
}