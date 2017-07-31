$(function () {

});

function buscar() {

    var nameStation = $('#stationSelect option').filter(':selected').text();
    var idStation = $('#stationSelect option').filter(':selected').val();
    $('#nameStation').html("Información correspondiente a la estación: " + nameStation)


    getDataFromBack(idStation, nameStation);

}

function getDataFromBack(id, nameStation) {

    var respuesta = null;
    var obj = null;
    $.ajax({
        url: 'http://localhost:9000/rsdensityPassenger/' + id,

        error: function (error) {
            respuesta = error.responseText;
            obj = $.parseJSON(respuesta);
            console.log(obj);
            console.log(obj.data[0].range);
            dataForChart(obj, nameStation)


        },
        dataType: 'jsonp',
        success: function (data) {
            console.log(data);
        },
        type: 'GET'
    });
}

function dataForChart(obj, nameStaion) {
    var arrayData = obj.data;
    var ranges = [];
    var serieEntrance = [];
    var serieDeparture = [];

    for (var i = 0; i < arrayData.length; i += 1) {
        ranges[i] = arrayData[i].range;

        serieEntrance[i] = arrayData[i].amount_entering;

        serieDeparture[i] = arrayData[i].amount_leaving;

    }
    drawChart(nameStaion, ranges, serieEntrance, serieDeparture)
    writeTable(ranges, serieEntrance, serieDeparture)

}

function writeTable(range, seriesEntrada, seriesSalida) {
    var count = 0;
    // declare html variable (a string holder):
    var html = '';

    $('#body').empty();

    html += '<tr><th><b>';
    html += 'Rangos de 10 minutos en el dia';
    html += '</b></th><th><b>';
    html += 'Entrada de pasajeros a la estación';
    html += '</b></th><th><b>';
    html += 'Salida de pasajeros de la estación';
    html += '</b></th></tr>';
    for (var i = 0; i < range.length; i++) {
        // add opening <tr> tag to the string:
        html += '<tr>';
        for (var j = 0; j < 1; j++) {
            // add <td> elements to the string:
            html += '<td>' + range[count] + '</td>';
            html += '<td>' + seriesEntrada[count] + '</td>';
            html += '<td>' + seriesSalida[count] + '</td>';
            count++;
        }
        // add closing </tr> tag to the string:
        html += '</tr>';
    }
    //append the whole created html string to the body:
    $('#body').append(html);
    // reset the count:
    count = 0;
}

function drawChart(nameOfStation, ranges, serieEntrance, serieDeparture) {

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
            categories: ranges
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
        series: [
            {
                name: "Entrada",
                data: serieEntrance
            },
            {
                name: "Salida",
                data: serieDeparture
            }
        ]
    });

    $('#barChart').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: "Densidad de pasajeros durante el dia"
        },
        subtitle: {
            text: nameOfStation
        },
        xAxis: {
            categories: ranges,
            title: {
                text: null
            }
        },
        yAxis: {
            min: 1,
            type: 'logarithmic',
            endOnTick: true,
            tickInterval: 1,
            minorTickInterval: 10,
            gridLineWidth: 0.1,
            //min: 0,
            title: {
                text: null,
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' millions'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 210,
            floating: true,
            borderWidth: 1,
            backgroundColor: '#FFFFFF',
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'Entrada',
            data: serieEntrance
        }, {
            name: 'Salida',
            data: serieDeparture
        }]
    });

}
