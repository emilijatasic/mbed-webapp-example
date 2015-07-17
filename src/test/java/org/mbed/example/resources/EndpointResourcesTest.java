/*
 * Copyright (c) 2015, ARM Limited, All Rights Reserved
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mbed.example.resources;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.arm.mbed.restclient.MbedClient;
import com.arm.mbed.restclient.endpoint.EndpointTarget;
import com.arm.mbed.restclient.entity.Endpoint;
import com.arm.mbed.restclient.entity.ResourceDescription;
import java.util.Arrays;
import org.junit.Test;

/**
 * Created by mitvah01 on 13.7.2015.
 */
public class EndpointResourcesTest {

    @Test
    public void getEndpoints() {

        MbedClient mbedClient = mock(MbedClient.class, RETURNS_DEEP_STUBS);
        when(mbedClient.endpoints().readAll()).thenReturn(Arrays.asList(new Endpoint("dev-01", null, null, false)));

        EndpointResources endpointResources = new EndpointResources(mbedClient);

        assertEquals(1, endpointResources.getEndpoints().size());
    }

    @Test
    public void getEndpointResources() {

        MbedClient mbedClient = mock(MbedClient.class, RETURNS_DEEP_STUBS);
        EndpointTarget endpointTarget = mock(EndpointTarget.class);
        when(mbedClient.endpoint("dev-01")).thenReturn(endpointTarget);
        when(endpointTarget.readResourceList())
                .thenReturn(Arrays.asList(new ResourceDescription("/dev/mac", null, null, null, false)));
        EndpointResources endpointResources = new EndpointResources(mbedClient);

        assertEquals(1, endpointResources.getEndpointResources("dev-01").size());
    }
}