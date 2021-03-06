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
using arma::Row;
using arma::shuffle;

#include <InputClass.hpp>
using armadilloJava::InputClass;

#include <Input.hpp>
using armadilloJava::Input;

namespace armadilloJava {
  class ExpectedRandomGenRowVec : public Expected {
    public:
      ExpectedRandomGenRowVec() {
        cout << "Compute ExpectedRandomGenRowVec(): " << endl;

          vector<vector<pair<string, void*>>> inputs = Input::getTestParameters({
            InputClass::Random,
            InputClass::GenRowVec
          });

          for (vector<pair<string, void*>> input : inputs) {
            _fileSuffix = "";

            int n = 0;
            for (pair<string, void*> value : input) {
              switch (n) {
                case 0:
                  _fileSuffix += value.first;
                  _random = *static_cast<int*>(value.second);
                  break;
                case 1:
                  _fileSuffix += "," + value.first;
                  _genRowVec = *static_cast<Row<double>*>(value.second);
                  break;
              }
              ++n;
            }

            cout << "Using input: " << _fileSuffix << endl;

            expectedArmaShuffle();
          }

          cout << "done." << endl;
        }

    protected:
      int _random;
      Row<double> _genRowVec;

      void expectedArmaShuffle() {
        cout << "- Compute expectedArmaShuffle() ... ";

        Row<double> result = shuffle(_genRowVec);
        for(int n = 2; n <= _random; n++) {
          result = (result * n + shuffle(_genRowVec)) / (n + 1);
        }
        save<double>("Arma.shuffle", result);

        cout << "done." << endl;
      }
  };
}
