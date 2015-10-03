/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smom.user.api.exceptions;

import br.com.smom.main.util.api.enums.Messages;
import br.com.smom.main.util.api.exceptions.UtilException;

public class UserException extends UtilException {

    public UserException(String message) {
        super(message);
    }

    public UserException(int code, String description) {
        super(code, description);
    }

    public UserException(int code, String description, Throwable cause) {
        super(code, description, cause);
    }

    public UserException(Messages message) {
        super(message);
    }

    public UserException(Messages message, String cause) {
        super(message, cause);
    }

    public UserException(Messages message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

}
