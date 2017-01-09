function submits(){
	if($("#eidtForm").validationEngine('validate')){
		$("#tj,#cz").hide();return true;
	}else{
		$("#tj,#cz").show();return false;
	}
}
$(document).ready(function(){
	$("#tj").bind({click:function(){
		return submits();
	}});
	$("#tj,#cz").bind({
		mouseover:function(){
			$(this).css({"background-color":"red","cursor":"pointer"});
		},  mouseout:function(){
			$(this).css({"background-color":"#FFFFFF","cursor":""});
		}
	});
	$("#startdate ,#enddate").datepicker({
		changeMonth: true,/**月份显示成select形式*/
		changeYear: true,/**年份显示成select形式*/
		showButtonPanel:true/**显示按钮面板*/
	});
	
	$("#price").bind({blur:function(){
		genaratePrice();
	}});
});

function genaratePrice(){
	var date1=$("#startdate").datepicker("getDate");  //开始时间
	if(!date1)return;
	var date2=$("#enddate").datepicker("getDate");    //结束时间
	if(!date2)return;
	var date3=date2.getTime()-date1.getTime()  //时间差的毫秒数
	var days=Math.floor(date3/(24*3600*1000))
	if(days>0){
		var sdate=date1;
		var dp="";
		for(var i=0;i<=days;i++){
			dp+=$.datepicker.formatDate('yy-mm-dd', sdate)+":"+$("#price").val()+"\n";
			sdate.setDate(sdate.getDate() + 1);
		}
		$("#dprice").val(dp);
	}
}