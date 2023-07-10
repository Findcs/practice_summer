document.getElementById('changeNicknameButton').addEventListener('click', function() {
    console.log(username);
    var changeNickname = document.getElementById('changeNickname');
    if (changeNickname.style.display === 'none') {
      changeNickname.style.display = 'block';
    } else {
      changeNickname.style.display = 'none';
    }
  });

  document.getElementById('submitchange').addEventListener('click', function() {
var xhr = new XMLHttpRequest();
xhr.open("POST", "/profile/change-nickname", true);
xhr.setRequestHeader("Content-Type", "application/json");
var newusername = document.getElementById('newNickname').value;
console.log("Отправляем запрос c  новым именем" + newusername);

var data = {
  username: username,
  newusername: newusername
};

xhr.onreadystatechange = function() {
  if (xhr.readyState === 4) {
    if (xhr.status === 200) {
      console.log("Запрос на ");
      location.reload();
      var usernameElement = document.getElementById("username");
      usernameElement.textContent = newusername; 
    } else {
      console.log("Error saving score:", xhr.status);
    }
  }
};

xhr.send(JSON.stringify(data));
});