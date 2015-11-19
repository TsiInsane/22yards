package misc;

import java.sql.*;

import player.Attributes.Player;
public class DbConn
{
	public static Player get_player_info(int playerId)
	{
		Player player = new Player();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tsi","tsi");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from player_info where player_id='"+playerId+"'");
			while(rs.next())
			{
				player.setfName(rs.getString("name_first"));
				player.setlName(rs.getString("name_last"));
				player.setBatting(rs.getInt("skill_batting"));
				player.setBowling(rs.getInt("skill_bowling"));
				player.setKeeping(rs.getInt("skill_keeping"));
				player.setFielding(rs.getInt("skill_fielding"));
				player.setFitness(rs.getInt("fitness"));
				player.setForm(rs.getInt("form"));
				player.setExperience(rs.getInt("experience"));
				player.setPlayerId(rs.getInt("player_id"));
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return player;
	}
}