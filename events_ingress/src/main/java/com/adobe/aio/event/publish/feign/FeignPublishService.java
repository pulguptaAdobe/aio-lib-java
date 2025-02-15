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
package com.adobe.aio.event.publish.feign;

import com.adobe.aio.event.publish.PublishService;
import com.adobe.aio.event.publish.api.PublishApi;
import com.adobe.aio.event.publish.model.CloudEvent;
import com.adobe.aio.exception.AIOException;
import com.adobe.aio.feign.AIOHeaderInterceptor;
import com.adobe.aio.ims.feign.JWTAuthInterceptor;
import com.adobe.aio.util.feign.FeignUtil;
import com.adobe.aio.util.JacksonUtil;
import com.adobe.aio.workspace.Workspace;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;

public class FeignPublishService implements PublishService {

  private final PublishApi publishApi;

  public FeignPublishService(final Workspace workspace, final String url) {
    String apiUrl = StringUtils.isEmpty(url) ? PublishApi.DEFAULT_URL : url;
    if (workspace == null) {
      throw new IllegalArgumentException(
          "PublishService is missing a workspace context");
    }
    workspace.validateWorkspaceContext();
    RequestInterceptor authInterceptor = JWTAuthInterceptor.builder().workspace(workspace).build();
    this.publishApi = FeignUtil.getDefaultBuilder()
        .requestInterceptor(authInterceptor)
        .requestInterceptor(AIOHeaderInterceptor.builder().workspace(workspace).build())
        .target(PublishApi.class, apiUrl);
  }

  @Override
  public CloudEvent publishCloudEvent(String providerId, String eventCode,
      String data) throws JsonProcessingException {
    return publishCloudEvent(providerId, eventCode, null, JacksonUtil.getJsonNode(data));
  }

  @Override
  public CloudEvent publishCloudEvent(String providerId, String eventCode, String eventId,
      String data) throws JsonProcessingException {
    return publishCloudEvent(providerId, eventCode, eventId, JacksonUtil.getJsonNode(data));
  }

  @Override
  public CloudEvent publishCloudEvent(String providerId, String eventCode, String eventId,
      JsonNode data) {
    CloudEvent inputModel = CloudEvent.builder()
        .providerId(providerId).eventCode(eventCode).eventId(eventId)
        .data(data).build();
    publishApi.publishCloudEvent(inputModel);
    return inputModel;
  }

  @Override
  public void publishRawEvent(String providerId, String eventCode, String rawEvent) {
    publishApi.publishRawEvent(providerId,  eventCode, getJsonNode(rawEvent));
  }

  private static JsonNode getJsonNode(String jsonPayload)  {
    if (StringUtils.isEmpty(jsonPayload)) {
      return new ObjectMapper().createObjectNode();
    } else if (jsonPayload.trim().startsWith("{")) {
      try {
        return new ObjectMapper().readTree(jsonPayload);
      } catch (JsonProcessingException e) {
        throw new AIOException("Invalid event json payload: "+e.getMessage(),e);
      }
    } else {
      return new TextNode(jsonPayload);
    }
  }
}
