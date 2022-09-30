const API_APARTMENT = 'http://localhost:8090/api/apartment/';
// Make a request for a user with a given ID

fetch(API_APARTMENT)
    .then(response => response.json())
    .then(data => console.log(data));


//fetch coordinates for apartment location
