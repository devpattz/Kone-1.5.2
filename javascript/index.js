setInterval(function(){
    window.onbeforeunload = null;
},10);
setTimeout(function(){
var displayfakedoc = false;
if (Cookies.get('fakedocmod') == 'true'){
document.addEventListener("keyup", function(event) {
  


  let fakedockeycode = Cookies.get('fakedockeycode');
  if (fakedockeycode == undefined || fakedockeycode == "undefined" || fakedockeycode == 'undefined'){
  Cookies.set('fakedockeycode', '`');
  }


  
if (event.key === fakedockeycode) {
    if (displayfakedoc === true){
        displayfakedoc = false;
        document.getElementById("available").style.display = "none";

    }
    else {
    displayfakedoc = true;
    document.getElementById("available").style.display = "block";
    }
}
});
}
},3)
var displaykonemenu = false;

function koneclient_notif(title,content){

  document.getElementById("notif-title").innerText = title;
  document.getElementById("notif-content-p").innerText = content;

  document.getElementById("notifications").style.left = "100vw";
  document.getElementById("notifications").style.transform = "translateX(-260px)";
  document.getElementById("notif-seconds").style.width = "0%";
  setTimeout(function(){
    document.getElementById("notifications").style.left = "130vw";
    setTimeout(function(){
      document.getElementById("notif-seconds").style.transition = "width 0s linear";
      document.getElementById("notif-seconds").style.width = "100%";
      setTimeout(function(){
        document.getElementById("notif-seconds").style.transition = "width 15s linear";
      },200);
    },2000);
    
  },15000);
}



setTimeout(function(){
  
  koneclient_notif("Minecraft Crashing?", "Try holding shift and clicking reload.");
},5000);

setTimeout(function(){
  koneclient_notif("Changelog:", "Will be moved to kone client menu.");
},30000);


document.addEventListener("keyup", function(event) {
if (event.code === "AltRight") {
    if (displaykonemenu === true){
        displaykonemenu = false;
        document.getElementById("koneclientmenu").style.display = "none";
        document.getElementById("game_frame").style.filter = "blur(0px)";
    document.getElementById("game_frame").style.webkitFilter = "blur(0px)";
        document.getElementById("koneclientmenu").style.backgroundColor = "rgba(12, 12, 12, 0)";
        $( "#dialog-confirm" ).dialog( "close" );
    }
    else {
    displaykonemenu = true;
    document.getElementById("koneclientmenu").style.display = "block";
    document.getElementById("koneclientmenu").style.backgroundColor = "rgba(12, 12, 12, 0)";
    document.getElementById("game_frame").style.filter = "blur(16px)";
    document.getElementById("game_frame").style.webkitFilter = "blur(16px)";
    }
}
});


setTimeout(function(){
let fakedockeycode = Cookies.get('fakedockeycode');




document.getElementById("fakedockeybindvalue").innerHTML = fakedockeycode;


document.getElementById("newkeybindfakedoc").addEventListener("mousedown", function() {
    this.style.border = "1px solid rgb(238, 81, 81)";
    this.style.backgroundColor = "rgb(105, 31, 31)";
    document.getElementById("keyboardlockmessage").style.opacity = "1";
    document.addEventListener("keyup", function keybindnewfakedoc(event) {
      document.getElementById("newkeybindfakedoc").style.border = "1px solid rgb(167, 167, 167)";
      document.getElementById("newkeybindfakedoc").style.backgroundColor = "rgb(48, 48, 48)";
      document.getElementById("keyboardlockmessage").style.opacity = "0";
      document.getElementById("fakedockeybindvalue").innerHTML = event.key;
      document.removeEventListener("keyup", keybindnewfakedoc);
    });
  
});
},5);




let fakedocmodval = Cookies.get('fakedocmod');
	setTimeout(function(){
    let fakedocmodval = Cookies.get('fakedocmod');
		if (fakedocmodval == 'true'){
		document.getElementById("mod_slider_round_check").checked = true;
	}
	else if (fakedocmodval == 'false'){
		document.getElementById("mod_slider_round_check").checked = false;
	}
	else{
		document.getElementById("mod_slider_round_check").checked = true;
    Cookies.set('fakedocmod', 'true');
	}
	},1);


  function SaveModConfig(){
    console.log("Saving Config...");


    alert("Please Reload Webpage for changes to take effect.");
    
    var keybindfakedoc = document.getElementById("fakedockeybindvalue").innerHTML;
    Cookies.set('fakedockeycode', keybindfakedoc);
    if (document.getElementById("mod_slider_round_check").checked == true){
      Cookies.set('fakedocmod', 'true');
    }
    else{
      Cookies.set('fakedocmod', 'false');
    }
  }



  function ReloadConfigFromCache(){

    document.getElementById("fakedockeybindvalue").innerHTML = Cookies.get('fakedockeycode');

    let fakedocmodval = Cookies.get('fakedocmod');
    setTimeout(function(){
      if (fakedocmodval == 'true'){
      document.getElementById("mod_slider_round_check").checked = true;
    }
    else if (fakedocmodval == 'false'){
      document.getElementById("mod_slider_round_check").checked = false;
    }
    else{
      document.getElementById("mod_slider_round_check").checked = true;
    }
    },1);
  }

setInterval(function(){
if (document.getElementById("mod_slider_round_check").checked == true){
    document.getElementById("slidertext").innerHTML = "ON";
    
}
else {
    document.getElementById("slidertext").innerHTML = "OFF";
    
}
//console.log(Cookies.get('fakedocmod'));
},10);










var current_version = "22w44a";
var user_prompted = false;
var cache_tricker = Math.floor(Math.random() * 100000000000) + 1  +numbercounter;
var numbercounter = 1
setInterval(function(){
numbercounter = numbercounter +1;
cache_tricker = Math.floor(Math.random() * 100000000000) + 1  +numbercounter;
if (user_prompted == false) {
    fetch('./version.txt?'+cache_tricker)
.then(response => response.text())
.then(data => {
  // Do something with your data
if (data == current_version){
    console.log("kone client Update Manager: server version = client version ("+data+")");
}
else{
    alert("Update available: kone client "+data+", Please reload the webpage to update kone client");
    user_prompted = true;
    console.log("kone client Update Manager: User Prompted.")
}
  console.log("kone client Update Manager: server version: "+data);
});
}
else{
    console.log("kone client Update Manager: Update available, user already prompted.")
}
},10000);



if(document.location.href.startsWith("http:")) {
//alert("You are on http, you will not be able to use voice chat because Google Chrome, Edge, Firefox, and others prevent you from using your microphone.");
}
disablecloswithoutsave = false;
setInterval(function(){
  disablecloswithoutsave = false;
  console.log("skip save without changes disabled");
},15000)


function fakedocsetpage(){
  document.getElementById("modspage").style.display = "none";
  document.getElementById("fakedocsetpage").style.display = "block";
}
function backtomodpage(){
  document.getElementById("fakedocsetpage").style.display = "none";
  document.getElementById("modspage").style.display = "block";
}


$( function() {
var widthmodsmodal = window.innerWidth / 1.366
var heightmodsmodal = window.innerHeight / 1.149


$( "#CloseWithoutSave" ).dialog({
  autoOpen: false,
  width: 500,
  height: "auto",
  buttons: {
    "Save": function(){
      SaveModConfig();
      $( this ).dialog( "close" );
      $( "#dialog-confirm" ).dialog( "close" );
    },
    "Dont Save": function(){
      ReloadConfigFromCache();
      $( this ).dialog( "close" );
      $( "#dialog-confirm" ).dialog( "close" );
    },
    "Cancel": function(){
      $( this ).dialog( "close" );
    }
  }
});



$( "#FeatureInBetaError" ).dialog({
  autoOpen: false,
  width: 500,
  height: "auto",
  buttons: {
  "Ok": function(){
    $( this ).dialog( "close" );
  }
  }
});

$( "#dialog-confirm" ).dialog({
  resizable: false,
  autoOpen: false,
  height: heightmodsmodal,
  width: widthmodsmodal,
  modal: false,
  buttons: {
    "Load Config": function(){
      $( "#FeatureInBetaError" ).dialog( "open" );
    },
    "Export Config": function(){
      $( "#FeatureInBetaError" ).dialog( "open" );
    },
    "Save": function() {
      SaveModConfig();
      disablecloswithoutsave = true;
    },
    Cancel: function() {
      if (disablecloswithoutsave == false){
        $( "#CloseWithoutSave" ).dialog( "open" );
      }
      else{
        $( this ).dialog( "close" );
      }
    }
  }
});

$( "#modsbutton_button" ).on( "click", function() {
  $( "#dialog-confirm" ).dialog( "open" );
  console.log("mods modal working");
});





} );





