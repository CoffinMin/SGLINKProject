let tabHeader1 = document.getElementsByClassName("tab-header1")[0];
let tabIndicator1 = document.getElementsByClassName("tab-indicator1")[0];
let tabBody1 = document.getElementsByClassName("tab-body")[0];

let tabsPane1 = tabHeader1.getElementsByTagName("div");

for(let i=0;i<tabsPane1.length;i++){
  tabsPane1[i].addEventListener("click",function(){
    tabHeader1.getElementsByClassName("active1")[0].classList.remove("active1");
    tabsPane1[i].classList.add("active1");
    tabBody1.getElementsByClassName("active1")[0].classList.remove("active1");
    tabBody1.getElementsByTagName("div")[i].classList.add("active1");
    
    tabIndicator1.style.left = `calc(calc(100% / 4) * ${i})`;
  });
}