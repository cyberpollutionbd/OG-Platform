/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.future.calculator;

import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitorAdapter;
import com.opengamma.analytics.financial.interestrate.annuity.derivative.AnnuityPaymentFixed;
import com.opengamma.analytics.financial.interestrate.future.derivative.SwapFuturesPriceDeliverableSecurity;
import com.opengamma.analytics.financial.model.interestrate.HullWhiteOneFactorPiecewiseConstantInterestRateModel;
import com.opengamma.analytics.financial.model.interestrate.definition.HullWhiteOneFactorPiecewiseConstantParameters;
import com.opengamma.analytics.financial.provider.calculator.discounting.CashFlowEquivalentCalculator;
import com.opengamma.analytics.financial.provider.description.interestrate.HullWhiteOneFactorProviderInterface;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderInterface;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;

/**
 * Computes the par rate for different instrument. The meaning of "par rate" is instrument dependent.
 */
public final class FuturesPriceHullWhiteCalculator extends InstrumentDerivativeVisitorAdapter<HullWhiteOneFactorProviderInterface, Double> {

  /**
   * The unique instance of the calculator.
   */
  private static final FuturesPriceHullWhiteCalculator INSTANCE = new FuturesPriceHullWhiteCalculator();

  /**
   * Gets the calculator instance.
   * @return The calculator.
   */
  public static FuturesPriceHullWhiteCalculator getInstance() {
    return INSTANCE;
  }

  /**
   * Constructor.
   */
  private FuturesPriceHullWhiteCalculator() {
  }

  /**
   * The Hull-White model.
   */
  private static final HullWhiteOneFactorPiecewiseConstantInterestRateModel MODEL = new HullWhiteOneFactorPiecewiseConstantInterestRateModel();
  /**
   * The cash flow equivalent calculator used in computations.
   */
  private static final CashFlowEquivalentCalculator CFEC = CashFlowEquivalentCalculator.getInstance();

  //     -----     Futures     -----

  @Override
  public Double visitSwapFuturesPriceDeliverableSecurity(final SwapFuturesPriceDeliverableSecurity futures, final HullWhiteOneFactorProviderInterface multicurve) {
    ArgumentChecker.notNull(futures, "Future");
    ArgumentChecker.notNull(multicurve, "Multi-curves with Hull-White");
    final Currency ccy = futures.getCurrency();
    ArgumentChecker.isTrue(multicurve.getHullWhiteCurrency().equals(ccy), "Futures currency incompatible with data");
    final HullWhiteOneFactorPiecewiseConstantParameters parameters = multicurve.getHullWhiteParameters();
    final MulticurveProviderInterface multicurves = multicurve.getMulticurveProvider();
    final AnnuityPaymentFixed cfe = futures.getUnderlyingSwap().accept(CFEC, multicurves);
    final int nbCf = cfe.getNumberOfPayments();
    final double[] adjustments = new double[nbCf];
    final double[] df = new double[nbCf];
    for (int loopcf = 0; loopcf < nbCf; loopcf++) {
      adjustments[loopcf] = MODEL.futuresConvexityFactor(parameters, futures.getTradingLastTime(), cfe.getNthPayment(loopcf).getPaymentTime(), futures.getDeliveryTime());
      df[loopcf] = multicurves.getDiscountFactor(ccy, cfe.getNthPayment(loopcf).getPaymentTime());
    }
    double price = 1.0;
    for (int loopcf = 0; loopcf < nbCf; loopcf++) {
      price += (cfe.getNthPayment(loopcf).getAmount() * df[loopcf] * adjustments[loopcf]) / df[0];
    }
    return price;
  }

}
