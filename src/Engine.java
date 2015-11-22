import player.Attributes.Player;
import team.Attributes.Team;
import matches.Match;
 

class Engine
{	
	public static void main(String args[]) throws InterruptedException
	{
		Player batsman_1a = misc.DbConn.get_player_info(1);
		Player batsman_2a = misc.DbConn.get_player_info(2);
		Player bowler_a = misc.DbConn.get_player_info(5);
		
		Player batsman_1b = misc.DbConn.get_player_info(12);
		Player batsman_2b = misc.DbConn.get_player_info(13);
		Player bowler_b = misc.DbConn.get_player_info(16);
		
		
		int aft = 200;

		System.out.println("AFT for all Matches " +aft);
		
		
		for (int i = 1001; i<=1020; i++)
		{
		Match match1 = new Match();
		match1.setMatchId(i);
		
		System.out.println("\nMatch No. " +match1.getMatchId());
		
		Team team1 = new Team();
		team1.setTeamId(101);
		Team team2 = new Team();
		team2.setTeamId(102);
		
		// After toss
		
		team1.setinnings(1);
		team2.setinnings(2);
		
		
		int firstInningSocre=Simulator.simulation(batsman_1a,batsman_2a,bowler_a,aft,team1, match1); 
		match1.setFirstInningScore(firstInningSocre); 
	
		Thread.sleep(100);
		
		int secondInningScore=Simulator.simulation(batsman_1b,batsman_2b,bowler_b,(firstInningSocre+(firstInningSocre/20)),team2, match1); 
		match1.setSecondInningScore(secondInningScore);
	
		}

	}
}	