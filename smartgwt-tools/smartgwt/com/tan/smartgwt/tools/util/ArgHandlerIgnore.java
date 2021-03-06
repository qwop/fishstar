/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tan.smartgwt.tools.util;

import com.google.gwt.util.tools.ArgHandlerFlag;

/**
 * Arg handler for "ignore" option.
 */
public abstract class ArgHandlerIgnore extends ArgHandlerFlag {

  @Override
  public String getPurpose() {
    return "Ignore any existing files; do not overwrite";
  }

  @Override
  public String getTag() {
    return "-ignore";
  }
}
