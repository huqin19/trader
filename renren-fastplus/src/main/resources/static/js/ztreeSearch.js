	/**
	 * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
	 * @param treeId
	 * @param searchConditionId 文本框的id
	 */
	function search_ztree(treeId, searchConditionId) {
		searchByFlag_ztree(treeId, searchConditionId, "");
	}

   /**
	 * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
	 * @param treeId
	 * @param searchConditionId     搜索条件Id
	 * @param flag                  需要高亮显示的节点标识
	 */
	function searchByFlag_ztree(treeId, searchConditionId, flag) {
		//<1>先把全部节点name、highlight还原
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var treeNodes = treeObj.transformToArray(treeObj.getNodes());
		for(var i = 0; i < treeNodes.length; i++) {
			treeNodes[i].highlight = false;
			if(treeNodes[i].name.indexOf("<span") != -1){
				treeNodes[i].name = (treeNodes[i].name.replace("<span style='color:red'>","").replace("</span>",""));
			}
			treeObj.updateNode(treeNodes[i]);
		}
		
		//<2>.搜索条件
		var searchCondition = $('#' + searchConditionId).val();
		//<3>.得到模糊匹配搜索条件的节点数组集合
		var highlightNodes = new Array();
		if(searchCondition != "") {
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			highlightNodes = treeObj.getNodesByParamFuzzy("name", searchCondition, null);
		}
		//<4>.高亮显示并展示【指定节点s】
		highlightAndExpand_ztree(treeId, highlightNodes, searchCondition, flag);
		if(0 < highlightNodes.length){
			treeObj.selectNode(highlightNodes[0]);
		}
	}

   /**
	 * 高亮显示并展示【指定节点s】
	 * @param treeId
	 * @param highlightNodes 需要高亮显示的节点数组
	 * @param flag           需要高亮显示的节点标识
	 */
	function highlightAndExpand_ztree(treeId, highlightNodes, tx, flag) {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		//<1>. 先把全部节点更新为普通样式
		/*
		var treeNodes = treeObj.transformToArray(treeObj.getNodes());
		for(var i = 0; i < treeNodes.length; i++) {
			treeNodes[i].highlight = false;
			if(treeNodes[i].name.indexOf("<span") != -1){
				treeNodes[i].name = (treeNodes[i].name.replace("<span style='color:red'>","").replace("</span>",""));
			}
			treeObj.updateNode(treeNodes[i]);
		}
		*/
		//<2>.收起树, 只展开根节点下的一级节点
		close_ztree(treeId);
		//<3>.把指定节点的样式更新为高亮显示，并展开
		if(highlightNodes != null) {
			for(var i = 0; i < highlightNodes.length; i++) {
				if(flag != null && flag != "") {
					if(highlightNodes[i].flag == flag) {
						//高亮显示节点，并展开
						highlightNodes[i].highlight = true;
						treeObj.updateNode(highlightNodes[i]);
						//高亮显示节点的父节点的父节点....直到根节点，并展示
						var parentNode = highlightNodes[i].getParentNode();
						var parentNodes = getParentNodes_ztree(treeId, parentNode);
						treeObj.setting.view.expandSpeed = "";
						treeObj.expandNode(parentNodes, true, false, true);
						treeObj.expandNode(parentNode, true, false, true);
						treeObj.setting.view.expandSpeed = "fast";
					}
				} else {
					//高亮显示节点，并展开
					//highlightNodes[i].checked = true;
					var t = highlightNodes[i].name;
					if(t.indexOf("<span") == -1){
						t = t.replace(eval("/" + tx + "/gi"), "<span style='color:red'>" + tx + "</span>");
					}
					highlightNodes[i].name = t;
					treeObj.updateNode(highlightNodes[i]);
					//高亮显示节点的父节点的父节点....直到根节点，并展示
					var parentNode = highlightNodes[i].getParentNode();
					var parentNodes = getParentNodes_ztree(treeId, parentNode);
					treeObj.setting.view.expandSpeed = "";
					treeObj.expandNode(parentNodes, true, false, true);
					treeObj.expandNode(parentNode, true, false, true);
					treeObj.setting.view.expandSpeed = "fast";
				}
			}
		}
	}

   /**
	 * 递归得到指定节点的父节点的父节点....直到根节点
	 */
	function getParentNodes_ztree(treeId, node) {
		if(node != null) {
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			var parentNode = node.getParentNode();
			return getParentNodes_ztree(treeId, parentNode);
		} else {
			return node;
		}
	}
	
    /**
     * 收起树：只展开根节点下的一级节点
     * @param treeId
     */
    function close_ztree(treeId) {
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        var nodes = treeObj.transformToArray(treeObj.getNodes());
        var nodeLength = nodes.length;
        for(var i = 0; i < nodeLength; i++) {
            if(nodes[i].id == '1') {
                //根节点：展开
            	treeObj.setting.view.expandSpeed = "";
                treeObj.expandNode(nodes[i], true, true, false);
                treeObj.setting.view.expandSpeed = "fast";
            } else {
                //非根节点：收起
            	treeObj.setting.view.expandSpeed = "";
                treeObj.expandNode(nodes[i], false, true, false);
                treeObj.setting.view.expandSpeed = "fast";
            }
        }
    }
