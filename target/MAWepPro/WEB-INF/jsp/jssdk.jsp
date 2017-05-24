<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype>
<html>
<head>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
	window.onload = function() {
		alert(3);
		wx.config({
			debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId : '${wxConfig.appId}', // 必填，公众号的唯一标识
			timestamp : ${wxConfig.timestamp}, // 必填，生成签名的时间戳
			nonceStr : '${wxConfig.nonceStr}', // 必填，生成签名的随机串
			signature : '${wxConfig.signature}',// 必填，签名，见附录1
			jsApiList : ["onMenuShareQQ","openLocation","getLocation"]// 必填，需要使用的JS接口列表，所有JS接口列表见附录2

		});
	}
	wx.ready(function() {
		
		wx.getLocation({

		    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'

		    success: function (res) {

		        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90

		        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。

		        var speed = res.speed; // 速度，以米/每秒计

		        var accuracy = res.accuracy; // 位置精度
		        alert(latitude);
		        wx.openLocation({

		            latitude: latitude, // 纬度，浮点数，范围为90 ~ -90

		            longitude: longitude, // 经度，浮点数，范围为180 ~ -180。

		            name: '', // 位置名

		            address: '', // 地址详情说明

		            scale: 1, // 地图缩放级别,整形值,范围从1~28。默认为最大

		            infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转

		        });

		    }
		});
		alert("ok");
			wx.onMenuShareQQ({

			    title: '1111', // 分享标题

			    desc: 'sdasd', // 分享描述

			    link: 'www.baidu.com', // 分享链接

			    imgUrl: 'www.baidu.com', // 分享图标

			    success: function () { 

			       // 用户确认分享后执行的回调函数

			    },

			    cancel: function () { 
	            alert("cancel");
			       // 用户取消分享后执行的回调函数

			    }

			});
		// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。

	});
	wx.error(function(res) {
		alert("error");
		// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

	});
	
</script>
</head>
<body>
<button onclick="shareQQ()">share qq</button>
</body>
</html>