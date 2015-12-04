(function() {
  $(function() {
    return $.get("/players", function(players) {
      return $.each(players, function(index, player) {
        
        var apt =  '<tr><td width="4%" >' + '<input type="checkbox" id="cbox" name="cbox" value = "'+player.id+'" />' + '</td><td width="8%">' + player.name + '</td><td width="8%">' + player.type + 
                    '</td><td width="8%">' + player.tm +'</td><td width="8%">' + player.tc +'</td><td width="8%">' + player.ss +'</td><td width="8%">' + player.bf +'</td><td width="8%">' + player.bs +'</td><td width="8%">' + player.ag +'</td><td width="8%">' + player.sp +'</td><td width="8%">' + player.sw +'</td><td width="8%">' + player.va +'</td><td width="8%">' + player.ct + '</td></tr>'; 
        return $('#players_list').append(apt);
      });
    });
  });

}).call(this);