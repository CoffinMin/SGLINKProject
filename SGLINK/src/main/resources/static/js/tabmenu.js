let tabHeader2 = document.getElementsByClassName("tab-header")[0];
let tabIndicator2 = document.getElementsByClassName("tab-indicator")[0];
let tabBody2 = document.getElementsByClassName("tab-body")[0];

let tabsPane2 = tabHeader2.getElementsByTagName("div");

for(let i=0;i<tabsPane2.length;i++){
  tabsPane2[i].addEventListener("click",function(){
    tabHeader2.getElementsByClassName("active")[0].classList.remove("active");
    tabsPane2[i].classList.add("active");
    tabBody2.getElementsByClassName("active")[0].classList.remove("active");
    tabBody2.getElementsByTagName("div")[i].classList.add("active");
    
    tabIndicator2.style.left = `calc(calc(100% / 4) * ${i})`;
  });
}