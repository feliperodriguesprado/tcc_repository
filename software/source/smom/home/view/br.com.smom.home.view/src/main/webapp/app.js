/* 
 * Copyright 2015 Smom - Software Module Management.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
var app = window.angular.module('app', ['ngRoute', 'ngResource', 'angular-md5']);

app.config(['$routeProvider', function ($routeProvider) {

        'use strict';

        $routeProvider.
                when('/', {
                    controller: 'homeCtrl',
                    templateUrl: 'partials/_home.html'
                }).
                when('/dashboard', {
                    controller: 'homeCtrl',
                    templateUrl: 'partials/_home.html'
                }).
                otherwise({
                    redirectTo: '/'
                });
    }]);

app.controller('homeCtrl', function ($scope, notification, messages, md5) {

    var ctxChartTotalByAccount = document.getElementById("chartTotalByAccount").getContext("2d");
    var data = [
        {
            value: 750,
            color: "#F7464A",
            highlight: "#FF5A5E",
            label: "Carteira"
        },
        {
            value: 1150,
            color: "#46BFBD",
            highlight: "#5AD3D1",
            label: "Itaú"
        },
        {
            value: 1300,
            color: "#FDB45C",
            highlight: "#FFC870",
            label: "Poupança"
        }
    ];

    var options = {
        animateRotate: false
    };

    new Chart(ctxChartTotalByAccount).Pie(data, options);

    var ctxChartTotalByPayment = document.getElementById("chartTotalByPayment").getContext("2d");
    var data = [
        {
            value: 380,
            color: "#F7464A",
            highlight: "#FF5A5E",
            label: "Carteira"
        },
        {
            value: 670,
            color: "#46BFBD",
            highlight: "#5AD3D1",
            label: "Itaú"
        },
        {
            value: 1200,
            color: "#FDB45C",
            highlight: "#FFC870",
            label: "Poupança"
        }
    ];

    var options = {
        animateRotate: false
    };

    new Chart(ctxChartTotalByPayment).Pie(data, options);


});