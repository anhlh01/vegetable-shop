/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
  "use strict";
  var links = document.getElementsByClassName("link");
console.log(links);
  for (let i = 0; i < links.length; i++) {
    links[i].addEventListener("click", function (e) {
        console.log(links[i]);
      var active = document.querySelector(".active");
      active.classList.remove("active");
      links[0].classList.remove("active");
      links[i].classList.add("active");
    });
  }
})();

