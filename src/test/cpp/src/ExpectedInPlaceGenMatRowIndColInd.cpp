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
#include <Expected.hpp>
using armadilloJava::Expected;

#include <iostream>
using std::cout;
using std::endl;

#include <utility>
using std::pair;

#include <armadillo>
using arma::Mat;

#include <InputClass.hpp>
using armadilloJava::InputClass;

#include <Input.hpp>
using armadilloJava::Input;

namespace armadilloJava {
  class ExpectedInPlaceGenMatRowIndColInd : public Expected {
    public:
      ExpectedInPlaceGenMatRowIndColInd() {
        cout << "Compute ExpectedInPlaceGenMatRowIndColInd(): " << endl;

          vector<vector<pair<string, void*>>> inputs = Input::getTestParameters({
            InputClass::GenMat,
            InputClass::RowInd,
            InputClass::ColInd
          });

          for (vector<pair<string, void*>> input : inputs) {
            _fileSuffix = "";

            int n = 0;
            for (pair<string, void*> value : input) {
              switch (n) {
                case 0:
                  _fileSuffix += value.first;
                  _genMat = *static_cast<Mat<double>*>(value.second);
                  break;
                case 1:
                  _fileSuffix += "," + value.first;
                  _rowInd = *static_cast<int*>(value.second);
                  break;
                case 2:
                  _fileSuffix += "," + value.first;
                  _colInd = *static_cast<int*>(value.second);
                  break;
              }
              ++n;
            }

            cout << "Using input: " << _fileSuffix << endl;

            _copyOfGenMat = _genMat;
            _copyOfRowInd = _rowInd;
            _copyOfColInd = _colInd;

            expectedMatAtIncrement();

            _genMat = _copyOfGenMat;
            _rowInd = _copyOfRowInd;
            _colInd = _copyOfColInd;
            expectedMatAtDecrement();
          }

          cout << "done." << endl;
        }

    protected:
      Mat<double> _genMat;
      Mat<double> _copyOfGenMat;

      int _rowInd;
      int _copyOfRowInd;

      int _colInd;
      int _copyOfColInd;

      void expectedMatAtIncrement() {
        if(!_genMat.in_range(_rowInd, _colInd)) {
          return;
        }

        _genMat.at(_rowInd, _colInd)++;

        cout << "- Compute expectedMatAtIncrement() ... ";
        save<double>("Mat.atIncrement", _genMat);
        cout << "done." << endl;
      }

      void expectedMatAtDecrement() {
        if(!_genMat.in_range(_rowInd, _colInd)) {
          return;
        }

        _genMat.at(_rowInd, _colInd)--;

        cout << "- Compute expectedMatAtDecrement() ... ";
        save<double>("Mat.atDecrement", _genMat);
        cout << "done." << endl;
      }
  };
}
