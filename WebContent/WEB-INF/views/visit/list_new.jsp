<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<style>
	.tour-backdrop {
	  z-index: 19998;
	}
	
	.tour-step-backdrop {
	  z-index: 19999;
	}
	
	.tour-step-background {
	  z-index: 19999;
	}
	.popover[class*="tour-"] {
	  z-index: 19999;
	}
	
	body {
		font-family:MicrosoftYaHei;	
	}
</style>
<section class="content">

	<div class="row page-titles">
	    <div class="col-md-6 col-8 align-self-center">
	    	<h3 class="text-themecolor m-b-0 m-t-0"><font color="black">所有参团活动</font></h3>
	    </div>
	</div>
	
	<div class="row">
		<div class="col-md-1">
			<h5><span style="line-height:19px;font-size1: 14px;color: rgba(82,92,102,0.7);">全部城市</span></h5>
		</div>
		<div class="col-md-10">
			<h5><span style="line-height:19px;font-size1: 14px;">
					<span style="background-color: rgba(42,145,238,0.08);color: rgba(42,145,238,1);padding:5px">不限</span>&emsp;&emsp;
					<span style="padding:5px">吉隆坡</span>&emsp;&emsp;
					<span style="padding:5px">马六甲</span>&emsp;&emsp;
					<span style="padding:5px">槟城</span>&emsp;&emsp;
					<span style="padding:5px">沙巴</span>&emsp;&emsp;
					<span style="padding:5px">新山</span>&emsp;&emsp;
					<span style="padding:5px">兰卡威</span>&emsp;&emsp;
					<span style="padding:5px">兰卡威</span>&emsp;&emsp;
					<span style="padding:5px">兰卡威</span>&emsp;&emsp;
					<span style="padding:5px">兰卡威</span>&emsp;&emsp;
					<span style="padding:5px">兰卡威</span>
				</span></h5>
		</div>
		<div class="col-md-1">
			<h5><span style="line-height:19px;font-size1: 14px;color: rgba(82,92,102,0.7);">更多&nbsp;<i class="fa fa-angle-down"></i></span></h5>
		</div>
	</div>
	
	<div class="row" style="margin-top: 14px;">
		<div class="col-md-1">
			<h5><span style="line-height:19px;font-size1: 14px;color: rgba(82,92,102,0.7);">活动类型</span></h5>
		</div>
		<div class="col-md-11">
			<h5><span style="line-height:19px;font-size1: 14px;">
					<span style="background-color: rgba(42,145,238,0.08);color: rgba(42,145,238,1);padding:5px">不限</span>&emsp;&emsp;
					<span style="padding:5px">房产</span>&emsp;&emsp;
					<span style="padding:5px">独栋别墅</span>&emsp;&emsp;
					<span style="padding:5px">联排别墅</span>&emsp;&emsp;
					<span style="padding:5px">商业地产</span>&emsp;&emsp;
					<span style="padding:5px">其他</span>
				</span></h5>
		</div>
	</div>
	 
	<div class="row" style="margin-top:25px;">
		<div style="width:49%;background-color:white;padding-left:15px;padding-top:15px;padding-bottom: 10px">
			<div style="position: relative;float:left;margin-right:20px">
				<img style="display: block;" src="${pageContext.request.contextPath}/resources/images/pic.PNG" />
			</div>
			<div style="position: relative;">
				<div class="row">
					<span style="font-weight:bold;font-size:18px;color:rgba(31,41,51,0.9);line-height:24px;">&nbsp;吉隆坡/新山/马六甲 5天4晚考察团 专车接送…</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-badge"></i>&nbsp;&nbsp;机场接送&nbsp;&nbsp;|&nbsp;&nbsp;5星级食宿&nbsp;&nbsp;|&nbsp;&nbsp;专业导游&nbsp;&nbsp;|&nbsp;&nbsp;个性化推荐</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-plane"></i>&nbsp;&nbsp;出发：上海 —— 终点：吉隆坡</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-clock"></i>&nbsp;&nbsp;出发：2018/09/23 —— 返程：2018/09/30</span>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="col-md-6" style="padding-left: 0px">
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">成团目标:&nbsp;&nbsp;</span>
						<span style="font-size:16px;color:rgba(42,145,238,1);line-height:21px;font-weight:bold;vertical-align: bottom;">30</span>
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">&nbsp;&nbsp;人</span>
					</div>
					<div class="col-md-6" style="text-align: right;">
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">已报名:&nbsp;&nbsp;</span>
						<span style="font-size:16px;color:rgba(42,145,238,1);line-height:21px;font-weight:bold;vertical-align: bottom;">20</span>
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">&nbsp;&nbsp;人</span>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="progress" style="width: 100%">
						<div class="progress-bar bg-info" role="progressbar" style="width: 67%;height:4px;background:rgba(192,222,250,1);border-radius:2px;"></div>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="col-md-6" style="padding-left: 0px">
						<span style="font-size:14px;color:rgba(232,90,100,1);line-height:19px;vertical-align: bottom;">¥&nbsp;&nbsp;</span>
						<span style="font-size:24px;color:rgba(232,90,100,1);line-height:31px;font-weight:bold;vertical-align: bottom;">2399</span>
						<span style="font-size:14px;color:rgba(232,90,100,1);line-height:19px;vertical-align: bottom;">&nbsp;&nbsp;起</span>
					</div>
					<div class="col-md-6" style="text-align: right;">
						<button type="button" class="btn btn-block btn-info">立即报名</button>
					</div>
				</div>
			</div>
		</div>
		<div style="width:2%;">&nbsp;</div>
		<div style="width:49%;background-color:white;padding-left:15px;padding-top:15px;padding-bottom: 10px">
			<div style="position: relative;float:left;margin-right:20px">
				<img style="display: block;" src="${pageContext.request.contextPath}/resources/images/pic.PNG" />
			</div>
			<div style="position: relative;">
				<div class="row">
					<span style="font-weight:bold;font-size:18px;color:rgba(31,41,51,0.9);line-height:24px;">&nbsp;吉隆坡/新山/马六甲 5天4晚考察团 专车接送…</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-badge"></i>&nbsp;&nbsp;机场接送&nbsp;&nbsp;|&nbsp;&nbsp;5星级食宿&nbsp;&nbsp;|&nbsp;&nbsp;专业导游&nbsp;&nbsp;|&nbsp;&nbsp;个性化推荐</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-plane"></i>&nbsp;&nbsp;出发：上海 —— 终点：吉隆坡</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-clock"></i>&nbsp;&nbsp;出发：2018/09/23 —— 返程：2018/09/30</span>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="col-md-6" style="padding-left: 0px">
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">成团目标:&nbsp;&nbsp;</span>
						<span style="font-size:16px;color:rgba(42,145,238,1);line-height:21px;font-weight:bold;vertical-align: bottom;">30</span>
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">&nbsp;&nbsp;人</span>
					</div>
					<div class="col-md-6" style="text-align: right;">
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">已报名:&nbsp;&nbsp;</span>
						<span style="font-size:16px;color:rgba(42,145,238,1);line-height:21px;font-weight:bold;vertical-align: bottom;">20</span>
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">&nbsp;&nbsp;人</span>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="progress" style="width: 100%">
						<div class="progress-bar bg-info" role="progressbar" style="width: 67%;height:4px;background:rgba(192,222,250,1);border-radius:2px;"></div>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="col-md-6" style="padding-left: 0px">
						<span style="font-size:14px;color:rgba(232,90,100,1);line-height:19px;vertical-align: bottom;">¥&nbsp;&nbsp;</span>
						<span style="font-size:24px;color:rgba(232,90,100,1);line-height:31px;font-weight:bold;vertical-align: bottom;">2399</span>
						<span style="font-size:14px;color:rgba(232,90,100,1);line-height:19px;vertical-align: bottom;">&nbsp;&nbsp;起</span>
					</div>
					<div class="col-md-6" style="text-align: right;">
						<button type="button" class="btn btn-block btn-info">立即报名</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row" style="margin-top:25px;">
		<div style="width:49%;background-color:white;padding-left:15px;padding-top:15px;padding-bottom: 10px">
			<div style="position: relative;float:left;margin-right:20px">
				<img style="display: block;" src="${pageContext.request.contextPath}/resources/images/pic.PNG" />
			</div>
			<div style="position: relative;">
				<div class="row">
					<span style="font-weight:bold;font-size:18px;color:rgba(31,41,51,0.9);line-height:24px;">&nbsp;吉隆坡/新山/马六甲 5天4晚考察团 专车接送…</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-badge"></i>&nbsp;&nbsp;机场接送&nbsp;&nbsp;|&nbsp;&nbsp;5星级食宿&nbsp;&nbsp;|&nbsp;&nbsp;专业导游&nbsp;&nbsp;|&nbsp;&nbsp;个性化推荐</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-plane"></i>&nbsp;&nbsp;出发：上海 —— 终点：吉隆坡</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-clock"></i>&nbsp;&nbsp;出发：2018/09/23 —— 返程：2018/09/30</span>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="col-md-6" style="padding-left: 0px">
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">成团目标:&nbsp;&nbsp;</span>
						<span style="font-size:16px;color:rgba(42,145,238,1);line-height:21px;font-weight:bold;vertical-align: bottom;">30</span>
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">&nbsp;&nbsp;人</span>
					</div>
					<div class="col-md-6" style="text-align: right;">
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">已报名:&nbsp;&nbsp;</span>
						<span style="font-size:16px;color:rgba(42,145,238,1);line-height:21px;font-weight:bold;vertical-align: bottom;">20</span>
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">&nbsp;&nbsp;人</span>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="progress" style="width: 100%">
						<div class="progress-bar bg-info" role="progressbar" style="width: 67%;height:4px;background:rgba(192,222,250,1);border-radius:2px;"></div>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="col-md-6" style="padding-left: 0px">
						<span style="font-size:14px;color:rgba(232,90,100,1);line-height:19px;vertical-align: bottom;">¥&nbsp;&nbsp;</span>
						<span style="font-size:24px;color:rgba(232,90,100,1);line-height:31px;font-weight:bold;vertical-align: bottom;">2399</span>
						<span style="font-size:14px;color:rgba(232,90,100,1);line-height:19px;vertical-align: bottom;">&nbsp;&nbsp;起</span>
					</div>
					<div class="col-md-6" style="text-align: right;">
						<button type="button" class="btn btn-block btn-info">立即报名</button>
					</div>
				</div>
			</div>
		</div>
		<div style="width:2%;">&nbsp;</div>
		<div style="width:49%;background-color:white;padding-left:15px;padding-top:15px;padding-bottom: 10px">
			<div style="position: relative;float:left;margin-right:20px">
				<img style="display: block;" src="${pageContext.request.contextPath}/resources/images/pic.PNG" />
			</div>
			<div style="position: relative;">
				<div class="row">
					<span style="font-weight:bold;font-size:18px;color:rgba(31,41,51,0.9);line-height:24px;">&nbsp;吉隆坡/新山/马六甲 5天4晚考察团 专车接送…</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-badge"></i>&nbsp;&nbsp;机场接送&nbsp;&nbsp;|&nbsp;&nbsp;5星级食宿&nbsp;&nbsp;|&nbsp;&nbsp;专业导游&nbsp;&nbsp;|&nbsp;&nbsp;个性化推荐</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-plane"></i>&nbsp;&nbsp;出发：上海 —— 终点：吉隆坡</span>
				</div>
				<div class="row" style="margin-top:10px;">
					<span style="font-size:14px;color:rgba(82,92,102,1);line-height:19px;"><i class="icon-clock"></i>&nbsp;&nbsp;出发：2018/09/23 —— 返程：2018/09/30</span>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="col-md-6" style="padding-left: 0px">
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">成团目标:&nbsp;&nbsp;</span>
						<span style="font-size:16px;color:rgba(42,145,238,1);line-height:21px;font-weight:bold;vertical-align: bottom;">30</span>
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">&nbsp;&nbsp;人</span>
					</div>
					<div class="col-md-6" style="text-align: right;">
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">已报名:&nbsp;&nbsp;</span>
						<span style="font-size:16px;color:rgba(42,145,238,1);line-height:21px;font-weight:bold;vertical-align: bottom;">20</span>
						<span style="font-size:12px;color:rgba(82,92,102,1);line-height:16px;vertical-align: bottom;">&nbsp;&nbsp;人</span>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="progress" style="width: 100%">
						<div class="progress-bar bg-info" role="progressbar" style="width: 67%;height:4px;background:rgba(192,222,250,1);border-radius:2px;"></div>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">
					<div class="col-md-6" style="padding-left: 0px">
						<span style="font-size:14px;color:rgba(232,90,100,1);line-height:19px;vertical-align: bottom;">¥&nbsp;&nbsp;</span>
						<span style="font-size:24px;color:rgba(232,90,100,1);line-height:31px;font-weight:bold;vertical-align: bottom;">2399</span>
						<span style="font-size:14px;color:rgba(232,90,100,1);line-height:19px;vertical-align: bottom;">&nbsp;&nbsp;起</span>
					</div>
					<div class="col-md-6" style="text-align: right;">
						<button type="button" class="btn btn-block btn-info">立即报名</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</section>
