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

#include <string>
using std::string;

#include <utility>
using std::pair;

#include <armadillo>
using arma::Col;
using arma::uword;
using arma::sort;
using arma::sort_index;
using arma::stable_sort_index;

#include <InputClass.hpp>
using armadilloJava::InputClass;

#include <Input.hpp>
using armadilloJava::Input;

namespace armadilloJava {
  class ExpectedGenColVecSort : public Expected {
    public:
      ExpectedGenColVecSort() {
        cout << "Compute ExpectedGenColVecSort(): " << endl;

        vector<vector<pair<string, void*>>> inputs = Input::getTestParameters({
          InputClass::GenColVec,
          InputClass::Sort
        });

        for (vector<pair<string, void*>> input : inputs) {
          _fileSuffix = "";

          int n = 0;
          for (pair<string, void*> value : input) {
            switch (n) {
              case 0:
                _fileSuffix += value.first;
                _genColVec = *static_cast<Col<double>*>(value.second);
                break;
              case 1:
                _fileSuffix += "," + value.first;
                _sort = *static_cast<string*>(value.second);
                break;
            }
            ++n;
          }

          cout << "Using input: " << _fileSuffix << endl;

          expectedArmaSort();
          expectedArmaSort_index();
          expectedArmaStable_sort_index();
        }

        cout << "done." << endl;
      }

    protected:
      Col<double> _genColVec;
      string _sort;

      void expectedArmaSort() {
        if(!_genColVec.is_finite()) {
          return;
        }

        cout << "- Compute expectedArmaSort() ... ";
        save<double>("Arma.sort", sort(_genColVec, _sort.c_str()));
        cout << "done." << endl;
      }

      void expectedArmaSort_index() {
        if(!_genColVec.is_finite()) {
          return;
        }

        cout << "- Compute expectedArmaSort_index() ... ";
        save<uword>("Arma.sort_index", sort_index(_genColVec, _sort.c_str()));
        cout << "done." << endl;
      }

      void expectedArmaStable_sort_index() {
        if(!_genColVec.is_finite()) {
          return;
        }

        cout << "- Compute expectedArmaStable_sort_index() ... ";
        save<uword>("Arma.stable_sort_index", stable_sort_index(_genColVec, _sort.c_str()));
        cout << "done." << endl;
      }

  };
}
