/* global Highcharts */

$.ajax({
    url: 'viewgrafik',
    success: function(result){
        var date = JSON.parse(result).date;
        var temperature = JSON.parse(result).temperature;
        drawLineChart(date, temperature);
    
    }
})


function drawLineChart(date, temperature){
	var myChart = Highcharts.chart('grafik', {
	    chart: {
	        type: 'line',
	        width: 500
	    },
	    
	    title: {
	        text: 'Line Chart'
	    },
	
	    xAxis: {
	        categories: date
	    },
	    
	    tooltip: {
	        formatter: function() {
	          return '<strong>'+this.x+': </strong>'+ this.y;
	        }
	    },
	
	    series: [{
	        data: temperature
	    }]
	});
}


