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
 *   Sebastian Niemann - Lead developer
 *   Daniel Kiechle - Unit testing
 ******************************************************************************/
#pragma once

namespace armadilloJava
{
  enum InputClass {
    ElemInd,
    ExtElemInd,
    ColInd,
    ExtColInd,
    RowInd,
    ExtRowInd,
    NumElems,
    NumCols,
    NumRows,
    Normal,
    Dim,
    Exp,
    MatNormInt,
    VecNormInt,
    GenDouble,
    SinValTol,
    ElemIndRange,
    ColIndRange,
    RowIndRange,
    MatSize,
    GenMat,
    SquMat,
    InvMat,
    SymMat,
    SymPDMat,
    LogicMat,
    OOMat,
    GenColVec,
    MonColVec,
    LogicColVec,
    OOColVec,
    GenRowVec,
    MonRowVec,
    LogicRowVec,
    OORowVec,
    ElemInds,
    ColInds,
    RowInds,
    Text,
    // FilePath, Test specific
    MatNormString,
    VecNormString,
    Sort,
    Search,
    SinValSel,
    // OutputStream, Java specific
    // InputStream, Java specific
    // FileType, Test specific
    DistrParam,
    Fill,
    Random
  };
}
