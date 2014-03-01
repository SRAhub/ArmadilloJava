/*******************************************************************************
 * Copyright 2013 Sebastian Niemann <niemann@sra.uni-hannover.de> and contributors.
 * 
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://opensource.org/licenses/MIT
 *******************************************************************************/

package org.armadillojava;

/**
 * Provides shallow matrix sub views of {@link AbstractMat}.
 * 
 * @author Sebastian Niemann <niemann@sra.uni-hannover.de>
 */
class ViewSubMat extends AbstractView {

  /**
   * The first position of the sub view within the underlying matrix
   */
  protected final int _firstPosition;

  /**
   * The rows to skip within the underlying matrix to move from the last position of a column to one position before
   * the first element in the following column.
   */
  protected final int _n_rows_skip;

  /**
   * The current row number within the sub view
   */
  protected int _row_number;

  /**
   * Creates a shallow copy of the specified matrix and restrict the access to a sub view.
   * 
   * @param matrix The matrix
   * @param first_row The first row position
   * @param first_col The first column position
   * @param n_rows The number of rows
   * @param n_cols The number of columns
   */
  protected ViewSubMat(final AbstractMat matrix, final int first_row, final int first_col, final int n_rows, final int n_cols) {
    super(matrix);

    this.n_rows = n_rows;
    this.n_cols = n_cols;
    this.n_elem = this.n_rows * this.n_cols;

    _firstPosition = first_row + first_col * this.n_rows;
    _n_rows_skip = matrix.n_rows - this.n_rows;
  }

  @Override
  protected void iteratorReset() {
    _iterator = _firstPosition - 1;
    _row_number = -1;
  }

  @Override
  protected int iteratorNext() {
    ++_row_number;

    if (_row_number >= n_rows) {
      _iterator += _n_rows_skip;
      _row_number = 0;
    }

    return ++_iterator;
  }
}
