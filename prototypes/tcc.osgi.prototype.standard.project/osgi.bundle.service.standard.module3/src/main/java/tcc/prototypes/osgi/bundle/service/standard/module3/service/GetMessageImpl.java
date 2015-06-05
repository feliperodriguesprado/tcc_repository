/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.prototypes.osgi.bundle.service.standard.module3.service;

import java.util.Date;
import tcc.prototypes.osgi.bundle.service.standard.module3.api.GetMessage;

/**
 *
 * @author felipeprado
 */
public class GetMessageImpl implements GetMessage {

    public String getMessage() {
        
        return String.format("Message ID: %d", new Date().getTime());
        
    }
    
}
