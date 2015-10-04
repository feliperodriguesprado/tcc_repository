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


function releaseRegisterCtrl($scope, $window, $routeParams, notification, messages, serverResponse, log) {

    if ($routeParams.releaseId === 'novo-pagar') {
        $scope.title = 'Lançamento de contas a pagar';
    } else if ($routeParams.releaseId === 'novo-receber') {
        $scope.title = 'Lançamento de contas a receber';
    }

    $scope.searchPeople = function (queryPeople) {
        notification.info(queryPeople);
        delete $scope.queryPeople;
    };

    $scope.save = function (release) {
        notification.info(JSON.stringify(release));
    };

    $scope.delete = function (release) {
        notification.info(JSON.stringify(release));
    };
}