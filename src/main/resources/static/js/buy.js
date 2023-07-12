const buttons = document.querySelectorAll("button");
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
    .then(()=>{
        alert("Куплено")
    })
    .catch(()=>{
        alert("Нету денег")
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
