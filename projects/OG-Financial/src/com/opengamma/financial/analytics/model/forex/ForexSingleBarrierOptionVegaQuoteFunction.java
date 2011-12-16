/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.forex;

import java.util.Collections;
import java.util.Set;

import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.function.FunctionInputs;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueProperties;
import com.opengamma.engine.value.ValuePropertyNames;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.financial.analytics.model.VegaMatrixHelper;
import com.opengamma.financial.analytics.volatility.surface.RawVolatilitySurfaceDataFunction;
import com.opengamma.financial.forex.calculator.PresentValueForexVegaQuoteSensitivityCalculator;
import com.opengamma.financial.forex.method.PresentValueVolatilityQuoteSensitivityDataBundle;
import com.opengamma.financial.interestrate.InstrumentDerivative;
import com.opengamma.financial.model.option.definition.SmileDeltaTermStructureDataBundle;

/**
 * 
 */
public class ForexSingleBarrierOptionVegaQuoteFunction extends ForexSingleBarrierOptionFunction {

  private static final PresentValueForexVegaQuoteSensitivityCalculator CALCULATOR = PresentValueForexVegaQuoteSensitivityCalculator.getInstance();

  public ForexSingleBarrierOptionVegaQuoteFunction(final String putFundingCurveName, final String putForwardCurveName, final String callFundingCurveName, final String callForwardCurveName,
      final String surfaceName) {
    super(putFundingCurveName, putForwardCurveName, callFundingCurveName, callForwardCurveName, surfaceName);
  }

  @Override
  protected Set<ComputedValue> getResult(final InstrumentDerivative fxSingleBarrierOption, final SmileDeltaTermStructureDataBundle data, final FunctionInputs inputs, final ComputationTarget target) {
    final PresentValueVolatilityQuoteSensitivityDataBundle result = CALCULATOR.visit(fxSingleBarrierOption, data);
    return Collections.singleton(new ComputedValue(getResultSpec(target), VegaMatrixHelper.getVegaFXQuoteMatrixInStandardForm(result)));
  }

  @Override
  public Set<ValueSpecification> getResults(final FunctionCompilationContext context, final ComputationTarget target) {
    return Collections.singleton(getResultSpec(target));
  }
  
  private ValueSpecification getResultSpec(final ComputationTarget target) {
    final ValueProperties properties = createValueProperties().with(ValuePropertyNames.PAY_CURVE, getPutFundingCurveName())
                                                              .with(ValuePropertyNames.RECEIVE_CURVE, getCallFundingCurveName())
                                                              .with(ValuePropertyNames.SURFACE, getSurfaceName())
                                                              .with(RawVolatilitySurfaceDataFunction.PROPERTY_SURFACE_INSTRUMENT_TYPE, "FX_VANILLA_OPTION").get();
    return new ValueSpecification(ValueRequirementNames.VEGA_QUOTE_MATRIX, target.toSpecification(), properties);
  }

}
