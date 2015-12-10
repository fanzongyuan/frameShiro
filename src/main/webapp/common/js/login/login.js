$(function(){
	$(".inputTip").click(function(){
		$(this).find("input").focus();
	});
	$("#username").on({
		"focus":function(){
			$(this).siblings(".tip").css("display", "none");
		},
		"blur":function(){
			if($(this).val().length === 0) $(this).siblings(".tip").css("display", "block");
			check("username", "uerror", "请输入用户名");
		}
	});
	//得到焦点
	$("#password").on({
		"focus":function(){
			$(this).siblings(".tip").css("display", "none");
			$("#left_hand").animate({
				left: "150",
				top: " -38"
			},{step: function(){
				if(parseInt($("#left_hand").css("left"))>140){
					$("#left_hand").attr("class","left_hand");
				}
			}}, 2000);
			$("#right_hand").animate({
				right: "-64",
				top: "-38px"
			},{step: function(){
				if(parseInt($("#right_hand").css("right"))> -70){
					$("#right_hand").attr("class","right_hand");
				}
			}}, 2000);
		},
		"blur":function(){
			if($(this).val().length === 0) $(this).siblings(".tip").css("display", "block");
			check("password", "perror", "请输入密码");
			
			$("#left_hand").attr("class","initial_left_hand");
			$("#left_hand").attr("style","left:100px;top:-12px;");
			$("#right_hand").attr("class","initial_right_hand");
			$("#right_hand").attr("style","right:-112px;top:-12px");
		}
	});
	//登录检测
	$("#btnlogin").click(function(){
		if(!check("username", "uerror", "请输入用户名")) return false;
		if(!check("password", "perror", "请输入密码")) return false;
	});
});

function check(id, errorClass, showText){
	var o = $("#" + id);
	if(o.val().length === 0){
		$("." + errorClass).addClass("showerror").text(showText);
		o.addClass("errorInput");
		return false;
	}else{
		$("." + errorClass).removeClass("showerror").text("&nbsp;");
		o.removeClass("errorInput");
		return true;
	}
};

/**
 * 表单提交
 */
function formSubmit(){
	$("#loginForm").submit();
}