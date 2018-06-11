	var treeName = []; //人员id
	var newtreeName = [];//去重
	var treeName2 = []; //日报类型id
	var newtreeName2 = [];//去重
	//左侧选中内容添加至右侧
	function getAllChildrenNodesToRight(seltype, ischeck, treeNameparam, treeNode){  
	      if (treeNode.isParent) {  
	        var childrenNodes = treeNode.children;  
	        if (childrenNodes) {  
	            for (var k = 0; k < childrenNodes.length; k++) {  
	            	if(childrenNodes[k].isleaf == '1'){//叶子
	            		if(ischeck){//选中
	            			treeNameparam.push(childrenNodes[k].id);
	        				//添加到右侧
	            			if(seltype == 1){//选择人员
	            				$("#personnel").append('<div class="personnel-name" personid=' + childrenNodes[k].id + '>' + childrenNodes[k].name + '<span></span></div>');
	            			}else if(seltype == 2){//选择日报类型
	            				$("#sheets").append('<div class="type-name" sheetid=' + childrenNodes[k].id + '>' + childrenNodes[k].name + '<span></span></div>');
	            			}
	            		}else{//取消选中
	        				var i = treeNameparam.length;
	        				while(i--){
	        					if(treeNameparam[i] == childrenNodes[k].id){
	        						treeNameparam.splice(i,1);
	        						if(seltype == 1){//选择人员
	        							$("div[personid='"+ childrenNodes[k].id +"']").remove();
	        						}else if(seltype == 2){//选择日报类型
	        							$("div[sheetid='"+ childrenNodes[k].id +"']").remove();
	        						}
	        					}
	        				}
	            		}
	            	}else{
	            		getAllChildrenNodesToRight(seltype, ischeck, treeNameparam, childrenNodes[k]); 
	            	}
	            }  
	        }  
	    }else{
			if(treeNode.isleaf == '1'){//叶子节点
				if(treeNode.checked == true && treeNode.checked != treeNode.checkedOld){//选中
					treeNameparam.push(treeNode.id);
					//添加到右侧
					if(seltype == 1){//选择人员
						$("#personnel").append('<div class="personnel-name" personid=' + treeNode.id + '>' + treeNode.name + '<span></span></div>');
					}else if(seltype == 2){//选择日报类型
        				$("#sheets").append('<div class="type-name" sheetid=' + treeNode.id + '>' + treeNode.name + '<span></span></div>');
					}
				}else if(treeNode.checked == false && treeNode.checked != treeNode.checkedOld){//取消选中
					var i = treeNameparam.length;
					while(i--){
						if(treeNameparam[i] == treeNode.id){
							treeNameparam.splice(i,1);
							if(seltype == 1){//选择人员
								$("div[personid='"+ treeNode.id +"']").remove();
							}else if(seltype == 2){//选择日报类型
								$("div[sheetid='"+ treeNode.id +"']").remove();
							}
						}
					}
				}
			}
	    }  
	    return treeNameparam;  
	}

	// ---------  start 部门人员  ------------------
	var zNodes=[{}], zNodes2=[{}];
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
	//部门人员ztree 点击事件
	function onCheck(e, treeId, treeNode) {
		var ischeck = false;
		if(treeNode.checked == true && treeNode.checked != treeNode.checkedOld){//选中
			ischeck = true;
		}
		if(treeNode.checked != treeNode.checkedOld){
			treeName = getAllChildrenNodesToRight(1, ischeck, treeName, treeNode);
		}
		clearCheckedOldNodes();
		//console.log("treeName: " + treeName + "," + treeName.length);
	}
	//防止二次点击无效
	function clearCheckedOldNodes() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		nodes = zTree.getChangeCheckedNodes(); //获取勾选状态被改变的节点集合
		for (var i=0, l=nodes.length; i<l; i++) {
			nodes[i].checkedOld = nodes[i].checked;
		}
	}
	// ---------  end 部门人员  ------------------
	
	// ---------  start 日报类型  ------------------
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

	//日报类型ztree 点击事件
	function onCheck2(e, treeId, treeNode) {
		var ischeck = false;
		if(treeNode.checked == true && treeNode.checked != treeNode.checkedOld){//选中
			ischeck = true;
		}
		if(treeNode.checked != treeNode.checkedOld){
			treeName2 = getAllChildrenNodesToRight(2, ischeck, treeName2, treeNode);
		}
		clearCheckedOldNodes2();
		console.log("treeName2: " + treeName2 + "," + treeName2.length);
	}
	//防止二次点击无效
	function clearCheckedOldNodes2() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo2");
		nodes = zTree.getChangeCheckedNodes();
		for (var i=0, l=nodes.length; i<l; i++) {
			nodes[i].checkedOld = nodes[i].checked;
		}
	}
	//---------  end 日报类型  ------------------

	//生成ztree
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
	   treeName = [];
	   newtreeName = [];
	   treeName2 = [];
	   newtreeName2 = [];
	}
	createTree(); 

	// 提交
	$(".send-message").click(function() {
		newtreeName = [];
		newtreeName2 = [];
		//去重
		for (var i = 0; i < treeName.length; i++) {
			if (newtreeName.indexOf(treeName[i]) == -1) {
				newtreeName.push(treeName[i]);
			}
		}
		for(var i=0;i<treeName2.length;i++){
			if(newtreeName2.indexOf(treeName2[i])==-1){
				newtreeName2.push(treeName2[i]);
			}
		}
		console.log(newtreeName);
		console.log(newtreeName2);
		
		var sheetDate = $("#sheetDate").val();
		if(sheetDate.length == 0){
			alert("请选择推送日报的日期");
			return;
		}else if (newtreeName2.length == 0) {
			alert("请选择推送的日报类型");
			return;
		} else if (newtreeName.length == 0) {
			alert("请选择接收人员");
			return;
		}
		var paramswx = {
			titleArr : newtreeName2,
			newtreeName : newtreeName,
			sheetDate : sheetDate
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
	
	// 消息定时推送-提交
	$(".submit-message").click(function() {
		newtreeName = [];
		newtreeName2 = [];
		//去重
		for (var i = 0; i < treeName.length; i++) {
			if (newtreeName.indexOf(treeName[i]) == -1) {
				newtreeName.push(treeName[i]);
			}
		}
		for(var i=0;i<treeName2.length;i++){
			if(newtreeName2.indexOf(treeName2[i])==-1){
				newtreeName2.push(treeName2[i]);
			}
		}
		console.log(newtreeName);
		console.log(newtreeName2);
		
		var beanName = $("#beanName").val();
		var methodName = $("#methodName").val();
		var appId = $("#appId").val();
		var params = $("#params").val();
		var cronExpression = $("#cronExpression").val();
		var remark = $("#remark").val();
		
		if(beanName.length == 0){
			alert("请填写bean名称");
			return;
		}else if (methodName.length == 0) {
			alert("请填写方法名称");
			return;
		}else if (cronExpression.length == 0) {
			alert("请填写定时表达式");
			return;
		}else if (newtreeName2.length == 0) {
			alert("请选择推送的日报类型");
			return;
		} else if (newtreeName.length == 0) {
			alert("请选择接收人员");
			return;
		}
		var paramswx = {
			titleArr : newtreeName2,
			newtreeName : newtreeName,
			beanName : beanName,
			methodName : methodName,
			appId : appId,
			params : params,
			cronExpression : cronExpression,
			remark : remark
		};
		// 消息定时推送-提交
		$.ajax({
			type : "POST",
			url : baseURL + "generator/weixin/submitMessage",
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
	
	$(".send-message1").click(function() {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		treeObj.checkNode(treeObj.getNodeByParam("id", "xinzhi_test", null), false, true);
	});