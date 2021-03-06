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
import static org.hamcrest.Matchers.lessThan;

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
public class TestInPlaceGenMatColIndsGenColVec extends TestClass {

  @Parameters(name = "{index}: GenMat = {0}, ColInds = {2}, GenColVec = {4}")
  public static Collection<Object[]> getParameters() {
    List<InputClass> inputClasses = new ArrayList<>();

    inputClasses.add(InputClass.GenMat);
    inputClasses.add(InputClass.ColInds);
    inputClasses.add(InputClass.GenColVec);

    return Input.getTestParameters(inputClasses);
  }

  @Parameter(0)
  public String _genMatString;

  @Parameter(1)
  public Mat    _genMat;

  protected Mat _copyOfGenMat;

  @Parameter(2)
  public String _colIndsString;

  @Parameter(3)
  public Col    _colInds;

  protected Col _copyOfColInds;

  @Parameter(4)
  public String _genColVecString;

  @Parameter(5)
  public Col    _genColVec;

  protected Col _copyOfGenColVec;

  @Before
  public void before() {
    _fileSuffix = _genMatString + "," + _colIndsString + "," + _genColVecString;

    _copyOfGenMat = new Mat(_genMat);
    _copyOfColInds = new Col(_colInds);
    _copyOfGenColVec = new Col(_genColVec);
  }

  @After
  public void after() {
    _genMat.inPlace(Op.EQUAL, _copyOfGenMat);
    _colInds.inPlace(Op.EQUAL, _copyOfColInds);
    _genColVec.inPlace(Op.EQUAL, _copyOfGenColVec);
  }

  @Test
  public void testMatColsEqual() throws IOException {
    for(int j = 0; j < _colInds.n_elem; j++) {
      assumeThat((int) _colInds.at(j), is(lessThan(_genMat.n_cols)));
    }
    assumeThat(_genColVec.n_rows, is(_genMat.n_rows));
    assumeThat(_colInds.n_elem, is(1));

    _genMat.cols(_colInds, Op.EQUAL, _genColVec);

    assertMatEquals(_genMat, load("Mat.colsEqual"));
  }

  @Test
  public void testMatColsPlus() throws IOException {
    for(int j = 0; j < _colInds.n_elem; j++) {
      assumeThat((int) _colInds.at(j), is(lessThan(_genMat.n_cols)));
    }
    assumeThat(_genColVec.n_rows, is(_genMat.n_rows));
    assumeThat(_colInds.n_elem, is(1));

    _genMat.cols(_colInds, Op.PLUS, _genColVec);

    assertMatEquals(_genMat, load("Mat.colsPlus"));
  }

  @Test
  public void testMatColsMinus() throws IOException {
    for(int j = 0; j < _colInds.n_elem; j++) {
      assumeThat((int) _colInds.at(j), is(lessThan(_genMat.n_cols)));
    }
    assumeThat(_genColVec.n_rows, is(_genMat.n_rows));
    assumeThat(_colInds.n_elem, is(1));

    _genMat.cols(_colInds, Op.MINUS, _genColVec);

    assertMatEquals(_genMat, load("Mat.colsMinus"));
  }

  @Test
  public void testMatColsElemTimes() throws IOException {
    for(int j = 0; j < _colInds.n_elem; j++) {
      assumeThat((int) _colInds.at(j), is(lessThan(_genMat.n_cols)));
    }
    assumeThat(_genColVec.n_rows, is(_genMat.n_rows));
    assumeThat(_colInds.n_elem, is(1));

    _genMat.cols(_colInds, Op.ELEMTIMES, _genColVec);

    assertMatEquals(_genMat, load("Mat.colsElemTimes"));
  }

  @Test
  public void testMatColsElemDivide() throws IOException {
    for(int j = 0; j < _colInds.n_elem; j++) {
      assumeThat((int) _colInds.at(j), is(lessThan(_genMat.n_cols)));
    }
    assumeThat(_genColVec.n_rows, is(_genMat.n_rows));
    assumeThat(_colInds.n_elem, is(1));

    _genMat.cols(_colInds, Op.ELEMDIVIDE, _genColVec);

    assertMatEquals(_genMat, load("Mat.colsElemDivide"));
  }

  @Test
  public void testMatEach_colEqual() throws IOException {
    for(int j = 0; j < _colInds.n_elem; j++) {
      assumeThat((int) _colInds.at(j), is(lessThan(_genMat.n_cols)));
    }
    assumeThat(_genColVec.n_rows, is(_genMat.n_rows));

    _genMat.each_col(_colInds, Op.EQUAL, _genColVec);

    assertMatEquals(_genMat, load("Mat.each_colEqual"));
  }

  @Test
  public void testMatEach_colPlus() throws IOException {
    for(int j = 0; j < _colInds.n_elem; j++) {
      assumeThat((int) _colInds.at(j), is(lessThan(_genMat.n_cols)));
    }
    assumeThat(_genColVec.n_rows, is(_genMat.n_rows));

    _genMat.each_col(_colInds, Op.PLUS, _genColVec);

    assertMatEquals(_genMat, load("Mat.each_colPlus"));
  }

  @Test
  public void testMatEach_colMinus() throws IOException {
    for(int j = 0; j < _colInds.n_elem; j++) {
      assumeThat((int) _colInds.at(j), is(lessThan(_genMat.n_cols)));
    }
    assumeThat(_genColVec.n_rows, is(_genMat.n_rows));

    _genMat.each_col(_colInds, Op.MINUS, _genColVec);

    assertMatEquals(_genMat, load("Mat.each_colMinus"));
  }

  @Test
  public void testMatEach_colElemTimes() throws IOException {
    for(int j = 0; j < _colInds.n_elem; j++) {
      assumeThat((int) _colInds.at(j), is(lessThan(_genMat.n_cols)));
    }
    assumeThat(_genColVec.n_rows, is(_genMat.n_rows));

    _genMat.each_col(_colInds, Op.ELEMTIMES, _genColVec);

    assertMatEquals(_genMat, load("Mat.each_colElemTimes"));
  }

  @Test
  public void testMatEach_colElemDivide() throws IOException {
    for(int j = 0; j < _colInds.n_elem; j++) {
      assumeThat((int) _colInds.at(j), is(lessThan(_genMat.n_cols)));
    }
    assumeThat(_genColVec.n_rows, is(_genMat.n_rows));

    _genMat.each_col(_colInds, Op.ELEMDIVIDE, _genColVec);

    assertMatEquals(_genMat, load("Mat.each_colElemDivide"));
  }

}
