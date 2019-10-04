/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VMAuditDao;
import com.sg.vendingmachine.dao.VMPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author brian russick
 */
public class LoggingAdvice {
    VMAuditDao auditDao;
 
    public LoggingAdvice(VMAuditDao auditDao) {
        this.auditDao = auditDao;
    }
 
    public void createAuditEntry(JoinPoint jp, Throwable ex) {
    Object[] args = jp.getArgs();
    String auditEntry = jp.getSignature().getName() + ex.toString() + ": ";

    for(Object currentArg : args) {
            auditEntry += currentArg;
    }
  
    try {
            auditDao.writeAuditEntry(auditEntry);
    }catch (VMPersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
    }
    }
}