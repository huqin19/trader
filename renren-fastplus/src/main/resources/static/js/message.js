	var zNodes2=[{}],zNodes=[{}];

	var setting = {
	    view: {
            selectedMulti: false
        },
        check: {
        	enable: true
        },
        data: {
        	simpleData: {
        		enable: true
        	}
        },
        callback: {
        	onCheck: onCheck
        }
	};

	function onCheck(e, treeId, treeNode) {
		count();
		clearCheckedOldNodes();
	}
	
	function clearCheckedOldNodes() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getChangeCheckedNodes(); //获取勾选状态被改变的节点集合
		for (var i=0, l=nodes.length; i<l; i++) {
			nodes[i].checkedOld = nodes[i].checked;
		}
	}
	var treeName = [];
	var newtreeName = [];//去重
	function count() {
		treeName = [];
		newtreeName = [];
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
		//获取数据
	 	$.ajax({
	 		type: "POST",
	 		url: baseURL + "generator/weixin/list",
	 		contentType: "application/json",
	 		data: JSON.stringify("{}"),
	 		async: false,
	 		success: function(r){
	 			if(r.code == 0){
	 				zNodes = r.weixinList;
	 				zNodes2 = r.weixinSheetList;
	 				//遍历list
	 				//$.each(r.weixinList,function(index, item){   
	 				//    item.id;  
	 				//})  
	 			}else{
	 				alert(r.msg);
	 			}
	 		}
	 	});
	   $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	   $.fn.zTree.init($("#treeDemo2"), setting2, zNodes2);
	   count();
	   count2();
	}
	createTree(); 
	createTree2();

	// 提交
	$(".send-message").click(function() {
		console.log(newtreeName);
		console.log(newtreeName2);
		if (newtreeName2.length == 0) {
			alert("请选择推送的日报类型");
			return;
		} else if (newtreeName.length == 0) {
			alert("请选择接收人员");
			return;
		}
		var paramswx = {
			titleArr : newtreeName2,
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

//2
var setting2 = {
  view: {
    selectedMulti: false
  },
  check: {
    enable: true
  },
  data: {
    simpleData: {
      enable: true
    }
  },
  callback: {
    onCheck: onCheck2
  }
};

function onCheck2(e, treeId, treeNode) {
	count2();
	clearCheckedOldNodes2();
}
function clearCheckedOldNodes2() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo2"),
	nodes = zTree.getChangeCheckedNodes();
	for (var i=0, l=nodes.length; i<l; i++) {
		nodes[i].checkedOld = nodes[i].checked;
	}
}

var treeName2 = [];
var newtreeName2 = [];
function count2() {
	treeName2 = [];
	newtreeName2 = [];
	var zTree = $.fn.zTree.getZTreeObj("treeDemo2"),
	checkCount = zTree.getCheckedNodes(true).length,
	nocheckCount = zTree.getCheckedNodes(false).length,
	changeCount = zTree.getChangeCheckedNodes().length;
	$("#checkCount").text(checkCount);
	$("#nocheckCount").text(nocheckCount);
	$("#changeCount").text(changeCount);
	for(var i=0;i<zTree.getCheckedNodes(true).length;i++){
		treeName2.push(zTree.getCheckedNodes(true)[i].name);
	}
	for(var i=0;i<treeName2.length;i++){
		if(newtreeName2.indexOf(treeName2[i])==-1){
			newtreeName2.push(treeName2[i]);
		}
	}
}

function createTree2() {
  $.fn.zTree.init($("#treeDemo2"), setting2, zNodes2);
  count2();
}
createTree2();