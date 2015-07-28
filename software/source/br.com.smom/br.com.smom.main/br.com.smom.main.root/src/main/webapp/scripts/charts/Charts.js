/*
Script para criação de gráficos de linhas (Line Chart) 
responsável por disponibilizar uma configuração para os gráficos,
tornando disponível a criação de gráficos com valores no eixo X e Y 
ou label no eixo X e valores no eixo X.
*/

/*
Function global que obtem as posições X e Y da linha no formato correto para adicionar no Chart.

params:
line (Line) = linha a ser inserida no Chart
i = index da linha para ter referência no formato do array de posição X e Y
hValuePointFormat = formato do valor de X no ponto
vValuePointFormat = formato do valor de Y no ponto
*/
function GetRowChart(line, i, hValuePointFormat, vValuePointFormat) {
    
    "use strict";
    
    var positionList = line.getPositionList(),
        pointXY,
        row = [];
    
    positionList.forEach(function (position) {
            
        pointXY = [];
        pointXY.push({v: position.x, f: window.FormatValue(position.x, hValuePointFormat)});
        
        while (pointXY.length < i + 1) {
            pointXY.push(null);
        }
        
        pointXY[i + 1] = {v: position.y, f: window.FormatValue(position.y, vValuePointFormat)};
        row.push(pointXY);
    });
    
    return row;
}

/*
Function global que obtem as linhas do gráfico com os valores 
do eixo vertical (Y) para cada label (eixo horizontal X).

params:
chart = objeto referente ao gráfico configura
*/
function GetRowsChartWithLabel(chart) {
    
    "use strict";
    
    var rowList = [], labelList = chart.getLabelList(), lineList = chart.getLineList(), row, i, j;
    
    for (i = 0; i < labelList.length; i = i + 1) {
        
        row = [];
        row.push(labelList[i].label);
        
        for (j = 0; j < lineList.length; j = j + 1) {
            row.push({v: lineList[j].positionList[i].y, f: window.FormatValue(lineList[j].positionList[i].y, chart.vValuePointFormat)});
        }
        
        rowList.push(row);
    }

    return rowList;
}

/*
Function global que formata um valor conforme formato recebido como parâmetro.
*/
function FormatValue(value, format) {

    'use strict';
    
    if (format === undefined) {
        return format;
    } else {
        window.numeral.language("pt-br");
        return window.numeral(value).format(format);
    }
    
}

/*
Function que controla a criação dos Charts.
*/
function ChartsController() {
    
    'use strict';
    
    var chartsToDraw = new window.ChartsToDraw();
    
    /*
    Método que adiciona Chart na lista de charts que serão gerados.
    Params:
    chart (Chart) - Objeto que será adicionado na lista de charts.
    */
    this.addChart = function (chart) {
        //window.chartList.push(chart);
        chartsToDraw.addChart(chart);
    };
    
    /*
    Método que deve ser chamado para obter os Charts configurados.
    */
    this.generateCharts = function () {
        
        try {
            window.google.load('visualization', '1', {packages: ['corechart', 'line'], 'language': 'pt-br'});
            window.google.setOnLoadCallback(this.drawCharts);
        } catch (exception) {
            window.console.error('Carregar script jsapi do Google no HTML: <script type="text/javascript" src="https://www.google.com/jsapi"></script>');
        }
        
    };
    
    /*
    Método que desenha os Charts configurados.
    */
    this.drawCharts = function () {
        
        var chartList = chartsToDraw.getChartList();
        
        chartList.forEach(function (chart) {
            
            var data = new window.google.visualization.DataTable(),
                lineList = chart.getLineList(),
                options,
                rowList = [],
                i,
                j;
            
            // Desabilita o preenchimento da área da linha devido a configuração do tipo da linha ser curvo:
            if (chart.curveType) {
                chart.chartType = "LineChart";
            }
            
            if (chart.hAxisIsLabel) { // Configura o tipo do eixo horizontal (X) como label e vertical (Y) como valor numérico:
                
                data.addColumn("string", "");
                
                // Adiciona a linha no gráfico
                lineList.forEach(function (line) {
                    data.addColumn("number", line.name);
                });
                
                data.addRows(window.GetRowsChartWithLabel(chart));
                
            } else { // Configura o tipo do eixo horizontal (X) e vertical (Y) como valor numérico:
                
                data.addColumn("number", "");
                
                // Adiciona linhas e suas posições no chart:
                for (i = 0; i < lineList.length; i = i + 1) {
                    
                    data.addColumn("number", lineList[i].name);
                    rowList = window.GetRowChart(lineList[i], i, chart.hValuePointFormat, chart.vValuePointFormat);
            
                    for (j = 0; j < rowList.length; j = j + 1) {
                        data.addRow(rowList[j]);
                    }
                }
            }
            
            options = {
                title: chart.chartTitle,
                hAxis: {
                    title: chart.hAxisTitle,
                    titleTextStyle: {color: '#333'},
                    minValue: chart.hMinValue,
                    maxValue: chart.hMaxValue,
                    gridlines: { count: chart.hGridlinesCount },
                    format: chart.hAxisFormat
                },
                vAxis: {
                    title: chart.vAxisTitle,
                    titleTextStyle: {color: '#333'},
                    minValue: chart.vMinValue,
                    maxValue: chart.vMaxValue,
                    gridlines: { count: chart.vGridlinesCount },
                    format: chart.vAxisFormat
                },
                pointSize: chart.pointSize,
                width: chart.width,
                height: chart.height,
                curveType: chart.curveType ? "function" : "",
                animation: {
                    startup: true,
                    duration: 1000
                },
                is3D: chart.pieChartIs3D

            };
            
            if (chart.chartType === "AreaChart") {
                chart = new window.google.visualization.AreaChart(document.getElementById(chart.htmlElement));
            } else if (chart.chartType === "LineChart") {
                chart = new window.google.visualization.LineChart(document.getElementById(chart.htmlElement));
            } else if (chart.chartType === "PieChart" && chart.hAxisIsLabel) {
                chart = new window.google.visualization.PieChart(document.getElementById(chart.htmlElement));
            } else if (chart.chartType === "BarChart") {
                chart = new window.google.visualization.BarChart(document.getElementById(chart.htmlElement));
            } else if (chart.chartType === "ColumnChart") {
                chart = new window.google.visualization.ColumnChart(document.getElementById(chart.htmlElement));
            }
            
            chart.draw(data, options);
            
        });
    };
}

/*
Function que representa o objeto Line.
*/
function Line(name) {
    
    'use strict';
    
    this.name = name;
    this.positionList = [];
    
    this.addPosition = function (position) {
        this.positionList.push(position);
    };
    
    this.getPositionList = function () {
        return this.positionList;
    };
    
}


/*
Function que representa o objeto Position.
*/
function Position(x, y) {
    
    'use strict';
    
    this.x = x;
    this.y = y;
}

/*
Function que representa o objeto Label do eixo horizontal (X).
*/
function Label(label) {
    
    "use strict";
    
    this.label = label;
    
}

/*
Function que representa o objeto Chart.
*/
function Chart() {

    'use strict';
    
    this.chartTitle = undefined;        // Define título do Chart (default: sem nome).
    
    this.chartType = "AreaChart";       // Define o tipo do gráfico. Tipos:
                                        // "LineChart", "AreaChart", "BarChart", "ColumnChart", "PieChart" (default: "AreaChart").
                                        // O tipo "PieChart" só é possivel com o gráfico de eixo horizontal (X) como label
    
    this.curveType = false;             // Define se a linha será curva ou reta. 
                                        // Se esse atributo for true o atributo charType passa a ser "LineChart" (default: false).
    
    this.htmlElement = undefined;       // Define qual elemento html o Chart será colocado.
    
    this.pieChartIs3D = false;          // Define se o Chart do tipo "PieChart" é 3D (default: false).
    
    this.pointSize = undefined;         // Define o tamanho dos pontos das linhas (default: sem ponto).
    
    this.hAxisFormat = undefined;       // Define o formato dos valores do eixo horizontal (X).
    this.vAxisFormat = undefined;       // Define o formato dos valores do eixo vertical (Y).
                                        // Formatos: 
                                        // "decimal", "scientific", "currency", "percent", "short", "long", "MMM d, y", "#,###%"
    
    this.hValuePointFormat = undefined; // Define o formato dos valores dos pontos do eixo horizontal (X). Formatos:
    this.vValuePointFormat = undefined; // Define o formato dos valores dos pontos do eixo vertical (Y).
                                        // Formatos: "0,0.00", "R$ 0,0.00". 
                                        // Está sendo usado a biblioteca Numeral.js. Documentação de outros formatos: http://numeraljs.com/
    
    this.hAxisIsLabel = false;          // Define se o eixo horizontal (X) será um label ou númerico (padrão: false).
    
    this.hAxisTitle = undefined;        // Define título do eixo horizontal (X) (default: sem nome).
    this.vAxisTitle = undefined;        // Define título do eixo vertical (Y) (default: sem nome).
    
    this.hGridlinesCount = undefined;   // Define quantidade de linhas horizontal (X) da grid (default: calculado automático).
    this.vGridlinesCount = undefined;   // Define quantidade de linhas vertical (Y) da grid (default: calculado automático).
    
    this.hMaxValue = undefined;         // Define o valor máximo do eixo horizontal (X) (default: calculado automático).
    this.hMinValue = undefined;         // Define o valor mínimo do eixo horizontal (X) (default: calculado automático).
    
    this.vMaxValue = undefined;         // Define o valor máximo do eixo vertical (Y) (default: calculado automático).
    this.vMinValue = undefined;         // Define o valor mínimo do eixo vertical (Y) (default: calculado automático).
    
    this.height = undefined;            // Define altura do Chart (default: estende no tamanho do elemento html).
    this.width = undefined;             // Define largura do Chart (default: estende no tamanho do elemento html).
    
    this.lineList = [];                 // Lista de linhas do chart.
    
    this.addLine = function (line) {
        this.lineList.push(line);
    };
    
    this.getLineList = function () {
        return this.lineList;
    };
    
    this.labelList = [];                // Lista de labes para Chart com eixo horizontal (X) com labels.
    
    this.addLabel = function (label) {
        this.labelList.push(label);
    };
    
    this.getLabelList = function () {
        return this.labelList;
    };
}

/*
Function que armazena objetos Chart que serão gerados.
*/

function ChartsToDraw() {
    
    'use strict';
    
    this.chartList = [];
    
    this.addChart = function (chart) {
        this.chartList.push(chart);
    };
    
    this.getChartList = function () {
        return this.chartList;
    };
    
}


var charts = new ChartsController();