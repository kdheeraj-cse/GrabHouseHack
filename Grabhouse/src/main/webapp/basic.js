//Generic AJAX call 
function makeAjaxCalls(url,dcallback,param){
	$.ajax({
 	url: url,
	type: "get",
	xhrFields:{ withCredentials: true},
	data:param
	}).done(dcallback);
}

//Poll request to see if any customer has requested to Owner regarding viewing House
function seeCustomerRequestMade(){
	var url='http://192.168.1.114:7070/Grabhouse/rest/api/pollRequest';
	makeAjaxCalls(url,requestDoneHandler);
}

//Handler function for request made by customer
function requestDoneHandler(data){
	alert(data);
}

//Request from customer to Owner 
function requestOwner(){
	var url='http://192.168.1.114:7070/Grabhouse/rest/api/putRequest';
	//Considering we get customer ID from the authentication services, Passing hardcoded customer id
	var params={
			"userId":'Customer_xb23na'
	};
	makeAjaxCalls(url,requestOwnerHandler,params);
}

//Handler function for the request done 
function requestOwnerHandler(data){
	alert(data);
}

//Update the expired keys for lock
function updateKey(){
	var url='http://192.168.1.114:7070/Grabhouse/rest/api/updateAllKeys';
	makeAjaxCalls(url,updateHandler);
}

//Update handler
function updateHandler(data){
	alert(data);
}

//Owner Generates lock key for Customer
function generateKey(){
	var url='http://192.168.1.114:7070/Grabhouse/rest/api/getLockKey';
	makeAjaxCalls(url,generateKeyHandler);
}

//Lock key generation handler
function generateKeyHandler(data){
	alert('Here is your key '+ data);
}

//Validate key
function validateKeyToUnlock(keytocheck){
	var url='http://192.168.1.114:7070/Grabhouse/rest/api/unlockHouse';
	var param={
			"key":keytocheck
	};
	makeAjaxCalls(url,validateKeyHandler,param);
}

//Validate key handler
function validateKeyHandler(data){
	alert(data);
}

//Once customer goes, Owner can lock the house
function lockHouse(){
	var url='http://192.168.1.114:7070/Grabhouse/rest/api/lockHouse';
	makeAjaxCalls(url,lockHandler);
}

//lock handler 
function lockHandler(data){
	alert(data);
}

$('.unlock').on('click',function(){
	var key=prompt("Please enter key");
	if(key){
		validateKeyToUnlock(key);
	}else{
		alert('key missing');
	}
});

$('.lock').on('click',function(){
	lockHouse();
});

$('.reqcheck').on('click', function(){
	seeCustomerRequestMade();			
});

$('.poke').on('click',function(){
	requestOwner();
});

$('.genkey').on('click',function(){
	generateKey();
});

$('.updkey').on('click',function(){
	updateKey();
});