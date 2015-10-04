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

function loginCtrl($scope, $window, notification, messages, encryption, resourceUserLogin, serverResponse, log) {

    $scope.validateFieldEmail = function (form) {
        if ((form.userName.$dirty && form.userName.$invalid) ||
                form.userName.$error.minlength ||
                form.userName.$error.maxlength) {
            return 'form-group has-error';
        } else if (form.userName.$valid) {
            return 'form-group has-success';
        } else {
            return 'form-group';
        }
    };

    $scope.validateFieldPassword = function (form) {
        if ((form.password.$dirty && form.password.$invalid) ||
                form.password.$error.minlength ||
                form.password.$error.maxlength) {
            return 'form-group has-error';
        } else if (form.password.$valid) {
            return 'form-group has-success';
        } else {
            return 'form-group';
        }
    };

    $scope.validateButtonLogin = function (form) {
        if (form.userName.$invalid || form.password.$invalid) {
            return true;
        } else {
            return false;
        }
    };

    $scope.login = function (user) {

        user.password = encryption.parseMD5($scope.password);

        resourceUserLogin.save(user, function (data) {
            try {
                if (data.responseResource.code === serverResponse.INFO_SUCCESS_USER_LOGIN) {
                    $window.location.href = "modules/home";
                } else if (data.responseResource.code === serverResponse.WARN_UNAVAILABLE_MODULE) {
                    notification.showMessage(messages.WARN_UNAVAILABLE_USER_MODULE);
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
}
