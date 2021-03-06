/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.marketdata.scenarios;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableSet;
import com.opengamma.sesame.MulticurveBundle;
import com.opengamma.sesame.marketdata.MarketDataId;
import com.opengamma.sesame.marketdata.MulticurveId;

/**
 * Filter that matches all curves in a {@link MulticurveBundle}.
 */
@BeanDefinition
public final class AllCurvesMulticurveFilter implements MarketDataFilter, ImmutableBean {

  /** Single instance of this filter. It has no state so there is no need to create multiple instances. */
  public static final AllCurvesMulticurveFilter INSTANCE = new AllCurvesMulticurveFilter();

  private Set<MulticurveMatchDetails> apply(MulticurveMetadata metadata) {
    Set<String> curveNames = metadata.getCurveNames();
    ImmutableSet.Builder<MulticurveMatchDetails> builder = ImmutableSet.builder();

    for (String curveName : curveNames) {
      builder.add(StandardMatchDetails.multicurve(curveName));
    }
    return builder.build();
  }

  @Override
  public Set<MulticurveMatchDetails> apply(MarketDataId<?> marketDataId) {
    MulticurveId id = (MulticurveId) marketDataId;
    MulticurveMetadata metadata = MulticurveMetadata.forConfiguration(id.resolveConfig());
    return apply(metadata);
  }

  @Override
  public Set<MulticurveMatchDetails> apply(MarketDataId<?> marketDataId, Object marketData) {
    MulticurveBundle multicurve = (MulticurveBundle) marketData;
    MulticurveMetadata metadata = MulticurveMetadata.forMulticurve(multicurve);
    return apply(metadata);
  }

  @Override
  public Class<?> getMarketDataType() {
    return MulticurveBundle.class;
  }

  @Override
  public Class<MulticurveId> getMarketDataIdType() {
    return MulticurveId.class;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AllCurvesMulticurveFilter}.
   * @return the meta-bean, not null
   */
  public static AllCurvesMulticurveFilter.Meta meta() {
    return AllCurvesMulticurveFilter.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(AllCurvesMulticurveFilter.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static AllCurvesMulticurveFilter.Builder builder() {
    return new AllCurvesMulticurveFilter.Builder();
  }

  private AllCurvesMulticurveFilter() {
  }

  @Override
  public AllCurvesMulticurveFilter.Meta metaBean() {
    return AllCurvesMulticurveFilter.Meta.INSTANCE;
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
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("AllCurvesMulticurveFilter{");
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AllCurvesMulticurveFilter}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null);

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    public AllCurvesMulticurveFilter.Builder builder() {
      return new AllCurvesMulticurveFilter.Builder();
    }

    @Override
    public Class<? extends AllCurvesMulticurveFilter> beanType() {
      return AllCurvesMulticurveFilter.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code AllCurvesMulticurveFilter}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<AllCurvesMulticurveFilter> {

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      throw new NoSuchElementException("Unknown property: " + propertyName);
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      throw new NoSuchElementException("Unknown property: " + propertyName);
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
    public AllCurvesMulticurveFilter build() {
      return new AllCurvesMulticurveFilter();
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      return "AllCurvesMulticurveFilter.Builder{}";
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
