/*
 * Copyright 2017 Adobe. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
 * OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.adobe.aio.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.commons.lang3.StringUtils;

public class JacksonUtil {

  private JacksonUtil() {
  }

  public static final ObjectMapper DEFAULT_OBJECT_MAPPER =
      new ObjectMapper()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .findAndRegisterModules();

  public static JsonNode getJsonNode(String jsonPayload) throws JsonProcessingException {
    if (StringUtils.isEmpty(jsonPayload)) {
      return new ObjectMapper().createObjectNode();
    } else if (jsonPayload.trim().startsWith("{")) {
      return new ObjectMapper().readTree(jsonPayload);
    } else {
      return new TextNode(jsonPayload);
    }
  }

}
