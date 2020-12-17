fetch('http://localhost:8080/subject/read')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }
      response.json().then(function(commentData) {
       

        let table = document.querySelector("table");
        let data = Object.keys(commentData[0]);
        
        createTableHead(table,data);
        createTableBody(table,commentData);
        
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createTableHead(table,data){
      let tableHead= table.createTHead();
      let row = tableHead.insertRow();
      for(let keys of data){
          let th = document.createElement("th");
          let text = document.createTextNode(keys);
          th.appendChild(text);
          row.appendChild(th)
        
      }
      let th2 = document.createElement("th")
      let text2 = document.createTextNode("View");
      th2.appendChild(text2);
      row.appendChild(th2);
      let th3 = document.createElement("th")
      let text3 = document.createTextNode("Delete");
      th3.appendChild(text3);
      row.appendChild(th3);
  }

  function createTableBody(table,commentData){
      for(let commentRecord of commentData){
          let row = table.insertRow();
          for(let values in commentRecord){
              let cell = row.insertCell();
              let text = document.createTextNode(JSON.stringify(commentRecord[values]));
              cell.appendChild(text);
            }
            let newCell = row.insertCell();
            let myViewButton = document.createElement("a");
            let myButtonValue = document.createTextNode("View/Edit a Task")
            myViewButton.className ="btn btn-success";
            myViewButton.href="viewbyid.html?id="+commentRecord.id
            myViewButton.appendChild(myButtonValue);
            newCell.appendChild(myViewButton)
          let newCellDelete = row.insertCell();
          let myDelButton = document.createElement("button");
          let myButtonValue1 = document.createTextNode("Delete a Task")
          myDelButton.className ="btn btn-danger";
         myDelButton.appendChild(myButtonValue1);
          newCellDelete.appendChild(myDelButton) 
          myDelButton.onclick = function(){

                return fetch("http://localhost:8080/subject/delete" + '/' + commentRecord.id, {
                  method: 'delete'
                }).then(response =>
                  response.json().then(json => {
                    return json;
                  })

                  
                ).then(window.location.reload()
                );
                
                
          };
          
      }
  }