/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.math.minimization;

import com.opengamma.math.function.Function1D;

/**
 * 
 */
public interface Minimizer1DWithSecondDerivative extends MinimizerWithSecondDerivative<Function1D<Double, Double>, Function1D<Double, Double>, Function1D<Double, Double>, Double> {
  double minimize(Function1D<Double, Double> f, Function1D<Double, Double> fPrime, Function1D<Double, Double> fDoublePrime, double startPosition, double lowerBound, double upperBound);
}
