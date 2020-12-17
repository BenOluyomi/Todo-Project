document
.querySelector("form.viewRecord")
.addEventListener("submit", function (stop) {
  stop.preventDefault();
  let formElements = document.querySelector("form.viewRecord").elements;
  console.log(formElements)
  let name=formElements["commentName"].value;
  let subject=formElements["commentSubject"].value;
  let data = {
    "name":name,
    "subject":{
        "id":subject
    }


}
console.log("Data to post",data)
sendData(data)

  // postData(noteTitle,noteBody)
});


function sendData(data){
fetch("http://localhost:8080/task/create", {
    method: 'post',
    headers: {
      "Content-type": "application/json; charset=UTF-8"
    },
    body:JSON.stringify(data)
  })
  .then(function (data) {
    console.log('Request succeeded with JSON response', data);
  })
  .catch(function (error) {
    console.log('Request failed', error);
  });
}