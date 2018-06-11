// 合并列
//基于Jquery写法 
//函数说明：合并指定表格（表格id为_w_table_id）指定列（列数为_w_table_colnum）的相同文本的相邻单元格  
//参数说明：_w_table_id 为需要进行合并单元格的表格的id。如在HTMl中指定表格 id="data" ，此参数应为 #data   
//参数说明：_w_table_colnum 为需要合并单元格的所在列。为数字，从最左边第一列为1开始算起。  
function _w_table_rowspan(_w_table_id, _w_table_colnum) {  
  _w_table_firsttd = "";  
  _w_table_currenttd = "";  
  _w_table_SpanNum = 0;  
  _w_table_Obj = $(_w_table_id + " tr td:nth-child(" + _w_table_colnum + ")");
  _w_table_Obj.each(function (i) {
    if (i == 0) {  
      _w_table_firsttd = $(this);  
      _w_table_SpanNum = 1;  
    } else {  
      _w_table_currenttd = $(this);  
      if (_w_table_firsttd.text() == _w_table_currenttd.text()) {//这边注意不是val（）属性，而是text（）属性  
          _w_table_SpanNum++;  
          _w_table_currenttd.css("visibility","hidden"); //remove();  
          _w_table_firsttd.attr("rowSpan", _w_table_SpanNum);  
          _w_table_firsttd.css({"borderBottom":"none"})  
          _w_table_firsttd.css({"borderLeft":"none"})  
      } else {  
          _w_table_firsttd = $(this);  
          _w_table_SpanNum = 1;  
      }  
    }  
  });  
}  
// _w_table_rowspan("#subtable",1);

// 合并行
//函数说明：合并指定表格（表格id为_w_table_id）指定行（行数为_w_table_rownum）的相同文本的相邻单元格  
//参数说明：_w_table_id 为需要进行合并单元格的表格id。如在HTMl中指定表格 id="data" ，此参数应为 #data   
//参数说明：_w_table_rownum 为需要合并单元格的所在行。其参数形式请参考jQuery中nth-child的参数。  
//如果为数字，则从最左边第一行为1开始算起。  
//"even" 表示偶数行  
//"odd" 表示奇数行  
//"3n+1" 表示的行数为1、4、7、10.......  
//参数说明：_w_table_maxcolnum 为指定行中单元格对应的最大列数，列数大于这个数值的单元格将不进行比较合并。  
//此参数可以为空，为空则指定行的所有单元格要进行比较合并。  
function _w_table_colspan(_w_table_id, _w_table_rownum, _w_table_maxcolnum) {  
  if (_w_table_maxcolnum == void 0) { 
    _w_table_maxcolnum = 0; 
  }  
  _w_table_firsttd = "";  
  _w_table_currenttd = "";  
  _w_table_SpanNum = 0;  
  $(_w_table_id + " tr:nth-child(" + _w_table_rownum + ")").each(function (i) {  
      _w_table_Obj = $(this).children();  
      _w_table_Obj.each(function (i) {  
          if (i == 0) {  
              _w_table_firsttd = $(this);  
              _w_table_SpanNum = 1;  
          } else if ((_w_table_maxcolnum > 0) && (i > _w_table_maxcolnum)) {  
              return "";  
          } else {  
              _w_table_currenttd = $(this);  
              if (_w_table_firsttd.text() == _w_table_currenttd.text()) {  
                  _w_table_SpanNum++;  
                  _w_table_currenttd.hide(); //remove();  
                  _w_table_firsttd.attr("colSpan", _w_table_SpanNum);  
              } else {  
                  _w_table_firsttd = $(this);  
                  _w_table_SpanNum = 1;  
              }  
          }  
      });  
  });  
}  
// _w_table_colspan("#subtable",1,6);

function _w_table_rowspanTh(_w_table_id, _w_table_colnum) {  
  _w_table_firsttd = "";  
  _w_table_currenttd = "";  
  _w_table_SpanNum = 0;  
  _w_table_Obj = $(_w_table_id + " tr th:nth-child(" + _w_table_colnum + ")");
  _w_table_Obj.each(function (i) {
    if (i == 0) {  
      _w_table_firsttd = $(this);  
      _w_table_SpanNum = 1;  
    } else {  
      _w_table_currenttd = $(this);  
      if (_w_table_firsttd.text() == _w_table_currenttd.text()) {//这边注意不是val（）属性，而是text（）属性  
          _w_table_SpanNum++;  
          _w_table_currenttd.hide(); //remove();  
          _w_table_firsttd.attr("rowSpan", _w_table_SpanNum);  
      } else {  
          _w_table_firsttd = $(this);  
          _w_table_SpanNum = 1;  
      }  
    }  
  });  
}

$(function(){
	// $("td").width($("th").width()+1);
	//select change
	$("#stype").on('change',function(){
		if($("#stype option:selected").val() == '1'){
			$("#nameX").text("标的券名称：");
			$("#dateinfoS").attr("placeholder", "委托日期起：");
		}else if($("#stype option:selected").val() == '2' || $("#stype option:selected").val() == '3'){
			$("#nameX").text("合约名称：");
			$("#dateinfoS").attr("placeholder", "交易日期起：");
		}
	});
	
	// 合并行 第一个参数table名，第二个参数需要合并的列数
	_w_table_rowspan("#process-demo-1",1);
	// 合并行 第一个参数tableming，第二个参数需要合并单元格的所在行，第三个参数为指定行中单元格对应的最大列数
    _w_table_colspan("#process-demo-1",1,6);
    
    jeDate("#dateinfoS",{
        //format:"YYYY-MM-DD hh:mm:ss",
        format:"YYYY-MM-DD",
        isTime:false,
        //minDate:"2014-09-19 00:00:00",
        //minDate:"2014-09-19",
        theme:{ bgcolor:"#00A1CB",color:"#ffffff", pnColor:"#00CCFF"},
        minDate: '1976-01-01 23:59:59', //设定最小日期为当前日期
        maxDate: function (that) {
            return jeDate.valText('#dateinfoE') == "" ? jeDate.nowDate({DD:0}) : jeDate.valText('#dateinfoE');
        }, //设定最大日期为当前日期
    })
    
   	jeDate("#dateinfoE",{
        //format:"YYYY-MM-DD hh:mm:ss",
        format:"YYYY-MM-DD",
        isTime:false,
        //minDate:"2014-09-19 00:00:00",
        //minDate:"2014-09-19",
        theme:{ bgcolor:"#00A1CB",color:"#ffffff", pnColor:"#00CCFF"},
        minDate: function (that) {
            return jeDate.valText('#dateinfoS') == "" ? '1976-01-01 23:59:59' : jeDate.valText('#dateinfoS') ;
        }, //设定最小日期为当前日期
        maxDate: jeDate.nowDate({DD:0}), //设定最大日期为当前日期
    })
    //selData();
})

//查询
function selData() {
	var stype = "1"
	if($("#stype option:selected").val() != ''){
		stype = $("#stype option:selected").val();
	}	
	
	var paramswx = {
			stype: stype, 
			sname: $("#sname").val(), 
			dateinfoS: $("#dateinfoS").val(), 
			dateinfoE: $("#dateinfoE").val()
	};	
	//获取数据
	$.ajax({
		type: "POST",
		url: baseURL + "generator/newspaper/list",
		contentType: "application/json",
		data: JSON.stringify(paramswx),
		async: false,
		success: function(r){
			if(r.code == 0){
				$("#stype").val(r.newspaperEntity.stype);
				$("#sname").val(r.newspaperEntity.sname);
				$("#dateinfoS").val(r.newspaperEntity.dateinfoS);
				$("#dateinfoE").val(r.newspaperEntity.dateinfoE);
				$("#trName").html('');
				var str = "";
				var strTd = "";
				if(r.newspaperEntity.stype == "1"){//银行间每日债券借贷
					str = "<tr>" +
							  "<th>委托日期</th>" +
							  "<th>标的券代码</th>" + 
							  "<th>标的券名称</th>" + 
					          "<th>加权平均费率(%)</th>" +
					          "<th>成交量(亿元)</th>" +
				          "</tr>";
					$("#trName").html(str);
					
					$("#tdName").html('');
					$.each(r.newspaperList,function(index, item){   
					    strTd += "<tr>" +
						    		"<td>" + item.trddate + "</td>" +
						    		"<td>" + item.uICode + "</td>" +
						    		"<td>" + item.bName + "</td>" +
						    		"<td>" + item.weightedAverageRate + "</td>" +
						    		"<td>" + item.volume + "</td>" +
					    		"</tr>";
					})  
					$("#tdName").html(strTd);
					
					//设置样式
//					$("#info #process-demo-1 th").css("width", "170px");
//					$("#info #process-demo-1 th").css("min-width", "170px");
//					$("#info #process-demo-1 th").css("max-width", "170px");
//					$("#info td").css("width", "170px");
//					$("#info td").css("min-width", "170px");
//					$("#info td").css("max-width", "170px");
					
					$("#nameX").text("标的券名称：");
					$("#labelS").text("委托日期起：");
					$("#labelE").text("委托日期止：");
				}else if(r.newspaperEntity.stype == "2"){//国债期货当日结算价
					str = "<tr>" +
							  "<th>交易日</th>" +
							  "<th>合约</th>" + 
							  "<th>开盘价</th>" + 
					          "<th>最高价</th>" +
					          "<th>最低价</th>" +
					          "<th>收盘价</th>" +
					          "<th>结算价</th>" +
					          "<th>成交量</th>" +
					          "<th>持仓量</th>" +
					          "<th>涨跌(元)-涨跌幅%</th>" +
					          "<th>最后交易日期</th>" +
				          "</tr>";
					$("#trName").html(str);
					
					$("#tdName").html('');
					$.each(r.newspaperList,function(index, item){   
					    strTd += "<tr>" +
						    		"<td>" + item.tradeDt + "</td>" +
						    		"<td>" + item.sInfoWindcode + "</td>" +
						    		"<td>" + item.sDqOpen + "</td>" +
						    		"<td>" + item.sDqHigh + "</td>" +
						    		"<td>" + item.sDqLow + "</td>" +
						    		"<td>" + item.sDqClose + "</td>" +
						    		"<td>" + item.sDqSettle + "</td>" +
						    		"<td>" + item.sDqVolume + "</td>" +
						    		"<td>" + item.sDqOi + "</td>" ;
				    	if(parseFloat(item.sDqChange) > 0){
				    	   strTd += "<td><font color='#ff0000'>" + item.sDqChange + "</font></td>";
				    	}else if(parseFloat(item.sDqChange) < 0){
				    	   strTd += "<td><font color='#008000'>" + item.sDqChange + "</font></td>";
				    	}else{
				    	   strTd += "<td>" + item.sDqChange + "</td>";
				    	}
				    	   strTd += "<td style='text-align:left;'>" + item.sInfoDelistdate + "</td>" +
					    		  "</tr>";
					})  
					$("#tdName").html(strTd);
					
					//设置样式
//					$("#info #process-demo-1 th").css("width", "90px");
//					$("#info #process-demo-1 th").css("min-width", "90px");
//					$("#info #process-demo-1 th").css("max-width", "90px");
//					$("#info td").css("width", "90px");
//					$("#info td").css("min-width", "90px");
//					$("#info td").css("max-width", "90px");
					
					$("#nameX").text("合约名称：");
					$("#labelS").text("交易日期起：");
					$("#labelE").text("交易日期止：");
				}else if(r.newspaperEntity.stype == "3"){//国债期货品种排名
					str = "<tr>" +
							  "<th></th>" +
							  "<th></th>" +
							  "<th>成交量排名</th>" + 
							  "<th>成交量排名</th>" + 
							  "<th>成交量排名</th>" + 
							  "<th>成交量排名</th>" + 
							  "<th>持买量排名</th>" + 
							  "<th>持买量排名</th>" + 
							  "<th>持买量排名</th>" + 
							  "<th>持买量排名</th>" + 
							  "<th>持卖量排名</th>" + 
							  "<th>持卖量排名</th>" + 
							  "<th>持卖量排名</th>" + 
							  "<th>持卖量排名</th>" + 
						  "</tr>" +
						  "<tr>" +
						  	  "<th>日期</th>" +
							  "<th>合约</th>" + 
							  "<th>名次</th>" + 
							  "<th>会员简称</th>" + 
							  "<th>成交量</th>" + 
							  "<th>比上交易日增减</th>" + 
							  "<th>名次</th>" + 
							  "<th>会员简称</th>" + 
							  "<th>持买单量</th>" + 
							  "<th>比上交易日增减</th>" + 
							  "<th>名次</th>" + 
							  "<th>会员简称</th>" + 
							  "<th>持卖单量</th>" + 
							  "<th>比上交易日增减</th>" + 
						  "</tr>";
					$("#trName").html(str);
				    //合并列
					_w_table_colspan("#process-demo-1",1,13);
					
					$("#tdName").html('');
					$.each(r.newspaperList,function(index, item){   
					    strTd += "<tr>" +
						    		"<td style='text-align:left;'>" + item.tradeDt + "</td>" +
						    		"<td>" + item.sInfoWindcode + "</td>" +
						    		"<td>" + item.fsInfoRank + "</td>" +
						    		"<td>" + item.cJmem + "</td>" +
						    		"<td>" + item.cJl + "</td>";
			    		if(parseFloat(item.cJlbh) > 0){
			    		   strTd += "<td><font color='#ff0000'>" + item.cJlbh + "<font></td>"
			    		}else if(parseFloat(item.cJlbh) < 0){
			    		   strTd += "<td><font color='#008000'>" + item.cJlbh + "<font></td>"
			    		}else{
			    		   strTd += "<td>" + item.cJlbh + "</td>"
			    		}
			    		   strTd += "<td>" + item.fsInfoRank + "</td>" +
						    		"<td>" + item.cBmem + "</td>" +
						    		"<td>" + item.cBl + "</td>";
				    		if(parseFloat(item.cBlbh) > 0){
				    		   strTd += "<td><font color='#ff0000'>" + item.cBlbh + "<font></td>"
				    		}else if(parseFloat(item.cBlbh) < 0){
				    		   strTd += "<td><font color='#008000'>" + item.cBlbh + "<font></td>"
				    		}else{
				    		   strTd += "<td>" + item.cBlbh + "</td>"
				    		}
				    	   strTd += "<td>" + item.fsInfoRank + "</td>" +
						    		"<td>" + item.cSmem + "</td>" +
						    		"<td>" + item.cSl + "</td>";
				    	   if(parseFloat(item.cSlbh) > 0){
				    		   strTd += "<td><font color='#ff0000'>" + item.cSlbh + "<font></td>"
				    	   }else if(parseFloat(item.cSlbh) < 0){
				    		   strTd += "<td><font color='#008000'>" + item.cSlbh + "<font></td>"
				    	   }else{
				    		   strTd += "<td>" + item.cSlbh + "</td>"
				    	   }
						   strTd += "</tr>";
					});  
					$("#tdName").html(strTd);
					
					$("#nameX").text("合约名称：");
					$("#labelS").text("交易日期起：");
					$("#labelE").text("交易日期止：");
				}
			}else{
				alert(r.msg);
			}
		}
	});
}