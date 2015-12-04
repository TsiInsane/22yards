package controllers;
import play.db.ebean.Transactional;
import play.mvc.*;
import views.html.*;
import models.Players;
import models.Matches;

import play.data.Form;

import java.util.List;

import static play.libs.Json.*;



public class Application extends Controller {
	
@Transactional
public Result index() {
    return ok(index.render());
}

@Transactional
public Result addPlayer() {
    Players player = Form.form(Players.class).bindFromRequest().get();
    player.save();
    return redirect(routes.Application.index());
}

@Transactional
public Result startMatch() {
    Matches match = Form.form(Matches.class).bindFromRequest().get();
    match.save();
    return redirect(routes.Application.index());
}

@Transactional
public Result getPlayer() {
	List<Players> players = Players.player_find.findList();
	
	return ok(toJson(players));
}


@Transactional
public Result getSpecificPlayer() {
	List<Players> players3 = Players.player_find.where().ilike("id","2").findList();
	
	return ok(toJson(players3));
}


}