function submits(){if($("#eidtForm").validationEngine('validate')){$("#tj,#cz").hide();return true;}else{$("#tj,#cz").show();return false;}};$(document).ready(function(){$("#tj").bind({click:function(){submits();}});$("#tj,#cz").bind({mouseover:function(){$(this).css({"background-color":"red","cursor":"pointer"});},  mouseout:function(){$(this).css({"background-color":"#FFFFFF","cursor":""});}});$("#credate").datepicker({changeMonth: true,/**月份显示成select形式*/changeYear: true,/**年份显示成select形式*/showButtonPanel:true/**显示按钮面板*/});});
