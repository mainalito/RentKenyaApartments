const form = document.getElementById("form");
const input = document.getElementById("amenity");
const submitBtn = document.getElementById("submitBtn");
const API_AMENITY_URL = "http://localhost:8090/api/amenities/";
const resultsContainer = document.querySelector(".results");

//initial get amenities
getResults();

//CREATE
form.addEventListener("submit", (e) => {
  e.preventDefault();
  let inputValue = input.value.trim(" ");


  //save to db using api

  const myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  const raw = JSON.stringify({
    name: inputValue,
  });

  const requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
   
  };

  fetch(API_AMENITY_URL, requestOptions)
    .then((response) => response.json())
    .then((result) => {
       window.location.reload(true)
    })
    .catch((error) => console.log("error", error));

  input.value = "";
});
//fetch from DB
async function getResults() {
  const resp = await fetch(API_AMENITY_URL);
  const rspData = await resp.json();

  const { message, status } = rspData;

  if (status === 200 && message === "Successfully retrieved data!") {
    rspData.data.forEach((amenity) => {
      const { name, id } = amenity;

      const resultData = document.createElement("div");
      resultData.classList.add("item");
      resultData.innerHTML = `
              <p>${name}</p>
              <div class="delete">
                <button data-id="${id}" class="deleteBtn" >
                  <box-icon 
                    color="#e21430"
                    name="trash-alt"
                    type="solid"
                  ></box-icon>
                </button>
              </div>
        `;
      resultsContainer.appendChild(resultData);
    });

    //fetch all trash buttons
    getAllDeletes();
  }
}

function getAllDeletes() {
  let deleteBtns = document.querySelectorAll(".item .deleteBtn");
  deleteBtns.forEach((item) => {
    item.addEventListener("click", (e) => {
      e.preventDefault()
      deleteAmenityFromDatabase(item.dataset.id);
    
    });
  });
}

//DELETE
function deleteAmenityFromDatabase(id) {
  if (id != null) {
    const requestOptions = {
      method: "DELETE",
      redirect: "follow",
    };

    fetch(`http://localhost:8090/api/amenities/${id}`, requestOptions)
      .then((response) => response.text())
      .then((result) => window.location.reload(true))
      .catch((error) => console.log("error", error));
  }
}
