/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    "use strict";

    const password = document.getElementById("password");
    const passwordMessage = document.querySelector(".password");
    const confirm = document.getElementById("confirm");
    const confirmMessage = document.querySelector(".confirm");

    password.addEventListener("input", function () {
        if (password.value.length < 8 || password.value.length > 50) {
            passwordMessage.innerHTML = "The length of user name is between 8 to 50!";
        } else {
            passwordMessage.innerHTML = "";
        }
    });

    confirm.addEventListener("input", function () {
        if (confirm.value.localeCompare(password.value) !== 0) {
            confirmMessage.innerHTML = "The repeat password is not correct!";
        } else {
            confirmMessage.innerHTML = "";
        }
    });

    password.addEventListener("input", function () {
        if (confirm.value.localeCompare(password.value) !== 0) {
            confirmMessage.innerHTML = "The repeat password is not correct!";
        } else {
            confirmMessage.innerHTML = "";
        }
    });

})();


