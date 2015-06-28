function Main() {

    "use strict";

    var thisClass = this,
        moment = window.moment;

    this.getDateTime = function (format) {
        if (format === undefined) {
            return moment().format("DD/MM/YYYY HH:mm:ss");
        } else {
            moment().format(format);
        }
    };

    // Método para escrever log de info no console:
    this.writeInfoLog = function (message) {
        window.console.info("[INFO " + thisClass.getDateTime() + "] " + message);
    };
    
    // Método para escrever log de erro no console:
    this.writeErrorLog = function (message) {
        window.console.error("[ERROR " + thisClass.getDateTime() + "] " + message);
    };

}