var cvs = document.getElementById("canvas");
var ctx = cvs.getContext("2d");

var bird = new Image();
var bg = new Image();
var fg = new Image();
var pipeUp = new Image();
var pipeBottom = new Image();

fetch('/getcurrentbird')
  .then(response => response.text())
  .then(data => {
    // Обработка полученных данных
    var currentBird = data;
    bird.src = "img/" +currentBird+ ".png" // Значение текущей птицы
    bird.height= 26;
    bird.width=38;
    bird.style = {
      width: "20px",
      height: "20px"
    }
    bird.classList.add("game_image")
    console.log(currentBird);
    // Дальнейшая обработка данных
  })
  .catch(error => {
    // Обработка ошибки
    console.error('Error:', error);
  });


bg.src = "img/bg.png";
fg.src = "img/fg.png";
pipeUp.src = "img/pipeUp.png";
pipeBottom.src = "img/pipeBottom.png";

// Звуковые файлы



var gap = 130;
var score = 0;
var bestScore = 0;
// Позиция птички
var xPos = 80;
var yPos = 150;
var grav = 1.5;
// При нажатии на какую-либо кнопку
document.addEventListener("click", moveUp);
document.addEventListener("keydown", moveUp);
function moveUp() {
 grav = -2;
}



// Создание блоков
var pipe = [];
function new_game(){
score = 0;
// Позиция птички
xPos = 80;
yPos = 100;
grav = 0;
for(var i = 0; i < 5; i++){  
pipe[i] = {
 x : cvs.width-100 + 270 *i,
 y : Math.floor(Math.random() * pipeUp.height) - pipeUp.height
}
}
}
window.onload = new_game;

function max_x(){
    var max = 0;
    for (var i = 0; i< pipe.length; i ++)
    {
        if (pipe[i].x > max){
            max = pipe[i].x;
        }
    }
    return max;
}
var bgCount = Math.ceil(cvs.width/ bg.width);
function draw() {
    for (var j = 0; j < 4; j++) {
        ctx.drawImage(bg, (bg.width) * j, 0); 
    }
 for(var i = 0; i < pipe.length; i++) {
 ctx.drawImage(pipeUp, pipe[i].x, pipe[i].y);
 ctx.drawImage(pipeBottom, pipe[i].x, pipe[i].y + pipeUp.height + gap);

 pipe[i].x -= 1;

 

 // Отслеживание прикосновений
 if(xPos + bird.width >= pipe[i].x
 && xPos <= pipe[i].x + pipeUp.width
 && (yPos <= pipe[i].y + pipeUp.height
 || yPos + bird.height >= pipe[i].y + pipeUp.height + gap) || yPos + bird.height >= cvs.height - fg.height) {
    console.log(username);
 var xhr = new XMLHttpRequest();
xhr.open("POST", "/save", true);
xhr.setRequestHeader("Content-Type", "application/json");

var data = {
  username: username,
  score: score
};

xhr.onreadystatechange = function() {
  if (xhr.readyState === 4) {
    if (xhr.status === 200) {
      console.log("Score saved successfully.");
    } else {
      console.log("Error saving score:", xhr.status);
    }
  }
};

xhr.send(JSON.stringify(data));

 new_game();
 // Перезагрузка страницы
 }

 if(pipe[i].x < 5) {
 score++;
 if(score> bestScore){bestScore= score;}
 pipe[i].x = max_x() + 270;
 pipe[i].y =  Math.floor(Math.random() * pipeUp.height) - pipeUp.height;
 }
 }
 for (var j = 0; j < 4; j++) {
    ctx.drawImage(fg, j*fg.width, cvs.height - fg.height);
}
 ctx.drawImage(bird, xPos, yPos, bird.width,bird.height);
 

 yPos += grav;
 grav += 0.03;

 ctx.fillStyle = "#000";
 ctx.font = "24px Verdana";
 ctx.fillText("Счет: " + score, 10, cvs.height - 20);
 ctx.fillText("Рекорд: " + bestScore, Math.floor(cvs.width* 0.8), cvs.height - 20);
 requestAnimationFrame(draw);
}

pipeBottom.onload = draw;