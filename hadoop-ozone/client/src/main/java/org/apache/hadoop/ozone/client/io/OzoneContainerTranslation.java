/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.hadoop.ozone.client.io;


import org.apache.hadoop.hdds.protocol.datanode.proto.ContainerProtos.KeyData;
import org.apache.hadoop.hdds.client.BlockID;


/**
 * This class contains methods that define the translation between the Ozone
 * domain model and the storage container domain model.
 */
final class OzoneContainerTranslation {

  /**
   * Creates key data intended for reading a container key.
   *
   * @param blockID - ID of the block.
   * @return KeyData intended for reading the container key
   */
  public static KeyData containerKeyDataForRead(BlockID blockID) {
    return KeyData
        .newBuilder()
        .setBlockID(blockID.getDatanodeBlockIDProtobuf())
        .build();
  }

  /**
   * There is no need to instantiate this class.
   */
  private OzoneContainerTranslation() {
  }
}
