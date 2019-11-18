package com.sg.flooringmastery2.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author brian russick
 */                                                // audit dao implementation
public class AuditDaoFileImpl implements AuditDao{
    public static final String AUDIT_FILE = "audit.txt"; // specify file name

    @Override
    public void writeAuditEntry(String entry) throws PersistenceException {
        PrintWriter out;

        try {                             // create new PrintWriter/FileWriter
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {       // catch error
            throw new PersistenceException("ERROR: Could not create audit entry in LoggingAdvice.", e);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();                // write data, flush stream
    }
}