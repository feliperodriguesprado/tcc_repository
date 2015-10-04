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

function customerRegisterCtrl($scope, $window, $routeParams, notification, messages, encryption, serverResponse, log, uiGridConstants) {

    $scope.customer = {};

    if ($routeParams.requestId !== "novo") {
        $scope.customer = {
            id: "15",
            type: "1",
            name: "Felipe Prado",
            cpfCnpj: "100.522.156-10",
            active: true,
            dateCreate: "01/09/2015",
            phoneList: [{
                    "id": "15",
                    "number": "+55 (35) 99135 3169",
                    "peopleId" : 15
                },
                {
                    "id": "16",
                    "number": "+55 (21) 99146 3179",
                    "peopleId" : 15
                },
                {
                    "id": "17",
                    "number": "+55 (11) 99145 3199",
                    "peopleId" : 15
                },
                {
                    "id": "18",
                    "number": "+55 (11) 99145 3199",
                    "peopleId" : 15
                }],
            addressList: [
                {
                    "id": "20",
                    "street": "Rua José Carvalho da Costa, 110",
                    "district": "Faisqueira",
                    "city": "Pouso Alegre",
                    "cep": "3755-000",
                    "uf": "MG",
                    "peopleId" : 15
                },
                {
                    "id": "31",
                    "street": "Rua José Carvalho da Costa, 110",
                    "district": "Faisqueira",
                    "city": "Pouso Alegre",
                    "cep": "3755-000",
                    "uf": "MG",
                    "peopleId" : 15
                },
                {
                    "id": "41",
                    "street": "Rua José Carvalho da Costa, 110",
                    "district": "Faisqueira",
                    "city": "Pouso Alegre",
                    "cep": "3755-000",
                    "uf": "MG",
                    "peopleId" : 15
                },
                {
                    "id": "51",
                    "street": "Rua José Carvalho da Costa, 110",
                    "district": "Faisqueira",
                    "city": "Pouso Alegre",
                    "cep": "3755-000",
                    "uf": "MG",
                    "peopleId" : 15
                }
            ]
        };
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
        deletePhone: function (phone) {
            notification.info(JSON.stringify(phone));
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
        deleteAddress: function (address) {
            notification.info(JSON.stringify(address));
        }
    };

    /**
     * Configuration grid
     */
    $scope.gridPhone = {
        appScopeProvider: $scope.scopeGridPhone,
        columnDefs: getConfigColumnsPhone(),
        enableRowSelection: false,
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
        enableRowSelection: false,
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
            {name: 'number', displayName: 'Número', width: "71%", enableColumnMenu: false},
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
    
    $scope.savePhone = function (phone) {
        notification.info(JSON.stringify(phone));
    };

    $scope.addAddress = function () {
        $('#modalAddress').modal('show');
    };

    $scope.saveAddress = function (address) {
        notification.info(JSON.stringify(address));
    };

    $scope.closeModal = function (element) {
        $('#' + element).modal('hide');
    };

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    $scope.gridPhone.data = $scope.customer.phoneList;
    $scope.gridAddress.data = $scope.customer.addressList;

}