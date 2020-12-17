 fetch("http://localhost:8080/task/read", {
    method: 'put',
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

  function postData( title,body){
fetch('http://jsonplaceholder.typicode.com/posts', {
    method: 'put', //post, put,delete
    headers: {
      "Content-type": "application/json; charset=UTF-8"
    },
    body:{
        "title":title,
        "body": body
    }
  })
  .then(function (data) {
    console.log('Request succeeded with JSON response', data);


  })
  .catch(function (error) {
    console.log('Request failed', error);
  })

}