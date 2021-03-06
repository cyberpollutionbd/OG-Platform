/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.marketdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
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

import com.google.common.collect.ImmutableList;
import com.opengamma.engine.marketdata.spec.MarketDataSpecification;

/**
 * Specifies that market data should be requested from multiple data sources in order.
 * <p>
 * If a data source returns a value it is used. If not, the next data source is tried.
 */
@BeanDefinition
public final class CompositeMarketDataSpecification implements MarketDataSpecification, ImmutableBean {

  @PropertyDefinition(validate = "notNull")
  private final ImmutableList<MarketDataSpecification> _specifications;

  /**
   * Creates an instance from multiple underlying market data specifications.
   *
   * @param specification specification of the first market data source
   * @param specifications specifications of the other market data sources
   * @return a data source specifying that data should be requested from multiple sources in order, and the first
   *   available value should be used
   */
  public static CompositeMarketDataSpecification of(MarketDataSpecification specification,
                                                    MarketDataSpecification... specifications) {
    ImmutableList<MarketDataSpecification> specs =
        ImmutableList.<MarketDataSpecification>builder()
            .add(specification)
            .addAll(Arrays.asList(specifications))
            .build();
    return of(specs);
  }

  public static CompositeMarketDataSpecification of(List<MarketDataSpecification> specifications) {
    return new CompositeMarketDataSpecification(ImmutableList.copyOf(specifications));
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CompositeMarketDataSpecification}.
   * @return the meta-bean, not null
   */
  public static CompositeMarketDataSpecification.Meta meta() {
    return CompositeMarketDataSpecification.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CompositeMarketDataSpecification.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static CompositeMarketDataSpecification.Builder builder() {
    return new CompositeMarketDataSpecification.Builder();
  }

  private CompositeMarketDataSpecification(
      List<MarketDataSpecification> specifications) {
    JodaBeanUtils.notNull(specifications, "specifications");
    this._specifications = ImmutableList.copyOf(specifications);
  }

  @Override
  public CompositeMarketDataSpecification.Meta metaBean() {
    return CompositeMarketDataSpecification.Meta.INSTANCE;
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
   * Gets the specifications.
   * @return the value of the property, not null
   */
  public ImmutableList<MarketDataSpecification> getSpecifications() {
    return _specifications;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CompositeMarketDataSpecification other = (CompositeMarketDataSpecification) obj;
      return JodaBeanUtils.equal(getSpecifications(), other.getSpecifications());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getSpecifications());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("CompositeMarketDataSpecification{");
    buf.append("specifications").append('=').append(JodaBeanUtils.toString(getSpecifications()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CompositeMarketDataSpecification}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code specifications} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<ImmutableList<MarketDataSpecification>> _specifications = DirectMetaProperty.ofImmutable(
        this, "specifications", CompositeMarketDataSpecification.class, (Class) ImmutableList.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "specifications");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1868423120:  // specifications
          return _specifications;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public CompositeMarketDataSpecification.Builder builder() {
      return new CompositeMarketDataSpecification.Builder();
    }

    @Override
    public Class<? extends CompositeMarketDataSpecification> beanType() {
      return CompositeMarketDataSpecification.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code specifications} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ImmutableList<MarketDataSpecification>> specifications() {
      return _specifications;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1868423120:  // specifications
          return ((CompositeMarketDataSpecification) bean).getSpecifications();
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
   * The bean-builder for {@code CompositeMarketDataSpecification}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<CompositeMarketDataSpecification> {

    private List<MarketDataSpecification> _specifications = new ArrayList<MarketDataSpecification>();

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(CompositeMarketDataSpecification beanToCopy) {
      this._specifications = new ArrayList<MarketDataSpecification>(beanToCopy.getSpecifications());
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1868423120:  // specifications
          return _specifications;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 1868423120:  // specifications
          this._specifications = (List<MarketDataSpecification>) newValue;
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
    public CompositeMarketDataSpecification build() {
      return new CompositeMarketDataSpecification(
          _specifications);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code specifications} property in the builder.
     * @param specifications  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder specifications(List<MarketDataSpecification> specifications) {
      JodaBeanUtils.notNull(specifications, "specifications");
      this._specifications = specifications;
      return this;
    }

    /**
     * Sets the {@code specifications} property in the builder
     * from an array of objects.
     * @param specifications  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder specifications(MarketDataSpecification... specifications) {
      return specifications(Arrays.asList(specifications));
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("CompositeMarketDataSpecification.Builder{");
      buf.append("specifications").append('=').append(JodaBeanUtils.toString(_specifications));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
