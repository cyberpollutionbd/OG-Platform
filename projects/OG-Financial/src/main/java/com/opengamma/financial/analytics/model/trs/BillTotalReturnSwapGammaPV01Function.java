/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.trs;

import static com.opengamma.engine.value.ValueRequirementNames.GAMMA_PV01;

import java.util.Collections;
import java.util.Set;

import org.threeten.bp.Instant;

import com.google.common.collect.Iterables;
import com.opengamma.analytics.financial.forex.method.FXMatrix;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.bond.calculator.BondBillTrsGammaPV01Calculator;
import com.opengamma.analytics.financial.provider.description.interestrate.IssuerProviderInterface;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.function.CompiledFunctionDefinition;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.function.FunctionExecutionContext;
import com.opengamma.engine.function.FunctionInputs;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueProperties;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.financial.security.swap.BillTotalReturnSwapSecurity;

/**
 * Calculates the gamma PV01 of a bond total return swap security. The value is returned in
 * the currency of the asset.
 */
public class BillTotalReturnSwapGammaPV01Function extends BillTotalReturnSwapFunction {

  /**
   * Sets the value requirement to {@link ValueRequirementNames#GAMMA_PV01}.
   */
  public BillTotalReturnSwapGammaPV01Function() {
    super(GAMMA_PV01);
  }

  @Override
  public CompiledFunctionDefinition compile(final FunctionCompilationContext context, final Instant atInstant) {
    return new BillTotalReturnSwapCompiledFunction(getTargetToDefinitionConverter(context), getDefinitionToDerivativeConverter(context), true) {

      @Override
      protected Set<ComputedValue> getValues(final FunctionExecutionContext executionContext, final FunctionInputs inputs, final ComputationTarget target,
          final Set<ValueRequirement> desiredValues, final InstrumentDerivative derivative, final FXMatrix fxMatrix) {
        final ValueProperties properties = Iterables.getOnlyElement(desiredValues).getConstraints().copy().get();
        final ValueSpecification spec = new ValueSpecification(GAMMA_PV01, target.toSpecification(), properties);
        final IssuerProviderInterface issuerCurves = getMergedWithIssuerProviders(inputs, fxMatrix);
        final Double gammaPV01 = derivative.accept(BondBillTrsGammaPV01Calculator.getInstance(), issuerCurves);
        return Collections.singleton(new ComputedValue(spec, gammaPV01));
      }

      @Override
      protected String getCurrencyOfResult(final BillTotalReturnSwapSecurity security) {
        return security.getNotionalCurrency().getCode();
      }
    };
  }

}
