function RequestAjaxControl() {

    "use strict";
    
    var thisClass = this,
        $ = window.$,
        main = new window.Main();
    
    this.testClass = function () {
        thisClass.executeMethodGetJson("./resources/restfulService1", function (data) {
            data.name = "Felipe Prado";
            thisClass.executeMethodPostJson("./resources/restfulService1", data);
        });
    };

    this.executeMethodGetJson = function (url, callbackSucces, callbackError, callbackComplete) {

        $.ajax({
            dataType: "json", // Tipo de dados esperado de retorno do servidor.
            type: "GET",
            url: url,
            beforeSend: function (xhr) {
                main.writeInfoLog("Init request ajax url: " + url);
                main.writeInfoLog("xhr = " + JSON.stringify(xhr));
            },
            success: function (data, textStatus, jqXHR) {
                main.writeInfoLog("data = " + JSON.stringify(data));
                main.writeInfoLog("textStatus = " + textStatus);
                main.writeInfoLog("jqXHR = " + JSON.stringify(jqXHR));

                if (callbackSucces !== undefined && callbackSucces !== null) {
                    callbackSucces(data);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                main.writeErrorLog("jqXHR = " + JSON.stringify(jqXHR));
                main.writeErrorLog("textStatus = " + JSON.stringify(textStatus));
                main.writeErrorLog("errorThrown = " + JSON.stringify(errorThrown));

                if (callbackError !== undefined && callbackError !== null) {
                    callbackError();
                }
            },
            complete: function () {
                main.writeInfoLog("Complete request ajax.");
                
                if (callbackComplete !== undefined && callbackComplete !== null) {
                    callbackComplete();
                }
            }
        });
    };

    this.executeMethodPostJson = function (url, data, callbackSucces, callbackError, callbackComplete) {

        $.ajax({
            contentType: "application/json; charset=utf-8", // Tipo do conteúdo.
            data: JSON.stringify(data),
            dataType: "json", // Tipo de dados esperado de retorno do servidor.
            type: "POST",
            url: url,
            beforeSend: function (xhr) {
                main.writeInfoLog("Init request ajax url: " + url);
                main.writeInfoLog("xhr = " + JSON.stringify(xhr));
            },
            success: function (data, textStatus, jqXHR) {
                main.writeInfoLog("data = " + JSON.stringify(data));
                main.writeInfoLog("textStatus = " + textStatus);
                main.writeInfoLog("jqXHR = " + JSON.stringify(jqXHR));

                if (callbackSucces !== undefined && callbackSucces !== null) {
                    callbackSucces(data);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                main.writeErrorLog("jqXHR = " + JSON.stringify(jqXHR));
                main.writeErrorLog("textStatus = " + JSON.stringify(textStatus));
                main.writeErrorLog("errorThrown = " + JSON.stringify(errorThrown));

                if (callbackError !== undefined && callbackError !== null) {
                    callbackError();
                }
            },
            complete: function () {
                main.writeInfoLog("Complete request ajax.");
                
                if (callbackComplete !== undefined && callbackComplete !== null) {
                    callbackComplete();
                }
            }
        });
    };

}

var requestAjaxControl = new RequestAjaxControl();
requestAjaxControl.testClass();