//与后台调用的统一方法
function ajax(url, data, callback) {
	$.ajax({
		url : url,
		type : 'POST',
		data : data,
		timeout : 30000,
		beforeSend : function() {
			$.jBox.tip("正在处理中...", 'loading');
		},
		success : function(data, response, status) {
			$.jBox.tip("处理完成...", 'success');
			if (data == null)
				return;
			var obj = eval('(' + data + ')');
			if (obj.results == '0') {
				$.jBox.error('提示', '操作失败');
			} else if (obj.results == '1' || obj.results == '2')
				callback(obj);
			else {
				$.jBox.error('提示', '未知错误，请联系系统管理员');
			}
		},
		error : function(request, info, obj) {
			$.jBox.tip("系统异常..." + info, 'success');
		}
	});
}

function editXml() {
	var data = {
		"msg" : $('#xmlday').val()
	};
	ajax("qlyline!xmlEdit.do", data, function(o) {
	});
}