/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.world.exchange.master;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.time.Instant;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.BasicMetaBean;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaProperty;

import com.opengamma.id.UniqueIdentifier;
import com.opengamma.util.ArgumentChecker;

/**
 * A document used to pass into and out of the exchange master.
 */
@BeanDefinition
public class ExchangeDocument extends DirectBean {

  /**
   * The exchange unique identifier.
   */
  @PropertyDefinition
  private UniqueIdentifier _exchangeId;
  /**
   * The start of an interval that the version of the exchange is accurate for.
   * This field is populated and managed by the {@code ExchangeMaster}.
   */
  @PropertyDefinition
  private Instant _versionFromInstant;
  /**
   * The end of an interval that the version of the exchange is accurate for.
   * Null indicates this is the latest version.
   * This field is populated and managed by the {@code ExchangeMaster}.
   */
  @PropertyDefinition
  private Instant _versionToInstant;
  /**
   * The start of an interval that the correction of the version of the exchange is accurate for.
   * This field is populated and managed by the {@code ExchangeMaster}.
   */
  @PropertyDefinition
  private Instant _correctionFromInstant;
  /**
   * The end of an interval that the correction of the version of the exchange is accurate for.
   * Null indicates this is the latest correction.
   * This field is populated and managed by the {@code ExchangeMaster}.
   */
  @PropertyDefinition
  private Instant _correctionToInstant;
  /**
   * The exchange.
   */
  @PropertyDefinition
  private ManageableExchange _exchange;

  /**
   * Creates an instance.
   */
  public ExchangeDocument() {
  }

  /**
   * Creates an instance from an exchange.
   * @param exchange  the exchange, not null
   */
  public ExchangeDocument(final ManageableExchange exchange) {
    ArgumentChecker.notNull(exchange, "exchange");
    setExchangeId(exchange.getUniqueIdentifier());
    setExchange(exchange);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExchangeDocument}.
   * @return the meta-bean, not null
   */
  public static ExchangeDocument.Meta meta() {
    return ExchangeDocument.Meta.INSTANCE;
  }

  @Override
  public ExchangeDocument.Meta metaBean() {
    return ExchangeDocument.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case 913218206:  // exchangeId
        return getExchangeId();
      case 2006263519:  // versionFromInstant
        return getVersionFromInstant();
      case 1577022702:  // versionToInstant
        return getVersionToInstant();
      case 1808757913:  // correctionFromInstant
        return getCorrectionFromInstant();
      case 973465896:  // correctionToInstant
        return getCorrectionToInstant();
      case 1989774883:  // exchange
        return getExchange();
    }
    return super.propertyGet(propertyName);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case 913218206:  // exchangeId
        setExchangeId((UniqueIdentifier) newValue);
        return;
      case 2006263519:  // versionFromInstant
        setVersionFromInstant((Instant) newValue);
        return;
      case 1577022702:  // versionToInstant
        setVersionToInstant((Instant) newValue);
        return;
      case 1808757913:  // correctionFromInstant
        setCorrectionFromInstant((Instant) newValue);
        return;
      case 973465896:  // correctionToInstant
        setCorrectionToInstant((Instant) newValue);
        return;
      case 1989774883:  // exchange
        setExchange((ManageableExchange) newValue);
        return;
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the exchange unique identifier.
   * @return the value of the property
   */
  public UniqueIdentifier getExchangeId() {
    return _exchangeId;
  }

  /**
   * Sets the exchange unique identifier.
   * @param exchangeId  the new value of the property
   */
  public void setExchangeId(UniqueIdentifier exchangeId) {
    this._exchangeId = exchangeId;
  }

  /**
   * Gets the the {@code exchangeId} property.
   * @return the property, not null
   */
  public final Property<UniqueIdentifier> exchangeId() {
    return metaBean().exchangeId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the start of an interval that the version of the exchange is accurate for.
   * @return the value of the property
   */
  public Instant getVersionFromInstant() {
    return _versionFromInstant;
  }

  /**
   * Sets the start of an interval that the version of the exchange is accurate for.
   * @param versionFromInstant  the new value of the property
   */
  public void setVersionFromInstant(Instant versionFromInstant) {
    this._versionFromInstant = versionFromInstant;
  }

  /**
   * Gets the the {@code versionFromInstant} property.
   * @return the property, not null
   */
  public final Property<Instant> versionFromInstant() {
    return metaBean().versionFromInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the end of an interval that the version of the exchange is accurate for.
   * Null indicates this is the latest version.
   * @return the value of the property
   */
  public Instant getVersionToInstant() {
    return _versionToInstant;
  }

  /**
   * Sets the end of an interval that the version of the exchange is accurate for.
   * Null indicates this is the latest version.
   * @param versionToInstant  the new value of the property
   */
  public void setVersionToInstant(Instant versionToInstant) {
    this._versionToInstant = versionToInstant;
  }

  /**
   * Gets the the {@code versionToInstant} property.
   * Null indicates this is the latest version.
   * @return the property, not null
   */
  public final Property<Instant> versionToInstant() {
    return metaBean().versionToInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the start of an interval that the correction of the version of the exchange is accurate for.
   * @return the value of the property
   */
  public Instant getCorrectionFromInstant() {
    return _correctionFromInstant;
  }

  /**
   * Sets the start of an interval that the correction of the version of the exchange is accurate for.
   * @param correctionFromInstant  the new value of the property
   */
  public void setCorrectionFromInstant(Instant correctionFromInstant) {
    this._correctionFromInstant = correctionFromInstant;
  }

  /**
   * Gets the the {@code correctionFromInstant} property.
   * @return the property, not null
   */
  public final Property<Instant> correctionFromInstant() {
    return metaBean().correctionFromInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the end of an interval that the correction of the version of the exchange is accurate for.
   * Null indicates this is the latest correction.
   * @return the value of the property
   */
  public Instant getCorrectionToInstant() {
    return _correctionToInstant;
  }

  /**
   * Sets the end of an interval that the correction of the version of the exchange is accurate for.
   * Null indicates this is the latest correction.
   * @param correctionToInstant  the new value of the property
   */
  public void setCorrectionToInstant(Instant correctionToInstant) {
    this._correctionToInstant = correctionToInstant;
  }

  /**
   * Gets the the {@code correctionToInstant} property.
   * Null indicates this is the latest correction.
   * @return the property, not null
   */
  public final Property<Instant> correctionToInstant() {
    return metaBean().correctionToInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the exchange.
   * @return the value of the property
   */
  public ManageableExchange getExchange() {
    return _exchange;
  }

  /**
   * Sets the exchange.
   * @param exchange  the new value of the property
   */
  public void setExchange(ManageableExchange exchange) {
    this._exchange = exchange;
  }

  /**
   * Gets the the {@code exchange} property.
   * @return the property, not null
   */
  public final Property<ManageableExchange> exchange() {
    return metaBean().exchange().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExchangeDocument}.
   */
  public static class Meta extends BasicMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code exchangeId} property.
     */
    private final MetaProperty<UniqueIdentifier> _exchangeId = DirectMetaProperty.ofReadWrite(this, "exchangeId", UniqueIdentifier.class);
    /**
     * The meta-property for the {@code versionFromInstant} property.
     */
    private final MetaProperty<Instant> _versionFromInstant = DirectMetaProperty.ofReadWrite(this, "versionFromInstant", Instant.class);
    /**
     * The meta-property for the {@code versionToInstant} property.
     */
    private final MetaProperty<Instant> _versionToInstant = DirectMetaProperty.ofReadWrite(this, "versionToInstant", Instant.class);
    /**
     * The meta-property for the {@code correctionFromInstant} property.
     */
    private final MetaProperty<Instant> _correctionFromInstant = DirectMetaProperty.ofReadWrite(this, "correctionFromInstant", Instant.class);
    /**
     * The meta-property for the {@code correctionToInstant} property.
     */
    private final MetaProperty<Instant> _correctionToInstant = DirectMetaProperty.ofReadWrite(this, "correctionToInstant", Instant.class);
    /**
     * The meta-property for the {@code exchange} property.
     */
    private final MetaProperty<ManageableExchange> _exchange = DirectMetaProperty.ofReadWrite(this, "exchange", ManageableExchange.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap();
      temp.put("exchangeId", _exchangeId);
      temp.put("versionFromInstant", _versionFromInstant);
      temp.put("versionToInstant", _versionToInstant);
      temp.put("correctionFromInstant", _correctionFromInstant);
      temp.put("correctionToInstant", _correctionToInstant);
      temp.put("exchange", _exchange);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public ExchangeDocument createBean() {
      return new ExchangeDocument();
    }

    @Override
    public Class<? extends ExchangeDocument> beanType() {
      return ExchangeDocument.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code exchangeId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueIdentifier> exchangeId() {
      return _exchangeId;
    }

    /**
     * The meta-property for the {@code versionFromInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> versionFromInstant() {
      return _versionFromInstant;
    }

    /**
     * The meta-property for the {@code versionToInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> versionToInstant() {
      return _versionToInstant;
    }

    /**
     * The meta-property for the {@code correctionFromInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> correctionFromInstant() {
      return _correctionFromInstant;
    }

    /**
     * The meta-property for the {@code correctionToInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> correctionToInstant() {
      return _correctionToInstant;
    }

    /**
     * The meta-property for the {@code exchange} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ManageableExchange> exchange() {
      return _exchange;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
