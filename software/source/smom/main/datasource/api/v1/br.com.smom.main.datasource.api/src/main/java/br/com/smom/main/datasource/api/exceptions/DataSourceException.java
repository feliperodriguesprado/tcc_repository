/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smom.main.datasource.api.exceptions;

import br.com.smom.main.util.api.enums.Messages;
import br.com.smom.main.util.api.exceptions.UtilException;

public class DataSourceException extends UtilException {

    public DataSourceException(String message) {
        super(message);
    }

    public DataSourceException(int code, String description) {
        super(code, description);
    }

    public DataSourceException(int code, String description, Throwable cause) {
        super(code, description, cause);
    }

    public DataSourceException(Messages message) {
        super(message);
    }

    public DataSourceException(Messages message, String cause) {
        super(message, cause);
    }

    public DataSourceException(Messages message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceException(Throwable cause) {
        super(cause);
    }

}
