package com.sg.vendingmachine.dao;

/**
 *
 * @author brian russick
 */
public interface VMAuditDao {                                      // audit dao
    public void auditFile(String entry) throws VMPersistenceException;   
}