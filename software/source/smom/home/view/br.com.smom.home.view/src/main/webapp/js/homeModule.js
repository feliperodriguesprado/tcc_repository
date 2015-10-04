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
var homeModule = window.angular.module('homeModule', ['utilModule', 'ngRoute', 'ngResource']);

homeModule.config(['$routeProvider', function ($routeProvider) {

        'use strict';

        $routeProvider.
                when('/', {
                    controller: window.homeCtrl,
                    templateUrl: 'pages/partials/_home.html'
                }).
                when('/dashboard', {
                    controller: window.homeCtrl,
                    templateUrl: 'pages/partials/_home.html'
                }).
                otherwise({
                    redirectTo: '/'
                });
    }]);