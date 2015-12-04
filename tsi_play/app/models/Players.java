package models;
import javax.persistence.*;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;
import com.avaje.ebean.Query;


@Entity

@Table(name = "player_info")
public class Players extends Model {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int id;

    public String name;
    
    public String tm;
    public String tc;
    public String ss;
    public String bf;
    public String bs;
    public String ag;
    public String sp;
    public String sw;
    public String va;
    public String ct;
    public String type;
    
    
    
    
   public static Model.Finder<Integer, Players> player_find = new Model.Finder<>(Players.class);

}