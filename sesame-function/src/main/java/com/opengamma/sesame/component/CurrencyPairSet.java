/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.component;

import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableSet;
import com.opengamma.financial.currency.CurrencyPair;

/**
 * Temporary bean to represent a set of currency pairs in a Joda bean so
 * it can be deserialised correctly.
 * This is required due to: http://jira.fudgemsg.org/browse/FRJ-128
 */
@BeanDefinition(builderScope = "private")
public final class CurrencyPairSet implements ImmutableBean {

  /**
   * The required set of currency pairs.
   */
  @PropertyDefinition(validate = "notNull")
  private final Set<CurrencyPair> _currencyPairs;

  /**
   * Create an instance with the specified set of currency pairs.
   *
   * @param currencyPairs the currency pairs required in the set
   * @return a CurrencyPairSet
   */
  public static CurrencyPairSet of(CurrencyPair... currencyPairs) {
    return new CurrencyPairSet(ImmutableSet.copyOf(currencyPairs));
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CurrencyPairSet}.
   * @return the meta-bean, not null
   */
  public static CurrencyPairSet.Meta meta() {
    return CurrencyPairSet.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CurrencyPairSet.Meta.INSTANCE);
  }

  private CurrencyPairSet(
      Set<CurrencyPair> currencyPairs) {
    JodaBeanUtils.notNull(currencyPairs, "currencyPairs");
    this._currencyPairs = ImmutableSet.copyOf(currencyPairs);
  }

  @Override
  public CurrencyPairSet.Meta metaBean() {
    return CurrencyPairSet.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the required set of currency pairs.
   * @return the value of the property, not null
   */
  public Set<CurrencyPair> getCurrencyPairs() {
    return _currencyPairs;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CurrencyPairSet other = (CurrencyPairSet) obj;
      return JodaBeanUtils.equal(getCurrencyPairs(), other.getCurrencyPairs());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getCurrencyPairs());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("CurrencyPairSet{");
    buf.append("currencyPairs").append('=').append(JodaBeanUtils.toString(getCurrencyPairs()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CurrencyPairSet}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code currencyPairs} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Set<CurrencyPair>> _currencyPairs = DirectMetaProperty.ofImmutable(
        this, "currencyPairs", CurrencyPairSet.class, (Class) Set.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "currencyPairs");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1094810440:  // currencyPairs
          return _currencyPairs;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CurrencyPairSet> builder() {
      return new CurrencyPairSet.Builder();
    }

    @Override
    public Class<? extends CurrencyPairSet> beanType() {
      return CurrencyPairSet.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code currencyPairs} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Set<CurrencyPair>> currencyPairs() {
      return _currencyPairs;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1094810440:  // currencyPairs
          return ((CurrencyPairSet) bean).getCurrencyPairs();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code CurrencyPairSet}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<CurrencyPairSet> {

    private Set<CurrencyPair> _currencyPairs = new HashSet<CurrencyPair>();

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1094810440:  // currencyPairs
          return _currencyPairs;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 1094810440:  // currencyPairs
          this._currencyPairs = (Set<CurrencyPair>) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public CurrencyPairSet build() {
      return new CurrencyPairSet(
          _currencyPairs);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("CurrencyPairSet.Builder{");
      buf.append("currencyPairs").append('=').append(JodaBeanUtils.toString(_currencyPairs));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
