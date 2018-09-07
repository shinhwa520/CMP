<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<style>
	.content {
		background:rgba(245,245,245,1);
	}
	
	.block_bg {
		background:rgba(255,255,255,1);
		border-radius:0.22rem;
		border:0.06rem solid rgba(231,232,233,1);
		margin-bottom: 0.8rem;
		margin-right: 0.1rem;
	}

	.block_t {
		height:23.78rem;
	}
	
	.block_c {
		height:19.56rem;
	}
	
	.block_r1 {
		height:35.17rem;
	}
	
	.block_r2 {
		height:25.17rem;
	}
	
	.font_title {
		font-size:1.11rem;
		font-family:MicrosoftYaHei-Bold;
		
		color:rgba(31,41,51,0.9);
		line-height:1.44rem;
		padding: 1rem;
	}
	
	.block_t_util {
		margin-left: 0;
		margin-right: 3rem;
	}
	
	.block_t_title {
		height:4.11rem;
		font-size:1.56rem;
		font-family:MicrosoftYaHei-Bold;
		font-weight:bold;
		color:rgba(31,41,51,0.9);
		line-height:2.06rem;
	}
	.block_t_opt_bar {
		height:1.22rem;

	}
	.block_t_opt_item {
		width:5rem;
		height:1.78rem;
		background:rgba(42,145,238,0.08);
		font-size:0.89rem;
		font-family:MicrosoftYaHei;
		color:rgba(42,145,238,1);
		line-height:1.17rem;
		margin-right: 0.5rem;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	}
	.block_t_content {
		margin-top:0.5rem;
		font-size:0.89rem;
		font-family:MicrosoftYaHei;
		color:rgba(31,35,39,1);
		line-height:1.17rem;
	}
	.block_t_info {
		margin-top:5.5rem;
		font-size:0.78rem;
		font-family:MicrosoftYaHei;
		color:rgba(82,92,102,1);
		line-height:1.06rem;
	}
	.block_t_btn {
		margin-top:3rem;
	}
	
	.progress {
		margin-right: 3rem;
	}
	
	.progress .progress-bar {
		height:5px;
		background:rgba(192,222,250,1);
		border-radius:3px;
	}
	
	.block_t_info .block_t_info_item {
		padding-left:0;
	}
	
	.info_item_HL{
		width:30px;
		height:31px;
		font-size:24px;
		font-family:MicrosoftYaHei-Bold;
		font-weight:bold;
		color:rgba(42,145,238,1);
		line-height:31px;
	}
	
	.block_t_btn_item1{
		font-size:16px;
		font-family:MicrosoftYaHei;
		color:rgba(232,90,100,1);
		line-height:21px;
	}
	
	.block_t_btn_item1 span{
font-size:36px;
font-family:MicrosoftYaHei-Bold;
font-weight:bold;
color:rgba(232,90,100,1);
line-height:47px;
	}
	
	.block_t_btn_item2{
font-size:14px;
font-family:MicrosoftYaHei;
color:rgba(232,90,100,1);
line-height:19px;
background:rgba(232,90,100,0.08);
border-radius:2px 0px 0px 2px;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	}
	
	.block_t_btn_item3{
font-size:18px;
font-family:PingFangSC-Regular;
font-weight:400;
color:rgba(255,255,255,1);
line-height:25px;
background:rgba(42,145,238,1);
border-radius:2px;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	}
	
.nav-tabs{
height: 2.45rem;
}

.tab-content{
    top: 0;
    padding: 0;
}

.pane_item{
margin: 0;
}

.pane_item .pane_item_left{
padding-right: 0;
padding-bottom: .85rem;
font-size:16px;
font-family:MicrosoftYaHei;
color:rgba(138,145,153,1);
line-height:21px;
}
.pane_item .pane_item_right{
margin-left: -3.5rem;
padding-left: 0;
padding-bottom: .85rem;
font-size:16px;
font-family:MicrosoftYaHei;
color:rgba(31,35,39,1);
line-height:21px;
}

.block_r1_item_left{
padding-right: 0;
padding-bottom: .85rem;
font-size:14px;
font-family:MicrosoftYaHei;
color:rgba(138,145,153,1);
line-height:21px;
}

.block_r1_item_right{
padding-left: 0;
padding-bottom: .85rem;
font-size:14px;
font-family:MicrosoftYaHei;
color:rgba(31,35,39,1);
line-height:21px;
}

.block_r1_item_right2{
padding-left: 0;
padding-bottom: .85rem;
font-size:14px;
font-family:MicrosoftYaHei;
color:rgba(42,145,238,1);
line-height:21px;
}

.block_r1_item{
background-color: gray;
text-align:center;
font-size:18px;
font-family:MicrosoftYaHei;
color:rgba(31,35,39,1);
line-height:24px;
}
</style>
<section class="content">

	<div class="row">
		<div class="font_title">活动详情</div>
	 </div>
	 
	<div class="row block_t block_bg">
		<div class="col-md-3" style="background-color: gray;">
			photo
		</div>
		<div class="col-md-9">
			<div class="row block_t_util block_t_title">
				吉隆坡/新山/马六甲 5天4晚考察团 专车接送 个性化推荐看房团
			</div>
			<div class="row block_t_util">
				<div class="block_t_opt_item">
					机场接送
				</div>
				<div class="block_t_opt_item">
					5星级食宿
				</div>
				<div class="block_t_opt_item">
					专业导游
				</div>
				<div class="block_t_opt_item">
					个性化推荐
				</div>
			</div>
			<div class="row block_t_util block_t_content">
				<i class="mdi mdi-airplane-takeoff"></i>
				&nbsp;出发：上海 —— 终点：吉隆坡
			</div>
			<div class="row block_t_util block_t_content">
				<i class="mdi mdi-clock"></i>
				&nbsp;出发：2018/09/23 —— 返程：2018/09/30
			</div>
			<div class="row block_t_util block_t_info">
				<div class="col-md-2 block_t_info_item">成团目标:<span class="info_item_HL">40</span>人</div>
				<div class="col-md-8 block_t_info_item">&nbsp;</div>
				<div class="col-md-2 block_t_info_item">已报名:<span class="info_item_HL">30</span>人</div>
			</div>
                <div class="progress">
                    <div id="progressBar" class="progress-bar" role="progressbar"
                         aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
                </div>
			<div class="row block_t_util block_t_btn">
				<div class="col-md-7 block_t_btn_item1">¥<span>2399</span>起</div>
				<div class="col-md-3 block_t_btn_item2">活动截止日期：2018年12月22日</div>
				<div class="col-md-2 block_t_btn_item3">立即报名</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-9">
			<div class="row block_c block_bg">
					<ul class="nav nav-tabs">
					  <li class="nav-item">
					    <a class="nav-link active" href="#pnl_1" data-toggle="tab" role="tab">活动信息</a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link" href="#pnl_2" data-toggle="tab" role="tab">行程安排</a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link" href="#pnl_3" data-toggle="tab" role="tab">考察楼盘</a>
					  </li>
					</ul>
				<div class="col-12 tab-content">
					<div class="tab-pane active" id="pnl_1" role="tabpanel">
						<div class="font_title">活动信息</div>
						<div class="row pane_item">
							<div class="col-2 pane_item_left">活动名称:</div>
							<div class="col-10 pane_item_right">吉隆坡/新山/马六甲 5天4晚考察团 专车接送 个性化推荐看房团</div>
							<div class="col-2 pane_item_left">参团人数:</div>
							<div class="col-10 pane_item_right">30人</div>
							<div class="col-2 pane_item_left">截止时间:</div>
							<div class="col-10 pane_item_right">2018 年 9 月 30 日</div>
							<div class="col-2 pane_item_left">活动概述:</div>
							<div class="col-10 pane_item_right">本次考察团是一个服务于企业级产品的设计体系，基于『确定』和『自然』的设计价值观和模块化的解决方案，让设计者专注于更好的用户体验。Ant Design是一个服务于企业级产品的设计体系，基于『确定』和『自然』的设计价值观和模块化的解决方案，让设计者专注于更好的用户体验。</div>
						</div>
					</div>
					<div class="tab-pane" id="pnl_2" role="tabpanel">
							<div class="font_title">行程安排</div>
					</div>
					<div class="tab-pane" id="pnl_3" role="tabpanel">
							<div class="font_title">考察楼盘</div>
					</div>
				</div>
			</div>
			<div class="row block_c block_bg">
				<div class="font_title">考察团行程安排</div>
			</div>
			<div class="row block_c block_bg">
				<div class="font_title">考察楼盘</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="row block_r1 block_bg">
				<div class="col-12">
					<div class="font_title">活动发起人</div>
				</div>
				<div class="col-12">
					<div class="row block_r1_item">
						<div class="col-12">
							photo
						</div>
						<div class="col-12">
							EQUITY REAL ESTATE
						</div>
						<div class="col-12">
							青岛链家四季景园店
						</div>
					</div>
				</div>
				<div class="col-12">
					<div class="row">
						<div class="col-2 block_r1_item_left">行业: </div>
						<div class="col-10 block_r1_item_right">房地产开发</div>
						<div class="col-2 block_r1_item_left">性质: </div>
						<div class="col-10 block_r1_item_right">私营企业 </div>
						<div class="col-2 block_r1_item_left">规模: </div>
						<div class="col-10 block_r1_item_right">1000人以上</div>
						<div class="col-2 block_r1_item_left">地址:</div>
						<div class="col-10 block_r1_item_right">山东省青岛市市北区合肥路668号四季景园南门链家 </div>
						<div class="col-2 block_r1_item_left">简介:</div>
						<div class="col-10 block_r1_item_right">链家房地产经纪有限公司（以下简称“链家”）成立于2001年，是一家集房产交易服务、资产管理服务为一体以数据驱动的价值链房产服务平台，业务覆盖二手房交易、新房交易、租赁、装修服务等。</div>
					</div>
				</div>
				<div class="col-12">
					<div class="row">
						<div class="col-2 block_r1_item_left"><i class="mdi mdi-phone"></i></div>
						<div class="col-10 block_r1_item_right2">18428386358</div>
						<div class="col-2 block_r1_item_left"><i class="mdi mdi-email"></i></div>
						<div class="col-10 block_r1_item_right2">2252520469@qq.com</div>
						<div class="col-2 block_r1_item_left"><i class="mdi mdi-wechat"></i></div>
						<div class="col-10 block_r1_item_right2">18428386358</div>
					</div>
				</div>
			</div>
			<div class="row block_r2 block_bg">
				<div class="font_title">文档资料</div>
			</div>
		</div>
	</div>
	
</section>

<script type="text/javascript">
</script>