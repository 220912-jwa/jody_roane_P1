async function loginRequest()
{
    // Get input.
    username = document.getElementById("input 1").value;
    password = document.getElementById("input 2").value;
    let inputCredentials = {username: username, password: password}
    console.log (inputCredentials)
    inputCredentialsJSON = JSON.stringify(inputCredentials);
    console.log (inputCredentialsJSON)
    // Send input in fetch request
    let requestURL = "http://localhost:8080/authenticate/login"
    let loginResponse = await fetch
    (
        requestURL,
        {
            method: "POST",
            header: {"Content-Type": "application/json"},
            body: inputCredentialsJSON
        }
    );
    // if fetch response = 200, navigate to landing.html
    if(loginResponse.status === 200)
    {
        let authenticatedEmployee = await loginResponse.json();
        sessionStorage.setItem("id", authenticatedEmployee.id);
        sessionStorage.setItem("firstName", authenticatedEmployee.firstName);
        sessionStorage.setItem("lastName", authenticatedEmployee.lastName);
        sessionStorage.setItem("role", authenticatedEmployee.role);
        sessionStorage.setItem("username",authenticatedEmployee.username);
        sessionStorage.setItem("password",authenticatedEmployee.password);
        document.location.assign("./landing.html");

    }
    // else if fetch response = 401, Send retry alert
    else if (loginResponse.status === 401)
    {
        alert("Authentication failed.");

    }
    //else console log something went wrong
    else
    {
        alert("You did something odd, please try to be even next time.");
        console.log("Response status was neither ok nor unauthorized, weird.");
    }

}


async function logoutRequest()
{
    let firstName = sessionStorage.getItem("firstName");
    let resquestBody = {logoutMessage: firstName +": Has logged out."}
    let requestBodyJSON = JSON.stringify(resquestBody)
    let requestURL = "http://localhost:8080/deauthenticate/logout"
    let logoutResponse = await fetch
    (
        requestURL,
        {
            method: "DELETE",
            header: {"Content-Type": "application/json"},
            body: requestBodyJSON
        }
    );
    if(logoutResponse.status === 200)
    {
        document.location.assign("./portal.html")
    }
    else
    {
        console.log("You can't log out it's forbidden")
    }
}

function loadLandingPage()
{
    let reimbursementForm = document.getElementById("div claim form");
    let claimsSeekingApprovalTable = document.getElementById("claims table div");
    let reimbursementApprovalForm = document.getElementById("approval form div");
    let pendingClaims = document.getElementById("pending claims table div");
    let finalGradeForm = document.getElementById("final grade form");
    let getClaimsSeekingApprovalButton = document.getElementById("get claims for approval button");
    reimbursementForm.style.display = "none";
    claimsSeekingApprovalTable.style.display = "none";
    reimbursementApprovalForm.style.display = "none";
    pendingClaims.style.display = "none";
    finalGradeForm.style.display = "none";
    getClaimsSeekingApprovalButton.style.display = "none";

    // Insert First Name, Last Name, role into DOM.
    let employeeId = sessionStorage.getItem("id")
    let firstName = sessionStorage.getItem("firstName");
    let lastName = sessionStorage.getItem("lastName");
    let role = sessionStorage.getItem("role");
    console.log(employeeId)
    console.log(role);
    document.getElementById("get pending claims button").onclick = function(){getPendingClaimsByEmployeeId(employeeId)};
    document.getElementById("h1 1").innerHTML = firstName;
    document.getElementById("h1 2").innerHTML = lastName;
    document.getElementById("p 1").innerHTML = role;
    // If session role = associate load associate links.

    if(role === "associate")
    {
        // Home link
        let homeLinkTag = document.createElement("a");
        let homelinkTextNode = document.createTextNode("Home");
        let linkDiv = document.getElementById("div links")
        homeLinkTag.appendChild(homelinkTextNode);
        homeLinkTag.id = "a 1";
        homeLinkTag.href = "http://localhost:63342/jody_roane_P1/ersWebsite/landing.html";
        linkDiv.append(homeLinkTag);
        // Log out link
        let logoutLinkTag = document.createElement("a");
        let logoutlinkTextNode = document.createTextNode("Logout");
        logoutLinkTag.appendChild(logoutlinkTextNode);
        logoutLinkTag.id = "a 2";
        logoutLinkTag.href = "#";
        logoutLinkTag.onclick = function(){logoutRequest()};
        linkDiv.append(logoutLinkTag);

    }
    // else load manager links.
    else
    {
        // Home link
        let homeLinkTag = document.createElement("a");
        let homelinkTextNode = document.createTextNode("Home");
        let linkDiv = document.getElementById("div links")
        homeLinkTag.appendChild(homelinkTextNode);
        homeLinkTag.id = "a 1";
        homeLinkTag.href = "http://localhost:63342/jody_roane_P1/ersWebsite/landing.html";
        linkDiv.append(homeLinkTag);
        // Log out link
        let logoutLinkTag = document.createElement("a");
        let logoutlinkTextNode = document.createTextNode("Logout");
        logoutLinkTag.appendChild(logoutlinkTextNode);
        logoutLinkTag.id = "a 2";
        logoutLinkTag.href = "#";
        logoutLinkTag.onclick = function(){logoutRequest()};
        linkDiv.append(logoutLinkTag);
        getClaimsSeekingApprovalButton.style.display = "block";
    }
}

function calculateReimbursement()
{
    // Store cost input as input
    let cost = document.getElementById("cost input").value;
    // store Event input id coverage
    let event = []
    event = document.getElementById("select 1");
    let option;
    option = event.selectedOptions[0]
    let coverage = option.getAttribute("name");
    console.log(coverage);
    // store reimbursement as (cost * (coverage/100))
    let reimbursement = (cost * (coverage/100)).toFixed(2);
    // Insert cost into p where id = " p2";
    document.getElementById("reimbursement").innerHTML = reimbursement;
    let calcButton = document.getElementById("button 1");
    let submitButton = document.getElementById("button 2");
    if (reimbursement > 0 )
    {
        calcButton.disabled = true;
        submitButton.disabled = false;

    }
    console.log(document.getElementById("input 2").value)

}

function checkSubmission()
{
    let description = document.getElementById("input 2").value;
    let location = document.getElementById("input 3").value;
    let startDate = document.getElementById("date 1").value;
    let endDate = document.getElementById("date 2").value;
    let checks = 0;
    let points = 0;
    let checklist =[description, location, startDate, endDate,];
    while(checks <= 3)
    {
        if(!(checklist[checks]) === false)
        {
            points = (points + 1)
        }
        checks = (checks + 1);
    }
    if(points === 4)
    {
        if(confirm("Are you sure?"))
        {
            createClaimRequest()
        };

    }
    else
    {
        console.log(points)
        console.log(checks)
        console.log(!(startDate))
        console.log(!(endDate))
        console.log(!(location))
        console.log(!(description))
        alert("All values excluding: \npassing grade, and final grade;\n must containvalid data before submitting.\nPlease try again.")

    }

}

async function createClaimRequest()
{
    //get input
    let firstName = sessionStorage.getItem("firstName");
    let lastName = sessionStorage.getItem("lastName");
    let role = sessionStorage.getItem("role");
    let employeeId = sessionStorage.getItem("id");
    let balance = 1000.00;
    let event = document.getElementById("select 1").selectedOptions[0].value;
    let coverage = document.getElementById("select 1").selectedOptions[0].getAttribute("name")
    let description = document.getElementById("input 2").value;
    let location = document.getElementById("input 3").value;
    let cost = document.getElementById("cost input").value;
    let reimbursement = document.getElementById("reimbursement").innerHTML;
    let startDate = document.getElementById("date 1").value;
    let endDate = document.getElementById("date 2").value;
    let passingGrade = document.getElementById("select 2").selectedOptions[0].value;
    let finalGrade = document.getElementById("select 3").selectedOptions[0].value;
    let claimStatus = "Approval Pending"
    let awardStatus = false
    let priority = "2"
    if(passingGrade = "Unknown")
    {
        claimStatus = "Urgent: Needs Passing Grade"
        priority = "1"
    }

    let newClaim = {
        firstName: firstName,
        lastname: lastName,
        role: role,
        employeeId: employeeId,
        balance: balance,
        event: event,
        coverage: coverage,
        description: description,
        location: location,
        cost: cost,
        reimbursement: reimbursement,
        startDate: startDate,
        endDate: endDate,
        passingGrade: passingGrade,
        finalGrade: finalGrade,
        claimStatus: claimStatus,
        awardStatus: awardStatus,
        priority: priority
    }
   let newClaimJSON = JSON.stringify(newClaim)
    console.log(newClaimJSON)
    let requestURL = "http://localhost:8080/claim/create"
    let response = await fetch
    (
        requestURL,
        {
            method: "POST",
            header: {"Content-Type": "application/json"},
            body: newClaimJSON
        }
    );
    if(response.status === 200)
    {
        // This hides or shows the table depending on the state of the table.
        let x = document.getElementById("div claim form")
        if(x.style.display === "none")
        {
            x.style.display = "block";

        }
        else 
        {
            x.style.display = "none";
        }
        alert("DataBase Update!")
    }
    else
    {
        console.log("Something isn't right...")
    }
}

async function getAllClaimsForApproval()
{
    var table = document.getElementById("claims seeking approval info");
    var rows = table.childElementCount;
    if(rows > 0)
    {
        let i = (rows-1)
        while(i >= 0)
        {
            table.deleteRow(i);
            i--
        }
    }

    // Send input in fetch request
    let requestURL = "http://localhost:8080/claim/get/all/approval"
    let getAllClaimsResponse = await fetch
    (
        requestURL,
        {
            method: "GET",
            header: {"Content-Type": "application/json"},
        }
    );
    let claims = await getAllClaimsResponse.json();
    // This hides or shows the table depedning on the state of the table.
    var tableBody = document.getElementById("claims table");
    let x = document.getElementById("claims table div")
    if(x.style.display === "none")
    {
        x.style.display = "block";

    }
    else 
    {
    }
    
    // if(tableBody.children[1]!= null)
    // {
    //     tableBody.deleteRow(0);
    // }
    claims.forEach(element => createClaimsTable([[element.id,element.firstName,element.lastname,element.claimStatus]])) 
}

async function updateClaimById(id,claim)
{
    id = claim.id;
    let firstName = claim.firstName;
    let lastName = claim.lastname;
    let role = claim.role;
    let employeeId = claim.employeeId;
    let balance = claim.balance;
    let event = claim.event;
    let coverage = claim.coverage;
    let description = claim.description;
    let location = claim.location;
    let cost = claim.cost;
    let sReimbursement = document.getElementById("claim info 2").children[0].children[2].innerHTML;
    let reimbursement = parseFloat(sReimbursement);
    let startDate = claim.startDate;
    let endDate = claim.endDate;
    let passingGrade = claim.passingGrade;
    let finalGrade = claim.finalGrade;
    let claimStatus = document.getElementById("select adjustment options").selectedOptions[0].value;
    let awardStatus = document.getElementById("award status").checked;
    let priority =  document.getElementById("select adjustment options").selectedOptions[0].getAttribute("id");
    let updatedClaim = 
    {
        id: id,
        firstName: firstName,
        lastname: lastName,
        role: role,
        employeeId: employeeId,
        balance: balance,
        event: event,
        coverage: coverage,
        description: description,
        location: location,
        cost: cost,
        reimbursement: reimbursement,
        startDate: startDate,
        endDate: endDate,
        passingGrade: passingGrade,
        finalGrade: finalGrade,
        claimStatus: claimStatus,
        awardStatus: awardStatus,
        priority: priority
    }
    let updatedClaimJSON = JSON.stringify(updatedClaim)
    console.log(id);
    console.log(updatedClaim);
    let requestURL = "http://localhost:8080/claim/update";
    let response = await fetch
    (
        requestURL,
        {
            method: "PATCH",
            header: {"Content-Type": "application/json"},
            body: updatedClaimJSON
        }
    );
    let update = await response
    if(update.status === 200)
    {
        // This hides or shows the table depending on the state of the table
        let x = document.getElementById("approval form div")
        if(x.style.display === "none")
        {
            x.style.display = "block";
    
        }
        else 
        {
            x.style.display = "none";
        }
        alert("Claim Updated")
    }
    else
    {
        alert("Database was not updated, please try again.")

    }


}
async function getClaimById(id)
{
    
    let requestURL = "http://localhost:8080/claim/get/" + id;
    let getClaimByIdResponse = await fetch
    (
        requestURL,
        {
            method: "GET",
            header: {"Content-Type": "application/json"},
        }
    );
    let claim = await getClaimByIdResponse.json();
    console.log(claim);

    // This hides or shows the table depending on the state of the table.
    let x = document.getElementById("approval form div")
    let z = document.getElementById("claims table div")
    if(x.style.display === "none")
    {
        x.style.display = "block";
        z.style.display = "none";

    }
    else 
    {
        x.style.display = "none";
    }

    let updateButton = document.getElementById("update button")
    updateButton.onclick = function(){updateClaimById(claim.id,claim)}
    createApprovalForm1([[claim.firstName,claim.lastname,claim.event,claim.description,claim.passingGrade]])
    createApprovalForm2([[claim.cost.toFixed(2),claim.balance.toFixed(2),claim.reimbursement.toFixed(2),claim.endDate,claim.finalGrade]])

}


function createClaimsTable(tableData)
 {
    var table = document.getElementById("claims table");
    var tableBody = document.getElementById("claims seeking approval info");
    tableData.forEach(function(rowData) {
      var row = document.createElement('tr');
  
      rowData.forEach(function(cellData) {
        var cell = document.createElement('td');
        cell.appendChild(document.createTextNode(cellData));
        row.appendChild(cell);
      });
      let selectButton = document.createElement("button");
      let id = row.children[0].innerHTML;
      selectButton.onclick = function(){getClaimById(id)}
      selectButton.innerHTML = "Select This Claim";
      row.appendChild(selectButton)
      tableBody.appendChild(row);
    });
    table.appendChild(tableBody);
  }

  function createApprovalForm1 (tableData)
 {
    var tableBody = document.getElementById("claim info 1");
    if(tableBody.children[0]!= null)
    {
        tableBody.deleteRow(0);
    }
  
    tableData.forEach(function(rowData) {
      var row = document.createElement('tr');
  
      rowData.forEach(function(cellData) {
        var cell = document.createElement('td');
        cell.appendChild(document.createTextNode(cellData));
        row.appendChild(cell);
      });
  
      tableBody.appendChild(row);
    });
  
  }

  function createApprovalForm2 (tableData)
  {
     var tableBody = document.getElementById("claim info 2");
     if(tableBody.children[0]!= null)
     {
         tableBody.deleteRow(0);
     }
   
     tableData.forEach(function(rowData) {
       var row = document.createElement('tr');
   
       rowData.forEach(function(cellData) {
         var cell = document.createElement('td');
         cell.appendChild(document.createTextNode(cellData));
         row.appendChild(cell);
       });
   
       tableBody.appendChild(row);
     });

   }
   async function getPendingClaimsByEmployeeId(employeeId)
   {
    
    var table = document.getElementById("pending claims tbody");
    var rows = table.childElementCount;
    if(rows > 0)
    {
        let i = (rows-1)
        while(i >= 0)
        {
            table.deleteRow(i);
            i--
        }
    }
    
    let requestURL = "http://localhost:8080/claim/get/all/employee/" + employeeId;
    let response = await fetch
    (
        requestURL,
        {
            method: "GET",
            header: {"Content-Type": "application/json"},
        }
    );
    
    let employeeClaims = await response.json();
    console.log(employeeClaims);

    // This hides or shows the table depending on the state of the table.
    let x = document.getElementById("pending claims table div")
    if(x.style.display === "none")
    {
        x.style.display = "block";

    }
    else
    {

    }
    var tableBody = document.getElementById('pending claims tbody');
    if(tableBody.children[0]!= null)
    {
        tableBody.deleteRow(0);
    }
    employeeClaims.forEach(element => createPendingClaimsTable([[element.id,element.firstName,element.lastname,element.claimStatus]])) 


   }
   function createPendingClaimsTable(tableData)
 {
    var table = document.getElementById("pending claims table");
    var tableBody = document.getElementById('pending claims tbody');
  
    tableData.forEach(function(rowData) {
      var row = document.createElement('tr');
  
      rowData.forEach(function(cellData) {
        var cell = document.createElement('td');
        cell.appendChild(document.createTextNode(cellData));
        row.appendChild(cell);
      });
      let selectButton = document.createElement("button");
      let id = row.children[0].innerHTML;
      selectButton.onclick = function(){getPendingClaimById(id)}
      selectButton.innerHTML = "Select This Pending Claim";
      row.appendChild(selectButton)
      tableBody.appendChild(row);
    });
    table.appendChild(tableBody);
  }

  async function getPendingClaimById(id)
  {
    let pRequestURL = "http://localhost:8080/claim/get/" + id;
    let pResponse = await fetch
    (
        pRequestURL,
        {
            method: "GET",
            header: {"Content-Type": "application/json"},
        }
    );
        // This hides or shows the table depending on the state of the able
        let x = document.getElementById("final grade form");
        let y = document.getElementById("pending claims table div");
        if(x.style.display === "none")
        {
            x.style.display = "block";
            updateButton = document.getElementById("update button");
            console.log(id)
            claimId = id
            console.log(claimId)
            y.style.display = "none";
    
        }
        else 
        {
            var tableBody = document.getElementById("pending claim info 1");
            if(tableBody.children[0]!= null)
            {
                tableBody.deleteRow(0);
            }
            var tableBody = document.getElementById("pending claim info 2");
            if(tableBody.children[0]!= null)
            {
                tableBody.deleteRow(0);
            }
            x.style.display = "none";
        }
    let pendingClaim = await pResponse.json();
    createFinalGradeForm1([[pendingClaim.firstName,pendingClaim.lastname,pendingClaim.event,pendingClaim.description]])
    createFinalGradeForm2([[pendingClaim.cost.toFixed(2),pendingClaim.reimbursement.toFixed(2),pendingClaim.endDate,pendingClaim.passingGrade]])
    if(pendingClaim.passingGrade != "Unknown")
    {
    let gradeChanger = document.getElementById("unknown only 2");
    let gradeChangerLable = document.getElementById("unknown only 1");
    gradeChanger.style.display = "none"
    gradeChangerLable.style.display = "none";
    }
  }

  function createFinalGradeForm1 (tableData)
  {
     var tableBody = document.getElementById("pending claim info 1");
     if(tableBody.children[0]!= null)
     {
         tableBody.deleteRow(0);
     }
   
     tableData.forEach(function(rowData) {
       var row = document.createElement('tr');
   
       rowData.forEach(function(cellData) {
         var cell = document.createElement('td');
         cell.appendChild(document.createTextNode(cellData));
         row.appendChild(cell);
       });
   
       tableBody.appendChild(row);
     });
   }

   function createFinalGradeForm2 (tableData)
   {
      var tableBody = document.getElementById("pending claim info 2");
      if(tableBody.children[0]!= null)
      {
          tableBody.deleteRow(0);
      }
    
      tableData.forEach(function(rowData) {
        var row = document.createElement('tr');
    
        rowData.forEach(function(cellData) {
          var cell = document.createElement('td');
          cell.appendChild(document.createTextNode(cellData));
          row.appendChild(cell);
        });
    
        tableBody.appendChild(row);
      });
    }
    function getReimbursementForm()
    {    
        //This hides or shows the div depending on the state of the div
        let x = document.getElementById("div claim form")
        if(x.style.display === "none")
        {
            x.style.display = "block";

        }
    }
    async function updatePendingClaimById()
    {
        let thisClaimId = claimId 

    console.log(thisClaimId);
    let pRequestURL = "http://localhost:8080/claim/get/" + thisClaimId;
    let pResponse = await fetch
    (
        pRequestURL,
        {
            method: "GET",
            header: {"Content-Type": "application/json"},
        }
    );
    let pendingClaim = await pResponse.json();

    id = pendingClaim.id;
    let firstName = pendingClaim.firstName;
    let lastName = pendingClaim.lastname;
    let role = pendingClaim.role;
    let employeeId = pendingClaim.employeeId;
    let balance = pendingClaim.balance;
    let event = pendingClaim.event;
    let coverage = pendingClaim.coverage;
    let description = pendingClaim.description;
    let location = pendingClaim.location;
    let cost = pendingClaim.cost;
    let reimbursement = document.getElementById("pending claim info 2").children[0].children[2].innerHTML;
    let startDate = pendingClaim.startDate;
    let endDate = pendingClaim.endDate;
    let passingGrade = pendingClaim.passingGrade;
    let finalGrade = document.getElementById("final grade selector").selectedOptions[0].value;;
    let claimStatus = pendingClaim.claimStatus
    let awardStatus = pendingClaim.awardStatus
    let priority = pendingClaim.priority;
    if(awardStatus === false)
    {
        claimStatus = "Approval Pending"
        priority = "2"
    }
    if(passingGrade === "Unknown")
    {
        passingGrade = document.getElementById("passing grade selector").selectedOptions[0].value;
    }
    let updatedPClaim = 
    {
        id: id,
        firstName: firstName,
        lastname: lastName,
        role: role,
        employeeId: employeeId,
        balance: balance,
        event: event,
        coverage: coverage,
        description: description,
        location: location,
        cost: cost,
        reimbursement: reimbursement,
        startDate: startDate,
        endDate: endDate,
        passingGrade: passingGrade,
        finalGrade: finalGrade,
        claimStatus: claimStatus,
        awardStatus: awardStatus,
        priority: priority
    }
    let updatedPClaimJSON = JSON.stringify(updatedPClaim)
    console.log(id);
    console.log(updatedPClaim);
    let requestURL = "http://localhost:8080/claim/update";
    let response = await fetch
    (
        requestURL,
        {
            method: "PATCH",
            header: {"Content-Type": "application/json"},
            body: updatedPClaimJSON
        }
    );
    let update = await response
    if(update.status === 200)
    {
        // This hides or shows the table depending on the state of the table
        let x = document.getElementById("final grade form")
        if(x.style.display === "none")
        {
            x.style.diplay = "block";
    
        }
        else 
        {
            var tableBody = document.getElementById("pending claim info 1");
            if(tableBody.children[0]!= null)
            {
                tableBody.deleteRow(0);
            }
            var tableBody = document.getElementById("pending claim info 2");
            if(tableBody.children[0]!= null)
            {
                tableBody.deleteRow(0);
            }
            x.style.display = "none";
            
        }
        alert("Claim Updated")
    }
    else
    {
        alert("Database was not updated, please try again.")

    }

}

function adjustReimbursement()
{
    let adjustment = document.getElementById("adjustment").value;
    let reimbursement =  document.getElementById("claim info 2").children[0].children[2];
    reimbursement.innerHTML = adjustment;
}