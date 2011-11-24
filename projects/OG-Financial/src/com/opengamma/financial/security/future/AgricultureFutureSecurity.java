/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.future;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Expiry;

/**
 * A security for agriculture futures.
 */
@BeanDefinition
public class AgricultureFutureSecurity extends CommodityFutureSecurity {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  AgricultureFutureSecurity() { //For builder
    super();
  }

  public AgricultureFutureSecurity(Expiry expiry, String tradingExchange, String settlementExchange, Currency currency, double unitAmount, String commodityType) {
    super(expiry, tradingExchange, settlementExchange, currency, unitAmount, commodityType);
  }

  //-------------------------------------------------------------------------
  @Override
  public <T> T accept(FutureSecurityVisitor<T> visitor) {
    return visitor.visitAgricultureFutureSecurity(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AgricultureFutureSecurity}.
   * @return the meta-bean, not null
   */
  public static AgricultureFutureSecurity.Meta meta() {
    return AgricultureFutureSecurity.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(AgricultureFutureSecurity.Meta.INSTANCE);
  }

  @Override
  public AgricultureFutureSecurity.Meta metaBean() {
    return AgricultureFutureSecurity.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AgricultureFutureSecurity}.
   */
  public static class Meta extends CommodityFutureSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends AgricultureFutureSecurity> builder() {
      return new DirectBeanBuilder<AgricultureFutureSecurity>(new AgricultureFutureSecurity());
    }

    @Override
    public Class<? extends AgricultureFutureSecurity> beanType() {
      return AgricultureFutureSecurity.class;
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
