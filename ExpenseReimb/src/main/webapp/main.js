/**
 * 
 */
window.onload = function(){
	getUserSession();	
}

getReimbList();
getUserReimbList();


//formtReimbList();


/*
 * getUserSession function gets the information of a logged in user.
 */
function getUserSession() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		
		if(xhttp.readyState == 4 && xhttp.status==200){
			let user = JSON.parse(xhttp.responseText);
//			let lReimbs = JSON.parse(xhttp.responseText);
//			console.log(lReimbs);
//			console.log(user);
			
			if(user != null) {
				document.getElementById("welcomeHeader").innerText=`Welcome ${user.fname} !`;
			}
			
			
//			document.getElementById("lReimbs").innerText=`${lReimbs.length} !`;
		}
		
	}
	
	xhttp.open("GET", "http://localhost:8080/ExpenseReimb/getusersession.json");

	xhttp.send();
	return user;
}

/*
 * 
 */

function getReimbList() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		
		if(xhttp.readyState == 4 && xhttp.status==200){
			
			let lReimbs = JSON.parse(xhttp.responseText);
			console.log("1st function");
			console.log(lReimbs);
			
			let cNames = ["ID", "Appicant", "Amount", "Description",  "Type", "Manager", "Status", "Sub. Date","Res. Date","Action", "---"];
			var result = "<table id='reimbTable' class=\"table table-success table-striped\">";
			result += "<thead>";
			result += "<tr>";
			result += "<th>" + cNames[0] + "</th>" 
			  + "<th>" + cNames[1] + "</th>"
			  + "<th>" + cNames[2] + "</th>"
			  + "<th>" + cNames[3] + "</th>"
			  + "<th>" + cNames[4] + "</th>"
			  + "<th>" + cNames[5] + "</th>"
			  + "<th>" + cNames[6] + "</th>"
			  + "<th>" + cNames[7] + "</th>"
			  + "<th>" + cNames[8] + "</th>"
			  + "<th colspan=\"2\">" + cNames[9] + "</th>";			 
			result += "</tr>";
			result += "</thead>";
			
			result += "<tbody>";			
			
			for (var j = 0; j < lReimbs.length; j++) {
//			  result += "<tr onclick= \"window.location='/ExpenseReimb/update.html';\">";
			  result += "<tr>";
			  var status = lReimbs[j].status;			  
			  result += "<td id=\"reimbId\">" + lReimbs[j].reimbId + "</td>" 
					  + "<td>" + lReimbs[j].applicant + "</td>"
					  + "<td>" + lReimbs[j].amount + "</td>"
					  + "<td>" + lReimbs[j].description + "</td>"
					  + "<td>" + lReimbs[j].type + "</td>"
					  + "<td>" + lReimbs[j].managerName + "</td>"
					  + "<td>" + lReimbs[j].status + "</td>"
					  + "<td>" + (lReimbs[j].submittedDate).substring(0,16)+ "</td>";
					  if((lReimbs[j].resolvedDate) != null) {
						  result += "<td>" + (lReimbs[j].resolvedDate).substring(0,16) + "</td>";
					  } else {
						  result += "<td>" + cNames[10] + "</td>";
					  }
					  
					  if(status == "Pending") {
						result += "<td>"+ "<input onclick='approve(event)'  class=\"btn btn-success\" type='button' value='Approve'>" + "</td>"
						  +"<td>"+ "<input onclick='deny(event)'  class=\"btn btn-danger\" type='button' value='Deny'>"+ "</td>";
					  } else {						  
						result += "<td colspan='2'>"+ "<input disabled class=\"btn btn-primary\" type='button' value='Decision Made'>" + "</td>";
					  }
					 
					  	  		
			  result += "</tr>";		
			}
			result += "</tbody>"; 
			
			result += "</table>";
			
//			let user = getUserSession();
//			console.log(user);
//			if(user["roleId"] == 2) {
			if(document.getElementById("list")!=null)
			{
				document.getElementById("list").innerHTML = result;
			}
//			}else {
//				document.getElementById("emplist").innerHTML = result;
//			}	

		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ExpenseReimb/getreimblist.json");

	xhttp.send();
}

function getUserReimbList() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		
		if(xhttp.readyState == 4 && xhttp.status==200){
			
			let userReimbs = JSON.parse(xhttp.responseText);
			console.log("2nd function");
			console.log(userReimbs);
			
			let cNames = ["Appicant", "Amount", "Description",  "Type", "Manager Name", "Status", "Submitted Date","Resolved Date", "---"];
			var result = "<table id='reimbTable' class=\"table table-success table-striped\">";
			result += "<thead>";
			result += "<tr>";
			result += "<th>" + cNames[0] + "</th>" 
			  + "<th>" + cNames[1] + "</th>"
			  + "<th>" + cNames[2] + "</th>"
			  + "<th>" + cNames[3] + "</th>"
			  + "<th>" + cNames[4] + "</th>"
			  + "<th>" + cNames[5] + "</th>"
			  + "<th>" + cNames[6] + "</th>"
			  + "<th>" + cNames[7] + "</th>";			 
			result += "</tr>";
			result += "</thead>";
			
			result += "<tbody>";
			
			
			for (var j = 1; j < userReimbs.length; j++) {
			  result += "<tr>";
			  result += "<td>" + userReimbs[j].applicant + "</td>"
					  + "<td>" + userReimbs[j].amount + "</td>"
					  + "<td>" + userReimbs[j].description + "</td>"
					  + "<td>" + userReimbs[j].type + "</td>"
					  + "<td>" + userReimbs[j].managerName + "</td>"
					  + "<td>" + userReimbs[j].status + "</td>"
					  + "<td>" + (userReimbs[j].submittedDate).substring(0,16)+ "</td>";
					  if((userReimbs[j].resolvedDate) != null) {
						  result += "<td>" + (userReimbs[j].resolvedDate).substring(0,16) + "</td>";
					  } else {
						  result += "<td>" + cNames[8] + "</td>";
					  }
					 			 
			  result += "</tr>";			  
			}
			result += "</tbody>";
			
			result += "</table>";
			
//			let user = getUserSession();
//			console.log(user);
//			if(user["roleId"] == 2) {
//				document.getElementById("list").innerHTML = result;
//			}else {
//			if(document.getElementById("emplist")!=null)
//			{
				document.getElementById("emplist").innerHTML = result;
//			}
//			}	

		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ExpenseReimb/getuserreimblist.json");

	xhttp.send();
}


function approve(e) {
    let interimValue = e.target;
    let inputValue = interimValue.parentElement.parentElement.getElementsByTagName("td")[0].innerHTML;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
//        	xhttp.open("POST", "/ExpenseReimb/update.dashboard", true);
//            xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            xhttp.send("value=" + e.target.value);
        }
    }
    xhttp.open("POST", "/ExpenseReimb/update.dashboard", true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.send("value=" + inputValue);
    location.reload();
    return false;
}

function deny(e) {
    let interimValue = e.target;
    let inputValue = interimValue.parentElement.parentElement.getElementsByTagName("td")[0].innerHTML;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
//        	xhttp.open("POST", "/ExpenseReimb/update.dashboard", true);
//            xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//            xhttp.send("value=" + e.target.value);
        }
    }
    xhttp.open("POST", "/ExpenseReimb/deny.dashboard", true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.send("value=" + inputValue);
    location.reload();
    return false;
}

function filterTable() {
	// Declare variables
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("key");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("reimbTable");
	  tr = table.getElementsByTagName("tr");

	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[6];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }
	  }
}

function approveDeny() {
	// Declare variables
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("key");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("reimbTable");
	  tr = table.getElementsByTagName("tr");

	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      document.getElementById('reimbId').innerHTML = txtValue;
	      console.log(txtValue);
//	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
//	        tr[i].style.display = "";
//	      } else {
//	        tr[i].style.display = "none";
//	      }
	    }
	  }
}




















