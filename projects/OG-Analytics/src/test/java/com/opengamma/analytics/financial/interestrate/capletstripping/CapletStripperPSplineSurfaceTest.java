package com.opengamma.analytics.financial.interestrate.capletstripping;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunction;
import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunctionProvider;
import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunctionProviderFromVolSurface;
import com.opengamma.analytics.financial.model.volatility.surface.BasisSplineVolatilitySurfaceProvider;
import com.opengamma.analytics.financial.model.volatility.surface.VolatilitySurface;
import com.opengamma.analytics.financial.model.volatility.surface.VolatilitySurfaceProvider;
import com.opengamma.analytics.math.interpolation.PenaltyMatrixGenerator;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.analytics.math.matrix.MatrixAlgebra;
import com.opengamma.analytics.math.matrix.OGMatrixAlgebra;
import com.opengamma.analytics.math.minimization.PositiveOrZero;
import com.opengamma.analytics.util.AssertMatrix;
import com.opengamma.util.test.TestGroup;

/**
 * This currently does not work
 */
@Test(groups=TestGroup.UNIT)
public class CapletStripperPSplineSurfaceTest extends CapletStrippingSetup {

  private static final MatrixAlgebra MA = new OGMatrixAlgebra();

  @SuppressWarnings("deprecation")
  @Test//(enabled = false)
  public void test() {

    MultiCapFloorPricer pricer = new MultiCapFloorPricer(getAllCaps(), getYieldCurves());

    double lambda = 1;
    CapletStripper stripper = new CapletStripperPSplineSurface(pricer, 0.0, 0.12, 10, 2, 0.0, 10.0, 10, 2, lambda);

    int size = 11 * 11;
    DoubleMatrix1D guess = new DoubleMatrix1D(size, 0.5);

    double[] vols = getAllCapVols();
    int nVols = vols.length;
    double[] errors = new double[nVols];
    Arrays.fill(errors, 1e-4); // 1bps
    @SuppressWarnings("unused")
    CapletStrippingResult res = stripper.solve(vols, MarketDataType.VOL, errors, guess);
    System.out.println(res);
    // System.out.println(res.getChiSq());
    // assertEquals(expChi2[i], res.getChiSq(), 1e-13);
    // res.printSurface(System.out, 101, 101);
  }

  @Test//(enabled = false)
  public void debugTest() {
    MultiCapFloorPricer pricer = new MultiCapFloorPricer(getAllCaps(), getYieldCurves());

    int tSize = 3;
    int kSize = 3;
    int tDegree = 1;
    int kDegree = 1;
    double kMax = 0.12;
    double tMax = 10.0;
    int tKnots = tSize + tDegree - 1;
    int kKnots = kSize + kDegree - 1;
    double tLambda = 100;
    double kLambda = 100;

    VolatilitySurfaceProvider vsp = new BasisSplineVolatilitySurfaceProvider(0.0, kMax, kSize, kDegree, 0.0, tMax,
        tSize, tDegree);
    int n = vsp.getNumModelParameters();
    DoubleMatrix1D x = new DoubleMatrix1D(n);
    for (int i = 0; i < kKnots; i++) {
      double k = i / (kKnots - 1.0);
      for (int j = 0; j < tKnots; j++) {
        double t = j / (tKnots - 1.0);
        double w = 0.7;// 0.2 + 0.4 * k + 0.7 * t + t * k + 0.1*t*t;
        x.getData()[i * tKnots + j] = w;
      }
      // x.getData()[i * tKnots + 0] = 1.0;
      // x.getData()[i * tKnots + 5] = 1.0;
      // x.getData()[i * tKnots + 8] = 1.0;
    }
    int nStrikePoints = 101;
    int nExpPoints = 101;
    double[] strikes = new double[nStrikePoints];
    double[] times = new double[nExpPoints];
    VolatilitySurface vs = vsp.getVolSurface(x);

    for (int i = 0; i < nStrikePoints; i++) {
      strikes[i] = -0.05 + 0.22 * i / (nStrikePoints - 1.0);
    }
    // System.out.println();
    // for (int j = 0; j < nExpPoints; j++) {
    // times[j] = -5 + 20.0 * j / (nExpPoints - 1.0);
    // System.out.print("\t" + times[j]);
    // }
    //
    // for (int i = 0; i < nStrikePoints; i++) {
    // System.out.print("\n" + strikes[i]);
    // for (int j = 0; j < nExpPoints; j++) {
    // Double vol = vs.getVolatility(times[j], strikes[i]);
    // System.out.print("\t" + vol);
    // }
    // }
    // System.out.println("\n");

    DiscreteVolatilityFunctionProvider dvfp = new DiscreteVolatilityFunctionProviderFromVolSurface(vsp);
    DiscreteVolatilityFunction func = dvfp.from(pricer.getExpiryStrikeArray());
    System.out.println(func.evaluate(x));
    DoubleMatrix2D jac = func.calculateJacobian(x);
    DoubleMatrix2D jacFD = func.calculateJacobianViaFD(x);
    AssertMatrix.assertEqualsMatrix(jac, jacFD, 1e-9);

    DoubleMatrix2D penalty = PenaltyMatrixGenerator.getPenaltyMatrix(new int[] {kKnots, tKnots }, new int[] {1, 1 },
        new double[] {kLambda, tLambda });
    // double p = MA.getInnerProduct(x, MA.multiply(penalty, x));
    // System.out.println(penalty);
    // System.out.println(p);
    CapletStrippingCore imp = new CapletStrippingCore(pricer, dvfp);
    double[] vols = getAllCapVols();
    int nVols = vols.length;
    double[] errors = new double[nVols];
    Arrays.fill(errors, 1e-4); // 1bps
    CapletStrippingResult res = imp.solveForCapVols(vols, errors, x, penalty, new PositiveOrZero());
    System.out.println(res);

    DoubleMatrix1D xFit = res.getFitParameters();
    double p = MA.getInnerProduct(xFit, MA.multiply(penalty, xFit));
    System.out.println(p);

    vs = vsp.getVolSurface(xFit);
    for (int i = 0; i < nStrikePoints; i++) {
      strikes[i] = 0.0 + 0.12 * i / (nStrikePoints - 1.0);
    }
    System.out.println();
    for (int j = 0; j < nExpPoints; j++) {
      times[j] = 0 + 10.0 * j / (nExpPoints - 1.0);
      System.out.print("\t" + times[j]);
    }

    for (int i = 0; i < nStrikePoints; i++) {
      System.out.print("\n" + strikes[i]);
      for (int j = 0; j < nExpPoints; j++) {
        Double vol = vs.getVolatility(times[j], strikes[i]);
        System.out.print("\t" + vol);
      }
    }
    System.out.println("\n");

  }
}
