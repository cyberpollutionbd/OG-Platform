/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.util.db.tool;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.opengamma.util.db.script.DbScript;

/**
 * Abstract representation of a database operation on master database schemas using the database installation scripts.
 * 
 * @param <T>  the type of database tool context
 */
public abstract class AbstractDbScriptOperation<T extends DbToolContext> extends AbstractDbOperation<T> {
  
  protected AbstractDbScriptOperation(T dbToolContext, boolean write, File outputFile) {
    super(dbToolContext, write, outputFile);
    contextNotNull(getDbToolContext().scriptReader());
    contextNotNull(getDbToolContext().dbManagement());
  }
  
  //-------------------------------------------------------------------------
  protected String getDatabaseName() {
    return getDbToolContext().getDbManagement().getDatabaseName();
  }
  
  protected DbScript getCreationScript(String groupName) {
    return getDbToolContext().getScriptReader().getCreationScript(getDatabaseName(), groupName);
  }
  
  protected List<DbScript> getMigrationScripts(String groupName) {
    Integer currentGroupVersion = getCurrentGroupVersion(groupName);
    if (currentGroupVersion == null) {
      return null;
    }
    return getDbToolContext().getScriptReader().getMigrationScripts(getDatabaseName(), groupName, currentGroupVersion);
  }
  
  protected Set<String> getAllSchemaNames() {
    return getDbToolContext().getScriptReader().getAllSchemaNames(getDatabaseName());
  }
  
  /**
   * Gets the current schema version for a group.
   * 
   * @param groupName  the group name of the database objects, not null
   * @return the current schema version of the group, null if not versioned
   */
  protected Integer getCurrentGroupVersion(String groupName) {
    return getDbToolContext().getDbManagement().getSchemaGroupVersion(getDbToolContext().getCatalog(), null, groupName);
  }

}
