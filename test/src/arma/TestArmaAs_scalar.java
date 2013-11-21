/*******************************************************************************
 * Copyright 2013 Sebastian Niemann <niemann@sra.uni-hannover.de> and contributors.
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *******************************************************************************/
package arma;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

/**
 * @author Sebastian Niemann <niemann@sra.uni-hannover.de>
 */
public class TestArmaAs_scalar {

  /**
   * @throws IOException 
   */
  @Test
  public void testAs_scalar() throws IOException {
    Mat input = new Mat();
    input.load("./test/data/input/elementwise.miscellaneous.mat");
    
    Mat testMatrix;

    for (double testValue : input) {
      testMatrix = new Mat(new double[]{testValue});
      assertEquals(testValue, Arma.as_scalar(testMatrix), 0);
      
      testMatrix = new Mat(new double[]{-testValue});
      assertEquals(-testValue, Arma.as_scalar(testMatrix), 0);
    }
  }
}
