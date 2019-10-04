package com.sg.vendingmachine.dao;

/**
 *
 * @author brian russick
 */
public interface VMAuditDao {                                      // audit dao
    public void writeAuditEntry(String entry) throws VMPersistenceException;   
}