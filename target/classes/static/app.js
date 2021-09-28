function sendRest(){
	if($("#name").val()==null || $("#name").val()==''){
		alert('You must provide a Stock ID to lookup');
		return;
	}
	$.ajax({
	  type: "POST",
	  url: "/stock",
	  data: JSON.stringify({ 'quote': $("#name").val() }),
  	  contentType:"application/json; charset=utf-8",
	  dataType:"json",
	  success: showGreeting
	});
}

function showGreeting(message) {
	var quote = message.quote;
	var msg = message.message;
	var flg = message.found;
	if ($('#' + quote + '').length > 0) {
		$($('#' + quote + '')).html(`<td id="${quote}-td">${msg}</td>`);
		blink($('#' + quote + ''));
	} else {
		var row = `<tr id="${quote}"><td id="${quote}-td">${msg}</td></tr>`;
		$("#report").append(row);
	}
	if(!flg){	
		$('#' + quote + '-td').addClass("bg-warning");
	}
}
function blink(obj) {
	$(obj).fadeTo(1000, 0.1).fadeTo(200, 1.0);
}
$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$('#send').click(function() { sendRest(); });
});
