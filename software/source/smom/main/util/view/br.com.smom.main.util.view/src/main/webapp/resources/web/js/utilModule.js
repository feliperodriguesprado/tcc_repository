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

var utilModule = window.angular.module('utilModule', ['ngRoute', 'ngResource', 'angular-md5']);

utilModule.factory('document', function () {
    var document = {
        formatDocument: function (document) {

            var i = 0, cpf = '', cnpj = '';

            if (document.length === 11) {
                for (i = 0; i <= document.length; i = i + 1) {
                    if (i === 3) {
                        cpf += '.';
                    } else if (i === 6) {
                        cpf += '.';
                    } else if (i === 9) {
                        cpf += '-';
                    }
                    cpf += document.charAt(i);
                }
                return cpf;
            } else if (document.length === 14) {
                for (i = 0; i <= document.length; i = i + 1) {
                    if (i === 2) {
                        cnpj += '.';
                    } else if (i === 5) {
                        cnpj += '.';
                    } else if (i === 8) {
                        cnpj += '/';
                    } else if (i === 12) {
                        cnpj += '-';
                    }
                    cnpj += document.charAt(i);
                }
                return cnpj;
            }
        }
    };
    return document;
});

utilModule.factory('messages', function () {
    return window.messages;
});

utilModule.factory('serverResponse', function () {
    return window.serverResponse;
});

utilModule.factory('log', function () {

    var getDate = function () {
        return window.moment().format("DD/MM/YYYY HH:mm:ss");
    };

    var log = {
        info: function (message) {
            console.info('Date=' + getDate() + 'Code=' + message.code + '; Description=' + message.description);
        },
        error: function (message, exception) {
            console.error('Date=' + getDate() + '; Code=' + message.code + '; Description=' + message.description + '; Exception=' + exception.message);
        },
        debug: function (object) {
            console.log(object);
        }
    };
    return log;
});

utilModule.factory('notification', function () {
    window.toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-center",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };

    var notification = {
        messageView: function (message) {
            if (message.code >= 0 && message.code < 1000) {
                window.toastr["success"](message.description);
            } else if (message.code >= 1000 && message.code < 1999) {
                window.toastr["info"](message.description);
            } else if (message.code >= 2000 && message.code < 2999) {
                window.toastr["warning"](message.description);
            } else if (message.code >= 3000 && message.code < 3999) {
                window.toastr["error"](message.description);
            }
        },
        messageServer: function (code, message) {
            if (code >= 3000 && code < 4000) {
                window.toastr["success"](message);
            } else if (code >= 4000 && code < 4999) {
                window.toastr["warning"](message);
            } else if (code >= 5000 && code < 5999) {
                window.toastr["error"](message);
            } else if (message.code >= 6000 && message.code < 6999) {
                window.toastr["error"](message.description);
            }
        },
        success: function (text) {
            window.toastr["success"](text);
        },
        info: function (text) {
            window.toastr["info"](text);
        },
        warn: function (text) {
            window.toastr["warning"](text);
        },
        error: function (text) {
            window.toastr["error"](text);
        }
    };

    return notification;
});

utilModule.factory('encryption', function (md5) {
    var encryption = {
        parseMD5: function (string) {
            var hash = md5.createHash(string);
            return hash;
        }
    };
    return encryption;
});