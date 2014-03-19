/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.bondcurves.inflationbondcurves;

import static com.opengamma.engine.value.ValueRequirementNames.CLEAN_PRICE;

import com.opengamma.analytics.financial.provider.calculator.inflation.CleanRealPriceFromCurvesCalculator;
import com.opengamma.analytics.financial.provider.calculator.issuer.CleanPriceFromCurvesCalculator;
import com.opengamma.analytics.financial.provider.description.inflation.InflationProviderInterface;
import com.opengamma.core.security.Security;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.financial.security.bond.BondSecurity;

/**
 * Calculates the clean price of a bond from yield curves.
 */
public class InflationBondCleanPriceFromCurvesFunction extends InflationBondFromCurvesFunction<InflationProviderInterface, Double> {

  /**
   * Sets the value requirement name to {@link ValueRequirementNames#CLEAN_PRICE} and
   * the calculator to {@link CleanPriceFromCurvesCalculator}.
   */
  public InflationBondCleanPriceFromCurvesFunction() {
    super(CLEAN_PRICE, CleanRealPriceFromCurvesCalculator.getInstance());
  }

  @Override
  public boolean canApplyTo(final FunctionCompilationContext context, final ComputationTarget target) {
    final Security security = target.getTrade().getSecurity();
    return security instanceof BondSecurity;
  }

}