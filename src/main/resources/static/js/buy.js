const buttons = document.querySelectorAll("button");
function onbuyclick(event){
    const id =event.target.id;
    fetch(
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

buttons.forEach((item ) =>{
    item.addEventListener('click', onbuyclick)
})