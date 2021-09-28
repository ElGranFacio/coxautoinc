var stompClient = null;
function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	$("#send").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
	$("#report").html("");
}

function connect() {
	var socket = new SockJS('/stomp-endpoint');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/quotes', function(greeting) {
			showGreeting(JSON.parse(greeting.body));
		});
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	if($("#name").val()==null || $("#name").val()==''){
		alert('You must provide a Stock ID to lookup');
		return;
	}
	stompClient.send("/app/stock", {}, JSON.stringify({ 'quote': $("#name").val() }));
}

function showGreeting(message) {
	var quote = message.quote;
	var msg = message.message;
	if ($('#' + quote + '').length > 0) {
		$($('#' + quote + '')).html(`<td>${msg}</td>`);
		blink($('#' + quote + ''));
	} else {
		var row = `<tr id="${quote}"><td>${msg}</td></tr>`;
		$("#report").append(row);
	}
}
function blink(obj) {
	$(obj).fadeTo(1000, 0.1).fadeTo(200, 1.0);
}
$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() { connect(); });
	$("#disconnect").click(function() { disconnect(); });
	$('#send').click(function() { sendName(); });
});
