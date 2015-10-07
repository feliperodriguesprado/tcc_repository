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

function releaseListCtrl($scope, $window, notification, date, messages, serverResponse, log, uiGridConstants, resourceListByCreateDate) {

    var filterList = 'show';

    $scope.releasesFilter = {
        createDateStart: date.getDateTime().subtract(30, 'days').calendar(),
        createDateEnd: date.getDateTime().format('L')
    };

    $scope.search = function (releasesFilter) {

        filterOptions = {
            createDateStart: date.formatDateToServer(releasesFilter.createDateStart),
            createDateEnd: date.formatDateToServer(releasesFilter.createDateEnd)
        };

        resourceListByCreateDate.get(filterOptions, function (data) {
            try {
                if (data.responseResource.code === serverResponse.INFO_GET_FINANCIAL_RELEASE_LIST) {
                    $scope.grid.data = data.releaseList;

                    var sizeItemList = data.releaseList.length,
                            pageSize = 0;

                    for (var i = 0; i < sizeItemList / 10; i = i + 1) {
                        $scope.grid.paginationPageSizes.push(pageSize + 10);
                        pageSize = pageSize + 10;
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

    };

    /*
     * Scope passed to grid for control events rows grid
     */
    $scope.scopeReleaseList = {
        dblClickRow: function (row) {
            $window.location.href = "#/release-register/" + row.entity.id;
        },
        viewRelease: function (row) {
            $window.location.href = "#/release-register/" + row.id;
        }
    };

    /**
     * Configuration grid
     */
    $scope.grid = {
        appScopeProvider: $scope.scopeReleaseList,
        columnDefs: getConfigColumns(),
        paginationPageSizes: [],
        paginationPageSize: 10,
        enableRowSelection: true,
        multiSelect: false,
        enableSelectAll: false,
        enableRowHeaderSelection: false,
        enableFiltering: false,
        enableColumnMenu: false,
        //rowHeight: 60,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        rowTemplate: "<div ng-dblclick=\"grid.appScope.dblClickRow(row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
    };

    /**
     * Register action in grid.
     * 
     * @param gridApi
     */
    $scope.grid.onRegisterApi = function (gridApi) {

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
    function getConfigColumns() {

        var templateType = '<div class="btn-type-grid-release-list"><button type="button" ng-class="row.entity.type === 1 ? \'btn btn-success btn-xs\' : \'btn btn-danger btn-xs\'" ng-click="grid.appScope.viewRelease(row.entity)"><i ng-class="row.entity.type === 1 ? \'fa fa-arrow-left\' : \'fa fa-arrow-right\'"></i></button></div>';

        return [
            {name: 'type', displayName: '', width: "4%", cellTemplate: templateType, enableColumnMenu: false, enableSorting: false, enableFiltering: false},
            {name: 'description', displayName: 'Descrição', width: "29%", enableColumnMenu: false},
            {name: 'people.name', displayName: 'Cliente', width: "35%", enableColumnMenu: false},
            {name: 'value', displayName: 'Valor', width: "12%", enableColumnMenu: false},
            {name: 'createDate', displayName: 'Data', width: "10%", enableColumnMenu: false},
            {name: 'dueDate', displayName: 'Vencimento', width: "10%", enableColumnMenu: false}
        ];
    }

    $scope.filter = function () {
        if (filterList === 'hide') {
            $('#filterList').collapse(filterList);
            filterList = 'show';
        }

        $scope.grid.enableFiltering = !$scope.grid.enableFiltering;
        $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.COLUMN);
    };

    $scope.showFilterList = function () {
        $('#filterList').collapse(filterList);

        if (filterList === 'show') {
            filterList = 'hide';
        } else {
            filterList = 'show';
        }
    };

    $scope.listAll = function () {
        notification.info("Não implementado");
    };

    $scope.closeModal = function (element) {
        $('#' + element).modal('hide');
    };

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
    
    $scope.search($scope.releasesFilter);
}

