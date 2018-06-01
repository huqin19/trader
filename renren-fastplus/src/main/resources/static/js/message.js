var setting = {
	view : {
		selectedMulti : false
	},
	check : {
		enable : true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onCheck : onCheck
	}
};

var zNodes = [ {
	id : 1,
	pId : 0,
	name : "随意勾选 1",
	open : true
}, {
	id : 11,
	pId : 1,
	name : "随意勾选 1-1"
}, {
	id : 12,
	pId : 1,
	name : "随意勾选  1-2",
	open : true
}, {
	id : 12,
	pId : 1,
	name : "随意勾选  1-2",
	open : true
}, {
	id : 121,
	pId : 12,
	name : "随意勾选 1-2-1",
	checked : false
}, {
	id : 122,
	pId : 12,
	name : "随意勾选 1-2-2"
}, {
	id : 123,
	pId : 12,
	name : "随意勾选 1-2-3"
}, {
	id : 13,
	pId : 1,
	name : "随意勾选 1-3"
}, {
	id : 2,
	pId : 0,
	name : "随意勾选 2",
	open : true
}, {
	id : 21,
	pId : 2,
	name : "随意勾选 2-1"
}, {
	id : 22,
	pId : 2,
	name : "随意勾选 2-2",
	open : true
}, {
	id : 221,
	pId : 22,
	name : "随意勾选 2-2-1",
	checked : false
}, {
	id : 222,
	pId : 22,
	name : "随意勾选 2-2-2"
}, {
	id : 223,
	pId : 22,
	name : "随意勾选 2-2-3"
}, {
	id : 23,
	pId : 2,
	name : "随意勾选 2-3",
	checked : false
} ];

var clearFlag = false;
function onCheck(e, treeId, treeNode) {
	count();
	if (clearFlag) {
		clearCheckedOldNodes();
	}
}
function clearCheckedOldNodes() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getChangeCheckedNodes();
	for (var i = 0, l = nodes.length; i < l; i++) {
		nodes[i].checkedOld = nodes[i].checked;
	}
}
var treeName = [];
var newtreeName = [];
function count() {
	newtreeName = [];
	treeName = [];
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), checkCount = zTree
			.getCheckedNodes(true).length, nocheckCount = zTree
			.getCheckedNodes(false).length, changeCount = zTree
			.getChangeCheckedNodes().length;
	$("#checkCount").text(checkCount);
	$("#nocheckCount").text(nocheckCount);
	$("#changeCount").text(changeCount);
	for (var i = 0; i < zTree.getCheckedNodes(true).length; i++) {
		if (zTree.getCheckedNodes(true)[i].isleaf == '1') {
			treeName.push(zTree.getCheckedNodes(true)[i].id);
		}
	}
	for (var i = 0; i < treeName.length; i++) {
		if (newtreeName.indexOf(treeName[i]) == -1) {
			newtreeName.push(treeName[i]);
		}
	}
}

function createTree() {
	// 获取数据
	$.ajax({
		type : "POST",
		url : baseURL + "generator/weixin/list",
		contentType : "application/json",
		data : JSON.stringify("{}"),
		async : false,
		success : function(r) {
			if (r.code == 0) {
				zNodes = r.weixinList;
				// 遍历list
				// $.each(r.weixinList,function(index, item){
				// item.id;
				// })
			} else {
				alert(r.msg);
			}
		}
	});
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	count();
	clearFlag = $("#last").attr("checked");
}
createTree();
$("#init").bind("change", createTree);
$("#last").bind("change", createTree);

// 左边选中
var titleArr = []
$(".check").click(function() {
	$(this).toggleClass("active");
	var val = $(this).next().text();
	if ($(this).hasClass("active")) {
		titleArr.push(val);
	} else {
		var index = titleArr.indexOf(val);
		if (index > -1) {
			titleArr.splice(index, 1);
		}
	}
});

// 提交
$("button").click(function() {
	console.log(titleArr)
	console.log(newtreeName)
	if (titleArr.length == 0) {
		alert("请选择推送消息的类型");
		return;
	} else if (newtreeName.length == 0) {
		alert("请选择接收人员");
		return;
	}

	var paramswx = {
		titleArr : titleArr,
		newtreeName : newtreeName
	};

	// 推送消息
	$.ajax({
		type : "POST",
		url : baseURL + "generator/weixin/send",
		contentType : "application/json",
		data : JSON.stringify(paramswx),
		async : false,
		success : function(r) {
			if (r.code == 0) {
				alert(r.resultDesc.replace(/\n|\r\n/g, "<br/>"));
			} else {
				alert(r.msg);
			}
		}
	});
})
