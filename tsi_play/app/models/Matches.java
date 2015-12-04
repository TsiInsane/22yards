package models;
import javax.persistence.*;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;
import com.avaje.ebean.Query;

@Entity

@Table(name = "match_tbl")
public class Matches extends Model {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int id;
	
	
	public String player_ids_A;
	public String player_ids_B;
	
	public String Aft;
	
	
    public static Model.Finder<Integer, Matches> match_find = new Model.Finder<>(Matches.class);

}
