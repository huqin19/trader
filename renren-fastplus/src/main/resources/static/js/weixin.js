////图表1
//var myChart1 = echarts.init(document.getElementById('main1')); 
//option1 = {
//  title: {
//    text: 'FR007利率互换收盘曲线',
//    x: 'center',
//    y: 'top',
//    padding: 5,
//    textStyle: {
//      fontSize: 20,
//    }
//  },
//  tooltip : {
//    show: true,
//    trigger: 'axis'
//  },
//  toolbox: {
//    show : true,
//    feature : {
//        mark : {show: true},
//        dataView : {show: true, readOnly: false},
//        magicType: {show: true, type: ['line', 'bar']},
//        restore : {show: true},
//        saveAsImage : {show: true}
//    }
//  },
//  calculable : true,
//  legend: {
//    data:['收盘利率（%）','涨跌（BP）'],
//    x: 'center',
//    y: 'bottom',
//    borderColor: '#3b3b3b',
//    borderWidth: 2,
//    padding: 5,
//    itemGap: 10,
//    itemWidth: 10,
//  },
//  axis: {
//    axisline: {
//      // show:false
//    } 
//  },
//  xAxis : [
//    {
//      show : true,
//      type : 'category',
//      data : ['1M','3M','6M','9M','1Y','2Y','3Y','4Y','5Y','7Y','10Y'],
//      logPositive: 'value',
//      axisLine: {
//       show: false
//      },
//      axisTick: {
//        show: false
//      },
//      splitLine: {
//        show:false
//      }
//    }
//  ],
//  // 收盘利率折线蓝色，涨跌红色柱状
//  yAxis : [
//    //蓝色 
//    {
//      type : 'value',
//      name : '收盘利率（%）',
//      axisLabel : {
//        formatter: function(value,index){
//          return value.toFixed(4)
//        }
//      },
//      logPositive: 'value',
//      min : 3.0000,
//      max : 4.0000,
//      splitNumber: 5,   //左侧y分成5段,
//      axisLine: {
//       show: false
//      },
//
//    },
//    // 红色
//    {
//      type : 'value',
//      name : '涨跌（BP）',
//      axisLabel : {
//        formatter: function(value,index){
//          return value.toFixed(2)
//        }
//      },
//      min : -6.00,
//      max : 5.00,
//      splitNumber: 5,    //右侧y分成5段
//      axisLine: {
//       show: false
//      },
//    }
//  ],
// 
//  series : [
//    {
//      name:'涨跌（BP）',
//      type:'bar',
//      yAxisIndex: 1,
//      data:[-5.22, -1.13, -0.25, 0.38, 0.85, 1.75, 2.36, 1.88, 2.5, 4.46, 3.0],
//      barWidth: 30,
//      itemStyle: {
//          normal: {
//              color: '#c1002f',
//         }
//      },
//    },
//    {
//      barWidth: 30,
//      barGap: '10%',
//      barCategoryGap: '10%',
//      name:'收盘利率（%）',
//      type:'line',
//      data:[3.0573, 3.0850, 3.1050, 3.1650, 3.2063, 3.3425, 3.4486, 3.5438, 3.6075, 3.7058, 3.7825],
//      itemStyle: {
//          normal: {
//              color: '#3E98C5',
//         }
//      },
//    },
//  ]
//};
//
////图表2
//myChart1.setOption(option1); 
//var myChart2 = echarts.init(document.getElementById('main2')); 
//option2 = {
//  title: {
//    text: 'Shibor3M利率互换收盘曲线',
//    x: 'center',
//    y: 'top',
//    padding: 5,
//    textStyle: {
//      fontSize: 20,
//    }
//  },
//  tooltip : {
//    trigger: 'axis'
//  },
//  toolbox: {
//    show : true,
//    feature : {
//      mark : {show: true},
//      dataView : {show: true, readOnly: false},
//      magicType: {show: true, type: ['line', 'bar']},
//      restore : {show: true},
//      saveAsImage : {show: true}
//    }
//  },
//  calculable : true,
//  legend: {
//    data:['收盘利率（%）','涨跌（BP）'],
//    x: 'center',
//    y: 'bottom',
//    borderColor: '#3b3b3b',
//    borderWidth: 2,
//    padding: 5,
//    itemGap: 10,
//    itemWidth: 10,
//  },
//  xAxis : [
//    {
//      type : 'category',
//      data : ['1M','3M','6M','9M','1Y','2Y','3Y','4Y','5Y','7Y','10Y'],
//      axisLine: {
//        show: false
//      },
//      axisTick: {
//        show: false
//      },
//      splitLine: {
//        show:false
//      }
//    },
//   
//  ],
//  yAxis : [
//    {
//      type : 'value',
//      name : '收盘利率（%）',
//      axisLabel : {
//        formatter: function(value,index){
//          return value.toFixed(4)
//        }
//      },
//      min : 4.0000,
//      max : 5.0000,
//      splitNumber: 5,   //左侧侧y分成5段
//      // splitLine: {
//      //   show:false
//      // }
//      axisLine: {
//       show: false
//      },
//    },
//    {
//      type : 'value',
//      name : '涨跌（BP）',
//      axisLabel : {
//          formatter: function(value,index){
//            return value.toFixed(2)
//          }
//      },
//      min : 0.00,
//      max : 7.00,
//      splitNumber: 7,  //右侧y分成7段
//      splitLine: {
//        show:false
//      },
//      axisLine: {
//       show: false
//      },
//    }
//  ],
//  series : [
//    {
//      barWidth: 30,
//      name:'涨跌（BP）',
//      type:'bar',
//      yAxisIndex: 1,
//      data:[0,0,3.81, 4.25, 6.47, 6.58, 6.5, 5.95, 6.60, 5.59, 5.13],
//      itemStyle: {
//          normal: {
//              color: '#c1002f',
//         }
//      },
//    },
//    {
//      name:'收盘利率（%）',
//      type:'line',
//      data:[0, 0, 4.1425, 4.2163, 4.2775, 4.3663, 4.3913, 4.4038, 4.4188, 4.4425, 4.4825],
//      itemStyle: {
//          normal: {
//              color: '#3E98C5',
//         }
//      },
//    },
//  ]
//};
//myChart2.setOption(option2); 