/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const xhr = new XMLHttpRequest();
xhr.open("GET", "MainController?action=Show Product", true);


xhr.onload=function (){
    if(this.readyState===4 && this.status===200){
        console.log("duoc roi ne");
    }
};

xhr.send();
