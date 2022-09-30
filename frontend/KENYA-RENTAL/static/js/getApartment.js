const featuredContainer = document.querySelector(".featured");
const homeContainer = document.querySelector(".top-featured");

var requestOptions = {
    method: 'GET',
    redirect: 'follow'
};
// IS FEATURED APARTMENTS
fetch("http://localhost:8090/api/apartment/featured", requestOptions)
    .then(response => response.json())
    .then(result => featuredApartment(result))
    .catch(error => console.log('error', error));

function featuredApartment(result) {
    const { data, message, status } = result;
    if (message === "featured Apartments" && status === 200) {
        data.map(apartment => {
            const { name, locationTown, id, images } = apartment
            //create a carousel for this
            // learn to use grid
            // design on figma 
            // use booking app inspiration
            const topFeaturedElem = document.createElement("div")
            topFeaturedElem.classList.add("image");
            const { imageData } = images[0]

            topFeaturedElem.innerHTML = `
            <img src='data:image/png;base64, ${imageData}'/>
            <a href="singleApartment.html" data-id=${id} >${name}</a>
            <div class="details">
                <i class="fa-solid fa-circle-check"></i>
                <p>${locationTown}</p>
            </div>
            `
            homeContainer.appendChild(topFeaturedElem)

        })

    }
}

// GET APARTMENTS FROM DB

fetch("http://localhost:8090/api/apartment/", requestOptions)
    .then(response => response.json())
    .then(result => showApartment(result))
    .catch(error => console.log('error', error));

function showApartment(result) {
    const { data, message, status } = result;
    if (message === "Successfully retrieved data!" && status === 200) {
        data.map(apartment => {
            const { name, locationTown, id, images } = apartment
            //create a carousel for this
            // learn to use grid
            // design on figma 
            // use booking app inspiration
            const featuredItemsElem = document.createElement("div")
            featuredItemsElem.classList.add("featuredItems");
            const { imageData } = images[0]
            const img = document.createElement("img");
            img.classList.add("featuredImage")
            img.src = "data:image/png;base64," + imageData;

            featuredItemsElem.innerHTML = `
            <div class="featuredTitles">
            <a href="#" onclick=displayOneApartment('${id}')>${name}</a>
            <h2>${locationTown}</h2>
            </div> `
            featuredItemsElem.appendChild(img)
            featuredContainer.appendChild(featuredItemsElem)

        })

    }
}
//display apartment
function displayOneApartment(id) {

    fetch(`http://localhost:8090/api/apartment/${id}`, requestOptions)
        .then(response => response.json())
        .then(result => loadApartment(result))
        .catch(error => console.log('error', error));
}

function loadApartment(result){
    window.open("singleApartment")
}
