<%@ page contentType="text/html; charset=UTF-8"%>
<section class="content">
<div class="box-body"></div>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">聯絡我們</h3>
	</div>
	<div class="box-body no-padding">
		<table class="table table-striped" id="tblMain">
			<thead>
				<tr>
					<th>姓名</th>
					<td>${userName}</td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td><a href="mailto:${userEmail}?Subject=test">${userEmail}</a></td>
				</tr>
				<tr>
					<th>Wechat</th>
					<td>${userWeChat}</td>
				</tr>
				<tr>
					<th>電話</th>
					<td>${userPhone}</td>
				</tr>
			</thead>
		</table>
	</div>
</div>

</section>