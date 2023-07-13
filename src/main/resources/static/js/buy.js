const buttons = document.querySelectorAll("button");

function badnotification(not){
  var notification = document.createElement("div");
  notification.classList.add("badcustom-notification");
  notification.textContent = not;
  document.body.appendChild(notification);
// Удаление элемента через некоторое время
  setTimeout(function() {
    notification.remove();
  }, 3000);
}

function goodnotification(not){
  var notification = document.createElement("div");
  notification.classList.add("goodcustom-notification");
  notification.textContent = not;
  document.body.appendChild(notification);

 
// Удаление элемента через некоторое время
  setTimeout(function() {
    notification.remove();
  }, 3000);
}

async function onbuyclick(event){
    const id =event.target.id;
    await fetch(
        "http://localhost:8080/buy",
        {
            method : "POST",
            body : JSON.stringify({name : id}),
            headers :{
                "Content-Type" : "application/json"
            }
        }
    ) 
    .then((res)=>{
        if (res.status == 200){
        goodnotification("Успешно куплено")
      }
      else{
        badnotification("Недостаточно денег")
      }
    })
}

 function getUserBirds() {
    fetch("/getuserbirds").then(async (res) => {
      if (res.status !== 200) {
        alert("ошибка");
        return;
      }
      const data = await res.json();
      const birdNames = data.map((item) => item.name);
      console.log(birdNames);
      buttons.forEach((item) => {
        if (birdNames.includes(item.id)) {
          item.disabled = true;
        }
      });
    });
  }


buttons.forEach((item ) =>{
    item.addEventListener('click', onbuyclick)
})
getUserBirds();
