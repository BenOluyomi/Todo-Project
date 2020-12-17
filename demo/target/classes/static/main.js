function getData(){
    fetch('http://localhost:8080/task/read')
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
            // for(let a of data){
            //     console.log(a)
            //     for (let b in a){
            //         console.log("b", a[b])
            //     }
                
            // }
            let table = document.querySelector("#tbl");
            let data2 = Object.keys(data[0]);
    
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
        for(let record of data){
            let row = table.insertRow();
            for(let prop in record){
                let cell = row.insertCell();
                let text = document.createTextNode(record[prop])
                cell.appendChild(text);
            }
        }
    }
    
    getData();