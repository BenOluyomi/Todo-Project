const params = new URLSearchParams(window.location.search);

for(let param of params ){
    console.log("here i am",param)
    let id = param[1];
    console.log(id);
    getData(id)
}

function getData(id){
    fetch('http://localhost:8080/task/read/'+id)
      .then(
        function(response) {
          if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
    
          // Examine the text in the response
          response.json().then(function(data) {
            console.log(data);

            let table = document.querySelector("#tbl");
            let data2 = Object.keys(data);
    
            createTableHead(table,data2)
            createTableBody(table,data)
    
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
    }
    
    function createTableHead(table,data){
        let tableHead = table.createTHead();
        let row = tableHead.insertRow();
        for(let val of data){
            let th = document.createElement("th");
            let text = document.createTextNode(val);
            th.appendChild(text);
            row.appendChild(th);
        }
    }
    
    function createTableBody (table,data){
        let row = table.insertRow();
        for(let prop in data){
            let cell = row.insertCell();
            let text = document.createTextNode(data[prop])
            cell.appendChild(text);
        }
    }