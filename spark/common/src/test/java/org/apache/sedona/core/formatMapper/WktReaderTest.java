/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sedona.core.formatMapper;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.apache.sedona.core.TestBase;
import org.apache.sedona.core.spatialRDD.SpatialRDD;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class WktReaderTest extends TestBase {

  public static String wktGeometries = null;

  @BeforeClass
  public static void onceExecutedBeforeAll() throws IOException {
    initialize(WktReaderTest.class.getName());
    wktGeometries = WktReaderTest.class.getClassLoader().getResource("county_small.tsv").getPath();
  }

  @AfterClass
  public static void tearDown() throws Exception {
    sc.stop();
  }

  /**
   * Test correctness of parsing geojson file
   *
   * @throws IOException
   */
  @Test
  public void testReadToGeometryRDD() throws IOException {
    // load geojson with our tool
    SpatialRDD wktRDD = WktReader.readToGeometryRDD(sc, wktGeometries, 0, true, false);
    assertEquals(wktRDD.rawSpatialRDD.count(), 103);
  }
}
