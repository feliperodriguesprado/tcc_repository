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
var customerRegisterModule = window.angular.module('customerRegisterModule', ['utilModule', 'ngRoute', 'ngResource', 'ui.grid', 'ngTouch', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.grid.moveColumns', 'ui.grid.pagination']);

customerRegisterModule.config(['$routeProvider', function ($routeProvider) {

        'use strict';

        $routeProvider.
                when('/', {
                    controller: window.customerListCtrl,
                    templateUrl: 'pages/partials/_customer-list.html'
                }).
                when('/customer-list', {
                    controller: window.customerListCtrl,
                    templateUrl: 'pages/partials/_customer-list.html'
                }).
                when('/customer-register/:customerId', {
                    controller: window.customerRegisterCtrl,
                    templateUrl: 'pages/partials/_customer-register.html'
                }).
                otherwise({
                    redirectTo: '/'
                });
    }]);