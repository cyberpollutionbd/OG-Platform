/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.region;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.time.InstantProvider;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;

import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.master.AbstractHistoryRequest;

/**
 * Request for the history of a region.
 * <p>
 * A full region master implements historical storage of data.
 * History can be stored in two dimensions and this request provides searching.
 * <p>
 * The first historic dimension is the classic series of versions.
 * Each new version is stored in such a manor that previous versions can be accessed.
 * <p>
 * The second historic dimension is corrections.
 * A correction occurs when it is realized that the original data stored was incorrect.
 * A simple region master might simply replace the original version with the corrected value.
 * A full implementation will store the correction in such a manner that it is still possible
 * to obtain the value before the correction was made.
 * <p>
 * For example, a region added on Monday and updated on Thursday has two versions.
 * If it is realized on Friday that the version stored on Monday was incorrect, then a
 * correction may be applied. There are now two versions, the first of which has one correction.
 * This may continue, with multiple corrections allowed for each version.
 * <p>
 * Versions and corrections are represented by instants in the search.
 */
@BeanDefinition
public class RegionHistoryRequest extends AbstractHistoryRequest {

  /**
   * Creates an instance.
   * The object identifier must be added before searching.
   */
  public RegionHistoryRequest() {
    super();
  }

  /**
   * Creates an instance with object identifier.
   * This will retrieve all versions and corrections unless the relevant fields are set.
   * 
   * @param objectId  the object identifier, not null
   */
  public RegionHistoryRequest(final ObjectIdentifiable objectId) {
    super(objectId);
  }

  /**
   * Creates an instance with object identifier and optional version and correction.
   * 
   * @param objectId  the object identifier, not null
   * @param versionInstantProvider  the version instant to retrieve, null for all versions
   * @param correctedToInstantProvider  the instant that the data should be corrected to, null for all corrections
   */
  public RegionHistoryRequest(final ObjectIdentifiable objectId, InstantProvider versionInstantProvider, InstantProvider correctedToInstantProvider) {
    super(objectId, versionInstantProvider, correctedToInstantProvider);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code RegionHistoryRequest}.
   * @return the meta-bean, not null
   */
  public static RegionHistoryRequest.Meta meta() {
    return RegionHistoryRequest.Meta.INSTANCE;
  }

  @Override
  public RegionHistoryRequest.Meta metaBean() {
    return RegionHistoryRequest.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
    }
    return super.propertyGet(propertyName);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code RegionHistoryRequest}.
   */
  public static class Meta extends AbstractHistoryRequest.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap(super.metaPropertyMap());
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public RegionHistoryRequest createBean() {
      return new RegionHistoryRequest();
    }

    @Override
    public Class<? extends RegionHistoryRequest> beanType() {
      return RegionHistoryRequest.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
