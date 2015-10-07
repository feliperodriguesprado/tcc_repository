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


function releaseRegisterCtrl($scope, $window, $routeParams, notification, date, messages, serverResponse, log, resourceListById, resourceUpdate, resourceCreate, resourceCustomerByName) {

    if ($routeParams.releaseId === 'novo-pagar') {
        $scope.title = 'Lançamento de contas a pagar';
        $scope.release = {
            type: 2
        };
    } else if ($routeParams.releaseId === 'novo-receber') {
        $scope.title = 'Lançamento de contas a receber';
        $scope.release = {
            type: 1
        };
    } else {

        resourceListById.get({releaseId: $routeParams.releaseId}, function (data) {
            try {
                if (data.responseResource.code === serverResponse.INFO_GET_FINANCIAL_RELEASE) {
                    $scope.release = data.release;
                    
                    if (data.release.peopleId !== undefined && data.release.peopleId !== null) {
                        $scope.peopleSearch = data.release.people.name;
                    }

                    $scope.release.dueDate = date.formatDateToView($scope.release.dueDate);

                    if (data.release.type === 1) {
                        $scope.title = 'Lançamento de contas a receber';
                    } else if (data.release.type === 2) {
                        $scope.title = 'Lançamento de contas a pagar';
                    }

                } else if (data.responseResource.code === serverResponse.WARN_UNAVAILABLE_MODULE) {
                    $('#modalUnavailableModule').modal('show');
                } else {
                    notification.showMessage(data.responseResource);
                }
            } catch (e) {
                log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
                notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
            }
        }, function () {
            try {
                log.error(messages.ERROR_REQUEST_SERVER);
                notification.error(messages.ERROR_REQUEST_SERVER);
            } catch (e) {
                log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
                notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
            }
        });
    }

    $scope.searchPeople = function (peopleSearch) {

        resourceCustomerByName.get({customerName: peopleSearch}, function (data) {
            try {
                if (data.responseResource.code === serverResponse.INFO_GET_CUSTOMER) {
                    $scope.release.people = data.customer;
                    $scope.release.peopleId = data.customer.id;
                    
                    if (data.customer.id !== undefined && data.customer.id !== null) {
                        $scope.peopleSearch = data.customer.name;
                    }
                    
                } else if (data.responseResource.code === serverResponse.WARN_UNAVAILABLE_MODULE) {
                    $scope.messageModalModuleExternal = messages.WARN_UNAVAILABLE_CUSTOMER_MODULE.description;
                    $('#modalUnavailableModule').modal('show');
                    
                    delete $scope.peopleSearch;
                    
                } else {
                    notification.showMessage(data.responseResource);
                }
            } catch (e) {
                log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
                notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
            }
        }, function () {
            try {
                log.error(messages.ERROR_REQUEST_SERVER);
                notification.error(messages.ERROR_REQUEST_SERVER);
            } catch (e) {
                log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
                notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
            }
        });



        notification.info(queryPeople);
        delete $scope.queryPeople;
    };

    $scope.saveFinancialRelease = function (release) {

        release.dueDate = date.getDateTime(date.formatDateToServer(release.dueDate));
        
        if ($scope.peopleSearch === '' || $scope.peopleSearch === null || $scope.peopleSearch === undefined) {
            release.peopleId = 0;
        }

        if (release.id !== undefined && release.id !== null && release.id !== '') {
            resourceUpdate.save({release: release}, function (data) {
                try {
                    if (data.responseResource.code === serverResponse.INFO_UPDATE_FINANCIAL_RELEASE) {
                        $scope.release = data.release;

                        $scope.release.dueDate = date.formatDateToView($scope.release.dueDate);

                        $scope.message = data.responseResource.description;
                        $('#modalInfoFinancialReleases').modal('show');
                    } else if (data.responseResource.code === serverResponse.WARN_UNAVAILABLE_MODULE) {
                        $('#modalUnavailableModule').modal('show');
                    } else {
                        notification.showMessage(data.responseResource);
                    }
                } catch (e) {
                    log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
                    notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
                }
            }, function () {
                try {
                    log.error(messages.ERROR_REQUEST_SERVER);
                    notification.error(messages.ERROR_REQUEST_SERVER);
                } catch (e) {
                    log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
                    notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
                }
            });
        } else {
            
            release.accountId = 1;
            release.paymentTypeId = 1;
            
            resourceCreate.save({release: release}, function (data) {
                try {
                    if (data.responseResource.code === serverResponse.INFO_CREATE_FINANCIAL_RELEASE) {
                        $scope.release = data.release;

                        $scope.release.dueDate = date.formatDateToView($scope.release.dueDate);

                        $scope.message = data.responseResource.description;
                        $('#modalInfoFinancialReleases').modal('show');
                    } else if (data.responseResource.code === serverResponse.WARN_UNAVAILABLE_MODULE) {
                        $('#modalUnavailableModule').modal('show');
                    } else {
                        notification.showMessage(data.responseResource);
                    }
                } catch (e) {
                    log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
                    notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
                }
            }, function () {
                try {
                    log.error(messages.ERROR_REQUEST_SERVER);
                    notification.error(messages.ERROR_REQUEST_SERVER);
                } catch (e) {
                    log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
                    notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
                }
            });
        }

    };

    $scope.delete = function (release) {
        notification.info(JSON.stringify(release));
    };

    $scope.closeModal = function (element) {
        $('#' + element).modal('hide');
    };
    
    $scope.validateButtonSearchPeople = function (searchPeople) {
        if (searchPeople !== null && searchPeople !== undefined && searchPeople !== '') {
            return false;
        } else {
            return true;
        }
    };

    $('#modalInfoFinancialReleases').on('hidden.bs.modal', function () {
        $window.location.href = "#/";
    });

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
}