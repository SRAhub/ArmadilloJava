/*******************************************************************************
 * Copyright 2013-2014 Sebastian Niemann <niemann@sra.uni-hannover.de>.
 * 
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://opensource.org/licenses/MIT
 * 
 * Developers:
 * Sebastian Niemann - Lead developer
 * Daniel Kiechle - Unit testing
 ******************************************************************************/
package org.armadillojava;

import static org.armadillojava.TestUtil.assertMatEquals;
import static org.junit.Assume.assumeThat;
import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestGenMatGenMat extends TestClass {

  @Parameters(name = "{index}: GenMatA = {0}, GenMatB = {2}")
  public static Collection<Object[]> getParameters() {
    List<InputClass> inputClasses = new ArrayList<>();

    inputClasses.add(InputClass.GenMat);
    inputClasses.add(InputClass.GenMat);

    return Input.getTestParameters(inputClasses);
  }

  @Parameter(0)
  public String _genMatAString;

  @Parameter(1)
  public Mat    _genMatA;

  protected Mat _copyOfGenMatA;

  @Parameter(2)
  public String _genMatBString;

  @Parameter(3)
  public Mat    _genMatB;

  protected Mat _copyOfGenMatB;

  @Before
  public void before() {
    _fileSuffix = _genMatAString + "," + _genMatBString;

    _copyOfGenMatA = new Mat(_genMatA);
    _copyOfGenMatB = new Mat(_genMatB);
  }

  @After
  public void after() {
    assertMatEquals(_genMatA, _copyOfGenMatA, 0);
    assertMatEquals(_genMatB, _copyOfGenMatB, 0);
  }

  @Test
  public void testArmaMin() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));

    assertMatEquals(Arma.min(_genMatA, _genMatB), load("Arma.min"));
  }

  @Test
  public void testArmaMax() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));

    assertMatEquals(Arma.max(_genMatA, _genMatB), load("Arma.max"));
  }

  @Test
  public void testArmaCor() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));

    assertMatEquals(Arma.cor(_genMatA, _genMatB), load("Arma.cor"));
  }

  @Test
  public void testArmaCov() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));

    assertMatEquals(Arma.cov(_genMatA, _genMatB), load("Arma.cov"));
  }

  @Test
  public void testArmaCross() throws IOException {
    Mat tempGenMatA = new Mat(_genMatA);
    tempGenMatA.resize(3, 1);
    Mat tempGenMatB = new Mat(_genMatB);
    tempGenMatB.resize(3, 1);

    assertMatEquals(Arma.cross(tempGenMatA, tempGenMatB), load("Arma.cross"));
  }

  @Test
  public void testArmaJoin_rows() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));

    assertMatEquals(Arma.join_rows(_genMatA, _genMatB), load("Arma.join_rows"));
  }

  @Test
  public void testArmaJoin_horiz() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));

    assertMatEquals(Arma.join_horiz(_genMatA, _genMatB), load("Arma.join_horiz"));
  }

  @Test
  public void testArmaJoin_cols() throws IOException {
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));

    assertMatEquals(Arma.join_cols(_genMatA, _genMatB), load("Arma.join_cols"));
  }

  @Test
  public void testArmaJoin_vert() throws IOException {
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));

    assertMatEquals(Arma.join_vert(_genMatA, _genMatB), load("Arma.join_vert"));
  }

  @Test
  public void testArmaKron() throws IOException {
    assertMatEquals(Arma.kron(_genMatA, _genMatB), load("Arma.kron"));
  }

  @Test
  public void testArmaSolveA() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));

    if(_genMatA.is_square() && Arma.rank(_genMatA) == _genMatA.n_rows) {
      assertMatEquals(Arma.solve(_genMatA, _genMatB), Arma.inv(_genMatA).times(_genMatB), 1e-11);
    }
  }

  @Test
  public void testArmaSolveB() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));

    Mat X = new Mat();
    Arma.solve(X, _genMatA, _genMatB);

    if(_genMatA.is_square() && Arma.rank(_genMatA) == _genMatA.n_rows) {
      assertMatEquals(X, Arma.inv(_genMatA).times(_genMatB), 1e-11);
    }
  }

  @Test
  public void testMatPlus() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));
    
    assertMatEquals(_genMatA.plus(_genMatB), load("Mat.plus"));
  }

  @Test
  public void testMatMinus() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));
    
    assertMatEquals(_genMatA.minus(_genMatB), load("Mat.minus"));
  }
  
  @Test
  public void testMatTimes() throws IOException {
    assumeThat(_genMatA.n_cols, is(_genMatB.n_rows));
    assumeThat(_genMatA.is_finite(), is(true));
    assumeThat(_genMatB.is_finite(), is(true));
    
    assertMatEquals(_genMatA.times(_genMatB), load("Mat.times"));
  }

  @Test
  public void testMatElemTimes() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));
    
    assertMatEquals(_genMatA.elemTimes(_genMatB), load("Mat.elemTimes"));
  }

  @Test
  public void testMatElemDivide() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));
    
    assertMatEquals(_genMatA.elemDivide(_genMatB), load("Mat.elemDivide"));
  }

  @Test
  public void testMatEquals() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));
    
    assertMatEquals(_genMatA.equals(_genMatB), load("Mat.equals"));
  }

  @Test
  public void testMatNonEquals() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));
    
    assertMatEquals(_genMatA.nonEquals(_genMatB), load("Mat.nonEquals"));
  }

  @Test
  public void testMatGreaterThan() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));
    
    assertMatEquals(_genMatA.greaterThan(_genMatB), load("Mat.greaterThan"));
  }

  @Test
  public void testMatLessThan() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));
    
    assertMatEquals(_genMatA.lessThan(_genMatB), load("Mat.lessThan"));
  }

  @Test
  public void testMatStrictGreaterThan() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));
    
    assertMatEquals(_genMatA.strictGreaterThan(_genMatB), load("Mat.strictGreaterThan"));
  }

  @Test
  public void testMatStrictLessThan() throws IOException {
    assumeThat(_genMatA.n_rows, is(_genMatB.n_rows));
    assumeThat(_genMatA.n_cols, is(_genMatB.n_cols));
    
    assertMatEquals(_genMatA.strictLessThan(_genMatB), load("Mat.strictLessThan"));
  }

}
