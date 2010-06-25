/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.model.cashflow;

import com.opengamma.financial.model.interestrate.InterestRateModel;

/**
 * 
 */
public class DiscreteCompoundingPresentValueCalculator extends PresentValueCalculator {
  // TODO this is correct for now, but when we have a bond cashflow instrument,
  // it will need to take number of payments per year into account

  @Override
  public double calculate(final double t, final double c, final InterestRateModel<Double> rates) {
    if (t < 0) {
      throw new IllegalArgumentException("Time must be positive");
    }
    if (rates == null) {
      throw new IllegalArgumentException("Rates model was null");
    }
    return c * Math.pow(1 + rates.getInterestRate(t), -t);
  }
}
