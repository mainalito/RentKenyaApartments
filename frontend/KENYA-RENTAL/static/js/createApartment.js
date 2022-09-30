const toast = document.querySelector(".toast"),
  closeIcon = document.querySelector(".close"),
  progress = document.querySelector(".progress");
const btnHouse = document.getElementById("btnHouse");
const mini_extra = document.querySelector(".mini-extra");

const apartmentName = document.getElementById("name"),
  form = document.getElementById("form"),
  latitudeCoordinates = document.getElementById("lat"),
  longitudeCoordinates = document.getElementById("long"),
  phone = document.getElementById("phone"),
  coordinates = document.getElementById("coordinates"),
  email = document.getElementById("email"),
  yesFeatured = document.getElementById("true"),
  noFeatured = document.getElementById("false"),
  town = document.getElementById("town");
//TODO RADIO BUTTON, IMAGE AND CHECKBOX
let chosenValue;

const housePrice = document.getElementById("price");
const houseValues = document.getElementById("houses");
const yesAvailable = document.getElementById("yes")
const noAvailable = document.getElementById("no");
const apartmentImage = document.getElementById("apartmentImage");
let available;
let arrayHouse = []
let imagesArray = []
const API_AMENITY_URL = "http://localhost:8090/api/amenities/";

let id = 0;
//fetch latitude and longitude coordinate
fetchCoordinates();

//GET AMENITIES FUNCTION
fetchAmenities()

//fetch geolocation coordinates
function fetchCoordinates() {
  coordinates.addEventListener("click", (e) => {
    e.preventDefault();
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        const { latitude, longitude } = position.coords;

        //REASSIGNED LAT AND LONG INPUT VALUE TEXTS
        longitudeCoordinates.value = longitude.toFixed(2);
        latitudeCoordinates.value = latitude.toFixed(2);

      });
    }
  });
}



//FETCH INPUT VALUES FROM FORM
form.addEventListener("submit", (e) => {
  e.preventDefault();
  let lat = latitudeCoordinates.value.trim();
  let long = longitudeCoordinates.value.trim();
  let name = apartmentName.value.trim();
  let apartmenteEmail = email.value.trim()
  let apartmentPhone = phone.value.trim()
  let checkbox = getChecked()
  let locationTown = town.value.trim();

  if (yesFeatured.checked == true) {
    chosenValue = yesFeatured.value;
    chosenValue = true;

  }
  else if (noFeatured.checked == true) {
    chosenValue = noFeatured.value;
    chosenValue = false;
  }
  //pass apartment images 
  var myHeaders = new Headers();



  var formdata = new FormData();
  for (let i = 0; i < apartmentImage.files.length; i++) {

    files = apartmentImage.files[i];
  }
  formdata.append("imagefile", apartmentImage.files[0])
  let apartmentObject = {
    name: name,
    latitudeCoordinates: lat,
    longitudeCoordinates: long,
    houseTypes: arrayHouse,
    amenities: checkbox,
    phoneNumber: apartmentPhone,
    email: apartmenteEmail,
    featured: chosenValue,
    locationTown: locationTown,
    houseTypes: arrayHouse

  }

  const jsonApartment = JSON.stringify(apartmentObject);
  const blob = new Blob([jsonApartment], {
    type: 'application/json'
  });
  formdata.append("apartment", blob)

  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: formdata,
    redirect: 'follow',

  };
  fetch("http://localhost:8090/api/apartment/", requestOptions)
    .then(response => response.json())
    .then(result => {
      const { message, status } = result;
      if (message === "Successfully added data" && status === 201) {
        
        successMessage();
        form.reset();

      }
      else {
        // errorMessage();
      }

    })
    .catch(error => console.log('error', error));

});
function successMessage() {
  toast.classList.add("active")
  progress.classList.add("active")

  setTimeout(() => {
    toast.classList.remove("active")
  }, 5000)
  setTimeout(() => {
    progress.classList.remove("active")
  }, 5300)
  closeIcon.addEventListener("click", () => {
    toast.classList.remove("active");
    setTimeout(() => {
      progress.classList.remove("active")
    })
  }, 300)
}
//add house types
//!BEDSITTER, ONEBEDROOM, THREE BEDROOM, TWO BEDROOM
btnHouse.addEventListener("click", (e) => {
  e.preventDefault();

  selectedHouseTypes();
});

//user chooses which house type  and its details
function selectedHouseTypes() {
  id++;
  let price = housePrice.value
  let house = houseValues.value

  if (yesAvailable.checked == true) {
    available = yesAvailable.value;
    available = true;

  }
  else if (noAvailable.checked == true) {
    available = noAvailable.value;
    available = false;
  }
  let houseObject = {
    "type": house,
    "Image": [""],
    "Available": available,
    "price": price,
    houseid: id

  }
  arrayHouse.push(houseObject);


  //populate the selected ones 
  const HouseElem = document.createElement("div");
  arrayHouse.map(house => {
    HouseElem.classList.add("optionsHouse");
    HouseElem.innerHTML = `
              <p>${house.type}</p>
              <p>${house.Available}</p>
              <p>${house.price}</p>
              <button class="removeBtn" onclick="removeElement(event, ${house.id})">
                  <box-icon
                    color="#e21430"
                    name="trash-alt"
                    type="solid"
                  ></box-icon>
              </button> 
    `;

  })
  mini_extra.append(HouseElem);

}

//remove element from container
function removeElement(event, pId) {

  event.preventDefault();

  event.target.parentElement.parentElement.remove()
  //excluding the found object from array
  arrayHouse = arrayHouse.filter((house) => house.id !== pId)
}


//populate amenities from database
async function fetchAmenities() {
  const resp = await fetch(API_AMENITY_URL);
  const rspData = await resp.json();

  const { message, status } = rspData;

  if (status === 200 && message === "Successfully retrieved data!") {
    rspData.data.forEach((amenity) => {
      const { name } = amenity;

      const resultData = document.createElement("div");
      resultData.classList.add("checkedAmenity");
      resultData.innerHTML = `  
        <label for="">${name}</label>
        <input type="checkbox" value="${name}"name="" id="amenity" />
  `;
      document.querySelector(".checked").appendChild(resultData);

    });
  }
}


//populate amenities to the form
function getChecked() {
  let amenitiesArray = []
  const checkbox = document.querySelectorAll('input[type="checkbox"]:checked');
  checkbox.forEach(ch => {
    let amenitiesObject = {
      name: ch.value
    }
    amenitiesArray.push(amenitiesObject)
  });

  return amenitiesArray;
}

