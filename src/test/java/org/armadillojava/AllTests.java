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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Sebastian Niemann <niemann@sra.uni-hannovr.de>
 */
@RunWith(Suite.class)
@SuiteClasses({
  TestDatum.class,
  TestGenColVec.class,
  TestGenColVecMonColVec.class,
  TestGenColVecMonRowVec.class,
  TestGenColVecSort.class,
  TestGenDouble.class,
  TestGenMat.class,
  TestGenMatDim.class,
  TestGenMatExp.class,
  TestGenMatMonColVec.class,
  TestGenMatMonColVecDim.class,
  TestGenMatMonRowVec.class,
  TestGenMatMonRowVecDim.class,
  TestGenMatNormal.class,
  TestGenMatSort.class,
  TestGenMatSortDim.class,
  TestGenRowVec.class,
  TestGenRowVecMonColVec.class,
  TestGenRowVecMonRowVec.class,
  TestGenRowVecSort.class,
  TestLogicColVec.class,
  TestLogicMat.class,
  TestLogicMatDim.class,
  TestLogicRowVec.class,
  TestNumElems.class,
  TestNumRowsNumCols.class,
  TestOOColVec.class,
  TestOOMat.class,
  TestOORowVec.class,
  TestSquMat.class,
  TestWallClock.class
})
public class AllTests {}
