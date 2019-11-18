package com.sg.flooringmastery2.advice;

import com.sg.flooringmastery2.dao.PersistenceException;
import org.aspectj.lang.JoinPoint;
import com.sg.flooringmastery2.dao.AuditDao;

/**
 *
 * @author brian russick
 */
public class LoggingAdvice {
    AuditDao auditDao;
   
    public LoggingAdvice(AuditDao auditDao){
        this.auditDao = auditDao;  // Spring framework hands us our dependency
    }
   
    public void createAuditEntry(JoinPoint jp) {
    Object[] args = jp.getArgs();                // get arguments of Joinpoint
    String auditEntry = jp.getSignature().getName() + ": ";
    
    for (Object currentArg : args) {
            auditEntry += currentArg; // concatenate argument with audit entry
    }
    
    try {
        auditDao.writeAuditEntry(auditEntry);
    } catch (PersistenceException e) {
            System.err.println(                       // print to error stream
            "ERROR: Could not create audit entry in LoggingAdvice.");
    }
    } 
}