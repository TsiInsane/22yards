import player.Attributes.Player;
import team.Attributes.Team;
import matches.Match;


class Simulator
{
	public static Player rotate_strike(Player currBat, Player bat1, Player bat2)
	{
		return currBat == bat1 ? bat2 : bat1;
	}
	
	


	public static int simulation(Player bat1, Player bat2, Player bowler, int aft1, Team team, Match match1) throws InterruptedException
	{
		Player currentBatsman = bat1;	

		int runs = 0;
		int total_runs = 0;
		int wickets = 0;
		int spell = 5;    // Bowler at position 5,6,7,8,9 will bowl
		double sigma = 1.0;
		double mu = 0.0;
		double p = 0.0;
        double balance = 0.0;
        int aft = aft1;
        int over_a =0, over_b=0;
        double sd_dec;
        
        if (team.getinnings() == 1)
        {
        	System.out.println("First innings");
        }
        else 
        {
        	System.out.println("Second innings");
        }
        
    	if(team.getinnings() == 2)
		{
			sd_dec=0.0;
		}	
    	else
    	{
    		sd_dec=(-0.9-Math.min(mu,0));
    	}	
	
        
		for (int i=1; i<=120; i++)
		{
			Thread.sleep(50);
			
			p = Math.random();
		
			mu = Math.log10((float)currentBatsman.getBatting()/bowler.getBowling());
    
			if(team.getinnings() == 2)
			{
				sd_dec=0.0;
			}	
	    	else
	    	{
	    		sd_dec=(-0.9-Math.min(mu,0));
	    	}	
		
			
			if ( i>1 && i<=36)
			{
				sigma = 1.0 + Math.max(balance, sd_dec) + 0.2 ;  //PP
					
			}
			else if (i>36 && i<=102)
			{
				sigma = 1.0 + Math.max(balance, sd_dec);  
			}
			
			else if (i>102) // Left free in final 3 overs
			{
				sigma= 1.0;				
			}
	
			
			
			
			if (currentBatsman.getBatting() > bowler.getBowling() )
			{
				mu = mu * 0.3;
			}
			else
			{
				mu = mu * 0.6;
			}
			
			double func_value = misc.norm_inv.compute(p,mu,sigma);
    
			if (func_value < -2.056)
			{
				runs = 0;
				wickets = wickets+1;
				
				if (wickets == 10 && team.getinnings() == 1)
				{
					System.out.println("All out for " + total_runs);
					team.setinningScore(total_runs);
					break;
				}
				else if (wickets == 10 && team.getinnings() == 2 && total_runs < (match1.getFirstInningScore()))
				{
					System.out.println("Team B score: "+total_runs);
					System.out.println("Team A won by " + (match1.getFirstInningScore()-total_runs) + " runs");
					break;
				}	
				
				if(currentBatsman == bat1)
				{
					bat1 = misc.DbConn.get_player_info(wickets+2);
					currentBatsman = bat1;	
				}
				else
				{
					bat2 = misc.DbConn.get_player_info(wickets+2);
					currentBatsman = bat2;	
				}
				
			}
			else if (func_value > -2.056 && func_value < 0.051 )
			{
				runs=0;
			}    
			else if (func_value > 0.051 && func_value < 0.583)
			{
				runs=1;
				currentBatsman = rotate_strike(currentBatsman, bat1, bat2);
			}			
			else if (func_value > 0.583 && func_value < 0.916)
			{
				runs=2;
			}
			else if (func_value > 0.916 && func_value < 1.127)
			{
				runs=3;
				currentBatsman = rotate_strike(currentBatsman, bat1, bat2);
			}
			else if (func_value > 1.127 && func_value < 2.056)
			{
				runs=4;
			}
			else if (func_value > 2.056)
			{
				runs=6;
			}
			total_runs= total_runs + runs;
		
			
			if (i<120)
			{
				balance = (aft-total_runs)/(120-i) - (150/120);
			}
			
			if ((i-1)%30 == 0)
			{
				bowler = misc.DbConn.get_player_info(++spell);
								
			}
			
			over_b=over_b+1;
			
			if (i%6 == 0)
			{
				over_a=over_a+1;
				over_b=0;
			}
				
	//		System.out.println("Overs: "+ over_a + "." + over_b + ", Batsman: " + currentBatsman.getfName() + ", Bowler: " + bowler.getfName() + ", Runs: " + runs + ", Score: "+total_runs+" / "+wickets);
			
			if (i%6 == 0)
			{
				currentBatsman = rotate_strike(currentBatsman, bat1, bat2);
			}
			
			if (team.getinnings() == 2 && total_runs>match1.getFirstInningScore())
			{   
				System.out.println("Team B score: "+total_runs);
				System.out.println("Team B won by " + (10-wickets) + " wickets");
				break;
			}
		
			
			team.setinningScore(total_runs);
				
		}
		// Inning ends
		
		
		
		if (team.getinnings() == 1)
		{
			System.out.println("Team A score: "+total_runs);
		}    
		
		    
		if (team.getinnings() == 2 && total_runs < match1.getFirstInningScore())
		{
			System.out.println("Team B score: "+total_runs);
			System.out.println("Team A won by " + (match1.getFirstInningScore()-total_runs) + " runs");
		}
		
		return team.getinningScore();	
	}
}