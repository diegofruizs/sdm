$(function () {

});

function buscar() {

    console.log(places);

    var x = $('#stationSelect option').filter(':selected').text();
    $('#nameStation').html("Información correspondiente a la estación: " + x)
    drawChart(x)
}

function drawChart(nameOfStation) {


    $('#columnChart').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: "Densidad de pasajeros durante el dia"
        },
        subtitle: {
            text: nameOfStation
        },
        xAxis: {
            categories: [
                '04:00:00 - 04:10:00',
                '04:10:00 - 04:20:00',
                '04:20:00 - 04:30:00',
                '04:30:00 - 04:40:00',
                '04:40:00 - 04:50:00',
                '04:50:00 - 05:00:00',
                '05:00:00 - 05:10:00',
                '05:10:00 - 05:20:00',
                '05:20:00 - 05:30:00',
                '05:30:00 - 05:40:00',
                '05:40:00 - 05:50:00',
                '05:50:00 - 06:00:00',
                '06:00:00 - 06:10:00',
                '06:10:00 - 06:20:00',
                '06:20:00 - 06:30:00',
                '06:30:00 - 06:40:00',
                '06:40:00 - 06:50:00',
                '06:50:00 - 07:00:00',
                '07:00:00 - 07:10:00'

            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Cantidad'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Tokyo',
            data: [300, 200, 250, 30, 40, 100, 180, 500, 10, 50, 80, 12, 34, 23, 56, 24, 34, 875, 244]

        }]
    });

}
