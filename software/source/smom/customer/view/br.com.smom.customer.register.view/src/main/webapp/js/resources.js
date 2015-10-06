/* 
 * Smom - Software Module Management.
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

window.angular.module('customerRegisterModule').factory('resourceCreate', function ($resource) {
    return $resource('resources/rest/create');
});

window.angular.module('customerRegisterModule').factory('resourceUpdate', function ($resource) {
    return $resource('resources/rest/update');
});

window.angular.module('customerRegisterModule').factory('resourceListById', function ($resource) {
    return $resource('resources/rest/list/:customerId');
});

window.angular.module('customerRegisterModule').factory('resourceListAll', function ($resource) {
    return $resource('resources/rest/list/all');
});

window.angular.module('customerRegisterModule').factory('resourceListCreatedRanking', function ($resource) {
    return $resource('resources/rest/list/created/ranking');
});