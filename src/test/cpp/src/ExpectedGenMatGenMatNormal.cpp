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

#include <stdexcept>
using std::runtime_error;

#include <armadillo>
using arma::Mat;
using arma::cor;
using arma::cov;

#include <InputClass.hpp>
using armadilloJava::InputClass;

#include <Input.hpp>
using armadilloJava::Input;

namespace armadilloJava {
  class ExpectedGenMatGenMatNormal : public Expected {
    public:
      ExpectedGenMatGenMatNormal() {
        cout << "Compute ExpectedGenMatGenMatNormal(): " << endl;

        vector<vector<pair<string, void*>>> inputs = Input::getTestParameters({
          InputClass::GenMat,
          InputClass::GenMat,
          InputClass::Normal
        });

        for (vector<pair<string, void*>> input : inputs) {
          _fileSuffix = "";

          int n = 0;
          for (pair<string, void*> value : input) {
            switch (n) {
              case 0:
                _fileSuffix += value.first;
                _genMatA = *static_cast<Mat<double>*>(value.second);
                break;
              case 1:
                _fileSuffix += "," + value.first;
                _genMatB = *static_cast<Mat<double>*>(value.second);
                break;
              case 2:
                _fileSuffix += "," + value.first;
                _normal = *static_cast<int*>(value.second);
                break;
            }
            ++n;
          }

          cout << "Using input: " << _fileSuffix << endl;

          expectedArmaCor();
          expectedArmaCov();
        }

        cout << "done." << endl;
      }

    protected:
      Mat<double> _genMatA;
      Mat<double> _genMatB;
      int _normal;

      void expectedArmaCor() {
        if(_genMatA.n_rows != _genMatB.n_rows) {
          return;
        }

        if(_genMatA.n_cols != _genMatB.n_cols) {
          return;
        }

        cout << "- Compute expectedArmaCor() ... ";
        save<double>("Arma.cor", cor(_genMatA, _genMatB, _normal));
        cout << "done." << endl;
      }

      void expectedArmaCov() {
        if(_genMatA.n_rows != _genMatB.n_rows) {
          return;
        }

        if(_genMatA.n_cols != _genMatB.n_cols) {
          return;
        }

        cout << "- Compute expectedArmaCov() ... ";
        save<double>("Arma.cov", cov(_genMatA, _genMatB, _normal));
        cout << "done." << endl;
      }
  };
}
