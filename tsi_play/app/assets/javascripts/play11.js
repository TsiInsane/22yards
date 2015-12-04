function getCheckedBoxes(chkboxName) {
  var checkboxes = document.getElementsByName(chkboxName);
  var checkboxesChecked = [];
  for (var i=0; i<checkboxes.length; i++) {
     if (checkboxes[i].checked) {
        checkboxesChecked.push(checkboxes[i].value);
     }
  }
  return checkboxesChecked.length > 0 ? checkboxesChecked : null;
}

function team_a_select() {
	var checkedBoxes = getCheckedBoxes("cbox");
    document.getElementById("team_a").value = checkedBoxes;
}

function team_b_select() {
	var checkedBoxes = getCheckedBoxes("cbox");
    document.getElementById("team_b").value = checkedBoxes;
}