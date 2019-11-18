package com.sg.flooringmastery2.dao;

/**
 *
 * @author brian russick
 */
public interface AuditDao {                                    
    public void writeAuditEntry(String entry) throws PersistenceException;  
}