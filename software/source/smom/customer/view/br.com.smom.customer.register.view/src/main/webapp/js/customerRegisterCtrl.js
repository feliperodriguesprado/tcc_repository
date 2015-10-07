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

function customerRegisterCtrl($scope, $window, $routeParams, notification, messages, serverResponse, log, uiGridConstants, resourceListById, resourceCreate, resourceUpdate) {

    $scope.customer = {};

    if ($routeParams.customerId === "novo") {

        $scope.customer = {
            type: "",
            name: "",
            cpfCnpj: "",
            active: true,
            dateCreate: "",
            phoneList: [],
            addressList: []
        };

    } else {

        resourceListById.get({customerId: $routeParams.customerId}, function (data) {
            try {
                if (data.responseResource.code === serverResponse.INFO_GET_CUSTOMER) {
                    $scope.customer = data.customer;
                    $scope.gridPhone.data = $scope.customer.phoneList;
                    $scope.gridAddress.data = $scope.customer.addressList;
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

    /*
     * Scope passed to grid for control events rows grid
     */
    $scope.scopeGridPhone = {
        dblClickRow: function (row) {
            notification.info(JSON.stringify(row.entity));
        },
        updatePhone: function (phone) {
            $scope.phone = window.angular.copy(phone);
            $('#modalPhone').modal('show');
        },
        deletePhone: function (phoneToDelete) {

            var phoneListUpdated = [];

            $scope.customer.phoneList.forEach(function (phone) {
                if (phone.number !== phoneToDelete.number) {
                    phoneListUpdated.push(phone);
                }
            });

            $scope.customer.phoneList = phoneListUpdated;
            $scope.gridPhone.data = $scope.customer.phoneList;
        }
    };

    $scope.scopeGridAddress = {
        dblClickRow: function (row) {
            notification.info(JSON.stringify(row.entity));
        },
        updateAddress: function (address) {
            $scope.address = window.angular.copy(address);
            $('#modalAddress').modal('show');
        },
        deleteAddress: function (addressToDelete) {

            var addressListUpdated = [];

            $scope.customer.addressList.forEach(function (address) {
                if (address.id !== addressToDelete.id && address.tempId !== addressToDelete.tempId) {
                    addressListUpdated.push(address);
                }
            });

            $scope.customer.addressList = addressListUpdated;
            $scope.gridAddress.data = $scope.customer.addressList;

        }
    };

    /**
     * Configuration grid
     */
    $scope.gridPhone = {
        appScopeProvider: $scope.scopeGridPhone,
        columnDefs: getConfigColumnsPhone(),
        enableRowSelection: true,
        multiSelect: false,
        enableSelectAll: false,
        enableRowHeaderSelection: false,
        enableFiltering: false,
        enableColumnMenu: false,
        enableHorizontalScrollbar: 0,
        enableVerticalScrollbar: 1,
        rowTemplate: "<div ng-dblclick=\"gridPhone.scopeGridPhone.dblClickRow(row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
    };

    $scope.gridAddress = {
        appScopeProvider: $scope.scopeGridAddress,
        columnDefs: getConfigColumns(),
        enableRowSelection: true,
        multiSelect: false,
        enableSelectAll: false,
        enableRowHeaderSelection: false,
        enableFiltering: false,
        enableColumnMenu: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        rowTemplate: "<div ng-dblclick=\"gridAddress.scopeGridAddress.dblClickRow(row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
    };

    /**
     * Register action in grid.
     * 
     * @param gridApi
     */
    $scope.gridPhone.onRegisterApi = function (gridApi) {

        //set gridApi on scope
        $scope.gridApi = gridApi;

//        gridApi.selection.on.rowSelectionChanged($scope, function (row) {
//            notification.info(JSON.stringify(row.entity));
//        });
//
//        gridApi.selection.on.rowSelectionChangedBatch($scope, function (rowList) {
//            rowList.forEach(function (row) {
//                notification.info(JSON.stringify(row.entity));
//            });
//        });
    };

    /**
     * Function that get array with set columns of grid
     * @returns {Array}
     */
    function getConfigColumnsPhone() {
        var templateUpdatePhone = '<div class="btn-grid-phone"><button type="button" class="btn btn-primary btn-xs" ng-click="grid.appScope.updatePhone(row.entity)"><i class="fa fa-pencil"></i></button></div>';
        var templateDeletePhone = '<div class="btn-grid-phone"><button type="button" class="btn btn-danger btn-xs" ng-click="grid.appScope.deletePhone(row.entity)"><i class="fa fa-times"></i></button></div>';

        return [
            {name: 'number', displayName: 'Telefone', width: "71%", enableColumnMenu: false},
            {name: 'update', displayName: '', width: "15%", cellTemplate: templateUpdatePhone, enableColumnMenu: false, enableSorting: false, enableFiltering: false},
            {name: 'delete', displayName: '', width: "15%", cellTemplate: templateDeletePhone, enableColumnMenu: false, enableSorting: false, enableFiltering: false}
        ];
    }

    function getConfigColumns() {
        var templateUpdateAddress = '<div class="btn-grid-phone"><button type="button" class="btn btn-primary btn-xs" ng-click="grid.appScope.updateAddress(row.entity)"><i class="fa fa-pencil"></i></button></div>';
        var templateDeleteAddress = '<div class="btn-grid-phone"><button type="button" class="btn btn-danger btn-xs" ng-click="grid.appScope.deleteAddress(row.entity)"><i class="fa fa-times"></i></button></div>';

        return [
            {name: 'street', displayName: 'Logradouro', width: "30%", enableColumnMenu: false},
            {name: 'district', displayName: 'Bairro', width: "20%", enableColumnMenu: false},
            {name: 'city', displayName: 'Cidade', width: "20%", enableColumnMenu: false},
            {name: 'cep', displayName: 'CEP', width: "15%", enableColumnMenu: false},
            {name: 'uf', displayName: 'UF', width: "5%", enableColumnMenu: false},
            {name: 'update', displayName: '', width: "5%", cellTemplate: templateUpdateAddress, enableColumnMenu: false, enableSorting: false, enableFiltering: false},
            {name: 'delete', displayName: '', width: "5%", cellTemplate: templateDeleteAddress, enableColumnMenu: false, enableSorting: false, enableFiltering: false}
        ];
    }

    $scope.addPhone = function () {
        $('#modalPhone').modal('show');
    };

    $scope.savePhone = function (customer, phoneToSave) {

        if (checkPhoneExists(customer.phoneList, phoneToSave)) {
            notification.showMessage(messages.WARN_PHONE_EXISTS);
        } else {

            if (customer.phoneList.length === 0) {
                phoneToSave.tempId = new Date().getTime();
                phoneToSave.peopleId = customer.id;
                customer.phoneList.push(phoneToSave);
                $scope.gridPhone.data = customer.phoneList;
            } else {
                customer.phoneList.forEach(function (phone) {

                    if (phoneToSave.id === undefined && phoneToSave.tempId === undefined) {
                        phoneToSave.tempId = new Date().getTime();
                        phoneToSave.peopleId = customer.id;
                        customer.phoneList.push(phoneToSave);
                        $scope.gridPhone.data = customer.phoneList;
                        return;
                    } else {
                        if (phone.id === phoneToSave.id && phone.tempId === phoneToSave.tempId) {
                            phone.number = phoneToSave.number;
                        }
                    }

                });
            }

            delete $scope.phone;
            $('#modalPhone').modal('hide');
        }
    };

    function checkPhoneExists(phoneList, phoneToSave) {

        var check = false;

        phoneList.forEach(function (phone) {
            if (phone.number === phoneToSave.number) {
                if (phone.tempId === phoneToSave.tempId && phone.id === phoneToSave.id) {
                    check = false;
                } else {
                    check = true;
                }
            }
        });

        return check;

    }

    $scope.addAddress = function () {
        $('#modalAddress').modal('show');
    };

    $scope.saveAddress = function (customer, addressToSave) {

        if (customer.addressList.length === 0) {
            addressToSave.tempId = new Date().getTime();
            addressToSave.peopleId = customer.id;
            customer.addressList.push(addressToSave);
            $scope.gridAddress.data = customer.addressList;
        } else {
            customer.addressList.forEach(function (address) {

                if (addressToSave.id === undefined && addressToSave.tempId === undefined) {
                    addressToSave.tempId = new Date().getTime();
                    addressToSave.peopleId = customer.id;
                    customer.addressList.push(addressToSave);
                    $scope.gridAddress.data = customer.addressList;
                    return;
                } else {
                    if (address.id === addressToSave.id && address.tempId === addressToSave.tempId) {
                        address.street = addressToSave.street;
                        address.district = addressToSave.district;
                        address.city = addressToSave.city;
                        address.cep = addressToSave.cep;
                        address.uf = addressToSave.uf;
                    }
                }

            });
        }

        delete $scope.address;
        $('#modalAddress').modal('hide');
    };

    $scope.saveCustomer = function (customer) {

        if (customer.id === undefined) {
            resourceCreate.save({customer: customer}, function (data) {
                try {
                    if (data.responseResource.code === serverResponse.INFO_CREATE_CUSTOMER) {
                        $scope.message = data.responseResource.description;
                        $('#modalInfoCustomer').modal('show');
                        $scope.customer = data.customer;
                        $scope.gridPhone.data = $scope.customer.phoneList;
                        $scope.gridAddress.data = $scope.customer.addressList;
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
            resourceUpdate.save({customer: customer}, function (data) {
                try {
                    if (data.responseResource.code === serverResponse.INFO_UPDATE_CUSTOMER) {
                        $scope.message = data.responseResource.description;
                        $('#modalInfoCustomer').modal('show');
                        $scope.customer = data.customer;
                        $scope.gridPhone.data = $scope.customer.phoneList;
                        $scope.gridAddress.data = $scope.customer.addressList;
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

    $scope.deleteCustomer = function (customer) {

        if (customer.id !== undefined) {

            customer.active = false;

            resourceUpdate.save({customer: customer}, function (data) {
                try {
                    if (data.responseResource.code === serverResponse.INFO_UPDATE_CUSTOMER) {
                        $scope.message = messages.INFO_DELETE_CUSTOMER.description;
                        $('#modalInfoCustomer').modal('show');
                        $scope.customer = data.customer;
                        $scope.gridPhone.data = $scope.customer.phoneList;
                        $scope.gridAddress.data = $scope.customer.addressList;
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
    
    $scope.validateButtonSaveCustomer = function (customer) {
        if (customer.name !== '' && customer.cpfCnpj !== '') {
            return false;
        } else {
            return true;
        }
    };

    $scope.validateButtonDeleteCustomer = function (customer) {
        if (customer.id === undefined || customer.id === null || customer.id === '') {
            return true;
        } else {
            return false;
        }
    };

    $scope.closeModal = function (element) {
        $('#' + element).modal('hide');
    };

    $('#modalInfoCustomer').on('hidden.bs.modal', function () {
        $window.location.href = "#/";
    });

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

}