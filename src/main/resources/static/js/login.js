/* -----------------------------------------------
/* How to use? : Check the GitHub README
/* ----------------------------------------------- */

/* To load a config file (particles.json) you need to host this demo (MAMP/WAMP/local)... */
/*
particlesJS.load('particles-js', 'particles.json', function() {
  console.log('particles.js loaded - callback');
});
*/

/* Otherwise just put the config content (json): */

particlesJS('particles-js',

	{
		"particles": {
			"number": {
				"value": 40,
				"density": {
					"enable": true,
					"value_area": 800
				}
			},
			"color": {
				"value": "#ffffff"
			},
			"shape": {
				"type": "circle",
				"stroke": {
					"width": 0,
					"color": "#000000"
				},
				"polygon": {
					"nb_sides": 5
				},
				"image": {
					"src": "img/github.svg",
					"width": 100,
					"height": 100
				}
			},
			"opacity": {
				"value": 0.7,
				"random": false,
				"anim": {
					"enable": false,
					"speed": 1,
					"opacity_min": 0.1,
					"sync": false
				}
			},
			"size": {
				"value": 3,
				"random": true,
				"anim": {
					"enable": false,
					"speed": 40,
					"size_min": 0.1,
					"sync": false
				}
			},
			"line_linked": {
				"enable": true,
				"distance": 150,
				"color": "#ffffff",
				"opacity": 0.6,
				"width": 1
			},
			"move": {
				"enable": true,
				"speed": 6,
				"direction": "none",
				"random": false,
				"straight": false,
				"out_mode": "out",
				"bounce": false,
				"attract": {
					"enable": false,
					"rotateX": 600,
					"rotateY": 1200
				}
			}
		},
		"interactivity": {
			"detect_on": "canvas",
			"events": {
				"onhover": {
					"enable": true,
					"mode": "grab"
				},
				"onclick": {
					"enable": true,
					"mode": "push"
				},
				"resize": true
			},
			"modes": {
				"grab": {
					"distance": 200,
					"line_linked": {
						"opacity": 1
					}
				},
				"bubble": {
					"distance": 400,
					"size": 40,
					"duration": 2,
					"opacity": 8,
					"speed": 3
				},
				"repulse": {
					"distance": 200,
					"duration": 0.4
				},
				"push": {
					"particles_nb": 4
				},
				"remove": {
					"particles_nb": 2
				}
			}
		},
		"retina_detect": false
	}

);

function hasClass(elem, cls) {
    cls = cls || '';
    if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
    return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
}

function addClass(ele, cls) {
    if (!hasClass(ele, cls)) {
        ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
    }
}

function removeClass(ele, cls) {
    if (hasClass(ele, cls)) {
        var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
        while (newClass.indexOf(' ' + cls + ' ') >= 0) {
            newClass = newClass.replace(' ' + cls + ' ', ' ');
        }
        ele.className = newClass.replace(/^\s+|\s+$/g, '');
    }
}

$(function () {
    $(".back").hide();
    $("#tip").hide();
    $("#tip2").hide();
})


function login() {
    var loginAccount=$("#loginAccount").val();
    var loginPass=$("#loginPass").val();
    var loginType=$("#loginType").val();
    if(!loginAccount){
        $("#loginAccount").focus();
        return;
    }
    if(!loginPass){
        $("#loginPass").focus();
        return;
    }
    $.ajax({
        url:"/login/login",
        type:"post",
        data:{
            account:loginAccount,
            password:loginPass,
            type:loginType
        } ,
        dataType:"json",
        success:function (res) {
            if(res.code==1){
                window.location.href = res.url;
            }else{
                $("#tip").show();
                $("#tip").html(res.msg)
                hideTip("tip")
            }
        }
    })
}

function register() {
    var registerName=$("#registerName").val();
    var registerAccount=$("#registerAccount").val();
    var registerPass=$("#registerPass").val();
    var registerRePass=$("#registerRePass").val();
    if(!registerName){
        $("#registerName").focus();
        return;
    }
    if(!registerAccount){
        $("#registerAccount").focus();
        return;
    }
    if(!registerPass){
        $("#registerPass").focus();
        return;
    }
    if(!registerRePass){
        $("#registerRePass").focus();
        return;
    }
    if(registerPass!= registerRePass){
        $("#tip2").show();
        $("#tip2").html("两次密码不一致");
        $("#registerRePass").focus();
        hideTip("tip2")
        return;
    }

    $.ajax({
        url:"/login/register",
        type:"post",
        data:{
			stuNum:registerAccount,
            name:registerName,
            password:registerPass,
		} ,
        dataType:"json",
        success:function (res) {
            $("#tip2").show();
            if(res.code==1){
                $("#tip2").html(res.msg)
				//注册成功，清空输入框
                $("#registerAccount").val("");
                $("#registerName").val("");
                $("#registerPass").val("");
                $("#registerRePass").val("");
            }else{
                $("#tip2").html(res.msg)
            }
            hideTip("tip2")
        }
    })
}

function hideTip(tipId) {
    setTimeout(function(){
        $("#"+tipId).hide();
    },2000)
}
function showBack(type) {
    if(type==1){
        $(".front").hide();
        $(".back").show();
        $(".login").height(600)
    }else{
        $(".back").hide();
        $(".front").show();
        $(".login").height(500)
    }
}