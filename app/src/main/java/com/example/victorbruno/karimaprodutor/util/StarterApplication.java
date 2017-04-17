/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.example.victorbruno.karimaprodutor.util;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Habilite armazenamento local.
    Parse.enableLocalDatastore(this);

    // Codigo de configuração do App
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("AP21i5wm00D0QPzgcqMqgz7DZJ87HGZgD9yDT6j1")
            .clientKey("Mh9j55vSPVBJWADcGtVDF0UbKFxPK71FTe656Hsm")
            .server("https://parseapi.back4app.com/")
            .build()
    );

      //ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    defaultACL.setPublicReadAccess(true);
    //ParseACL.setDefaultACL(defaultACL, true);
  }
}
