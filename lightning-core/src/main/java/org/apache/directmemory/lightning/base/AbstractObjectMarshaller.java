/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.directmemory.lightning.base;

import java.io.IOException;

import org.apache.directmemory.lightning.SerializationContext;
import org.apache.directmemory.lightning.Source;
import org.apache.directmemory.lightning.metadata.PropertyDescriptor;

public abstract class AbstractObjectMarshaller
    extends AbstractMarshaller
{

    @Override
    @SuppressWarnings( "unchecked" )
    public final <V> V unmarshall( PropertyDescriptor propertyDescriptor, Source source,
                                   SerializationContext serializationContext )
        throws IOException
    {
        Object value =
            serializationContext.getObjectInstantiatorFactory().getInstantiatorOf( propertyDescriptor.getType() );
        return unmarshall( (V) value, propertyDescriptor, source, serializationContext );
    }

    public abstract <V> V unmarshall( V value, PropertyDescriptor propertyDescriptor, Source source,
                                      SerializationContext serializationContext )
        throws IOException;
}
