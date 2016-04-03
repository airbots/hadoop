/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.yarn.server.resourcemanager.scheduler.efh;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.server.resourcemanager.reservation.ReservationSchedulerConfiguration;

public class EfhSchedulerConfiguration extends ReservationSchedulerConfiguration {

  private static final Log LOG =
      LogFactory.getLog(EfhSchedulerConfiguration.class);

  private static final String CS_CONFIGURATION_FILE = "efh-scheduler.xml";

  @Private
  public static final String PREFIX = "yarn.scheduler.efh.";

  @Private
  public static final String ROOT = "root";

  @Private
  public static final String DOT = ".";

  @Private
  public static final String LOCALITY_INFO_LOCATION = "";

  @Private
  public static final String DEFAULT_LOCALITY_INFO_LOCATION = "/scheduler/locality/";

  @Private
  public static class QueueMapping {

    public enum MappingType {

      USER("u"),
      GROUP("g");
      private final String type;
      private MappingType(String type) {
        this.type = type;
      }

      public String toString() {
        return type;
      }

    };

    MappingType type;
    String source;
    String queue;

    public QueueMapping(MappingType type, String source, String queue) {
      this.type = type;
      this.source = source;
      this.queue = queue;
    }
  }

  public EfhSchedulerConfiguration() {
    this(new Configuration());
  }

  public EfhSchedulerConfiguration(Configuration configuration) {
    this(configuration, true);
  }

  @Override
  public boolean isReservable(String queue) {
    return false;
  }

  public EfhSchedulerConfiguration(Configuration configuration,
                                        boolean useLocalConfigurationProvider) {
    super(configuration);
    if (useLocalConfigurationProvider) {
      addResource(CS_CONFIGURATION_FILE);
    }
  }

  private String getDefaultLocalityInfoLocation() {
    return get(LOCALITY_INFO_LOCATION, DEFAULT_LOCALITY_INFO_LOCATION);
  }
}
