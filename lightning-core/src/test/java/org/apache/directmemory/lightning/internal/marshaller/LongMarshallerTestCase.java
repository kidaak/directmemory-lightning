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
package org.apache.directmemory.lightning.internal.marshaller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import org.apache.directmemory.lightning.Lightning;
import org.apache.directmemory.lightning.Serializer;
import org.apache.directmemory.lightning.base.AbstractSerializerDefinition;
import org.apache.directmemory.lightning.internal.util.DebugLogger;
import org.apache.directmemory.lightning.metadata.Attribute;
import org.junit.Test;

public class LongMarshallerTestCase
{

    @Test
    public void testLongPrimitive()
        throws Exception
    {
        Serializer serializer =
            Lightning.newBuilder().logger( new DebugLogger() ).debugCacheDirectory( new File( "target" ) ).serializerDefinitions( new AbstractSerializerDefinition()
                                                                                                                                  {

                                                                                                                                      @Override
                                                                                                                                      protected void configure()
                                                                                                                                      {
                                                                                                                                          serialize(
                                                                                                                                                     PrimitiveHolder.class ).attributes();
                                                                                                                                      }
                                                                                                                                  } ).build();

        PrimitiveHolder value = new PrimitiveHolder();
        value.setValue1( 0 );
        value.setValue2( Long.MAX_VALUE );

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        serializer.serialize( value, baos );

        ByteArrayInputStream bais = new ByteArrayInputStream( baos.toByteArray() );
        Object result = serializer.deserialize( bais );

        assertNotNull( result );
        assertEquals( value, result );

        value = new PrimitiveHolder();
        value.setValue1( -10 );
        value.setValue2( 20 );

        baos = new ByteArrayOutputStream();
        serializer.serialize( value, baos );

        bais = new ByteArrayInputStream( baos.toByteArray() );
        result = serializer.deserialize( bais );

        assertNotNull( result );
        assertEquals( value, result );
    }

    @Test
    public void testLongWrapper()
        throws Exception
    {
        Serializer serializer =
            Lightning.newBuilder().logger( new DebugLogger() ).debugCacheDirectory( new File( "target" ) ).serializerDefinitions( new AbstractSerializerDefinition()
                                                                                                                                  {

                                                                                                                                      @Override
                                                                                                                                      protected void configure()
                                                                                                                                      {
                                                                                                                                          serialize(
                                                                                                                                                     WrapperHolder.class ).attributes();
                                                                                                                                      }
                                                                                                                                  } ).build();

        WrapperHolder value = new WrapperHolder();
        value.setValue1( Long.MAX_VALUE );
        value.setValue2( null );
        value.setValue3( 34L );

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        serializer.serialize( value, baos );

        ByteArrayInputStream bais = new ByteArrayInputStream( baos.toByteArray() );
        Object result = serializer.deserialize( bais );

        assertNotNull( result );
        assertEquals( value, result );

        value = new WrapperHolder();
        value.setValue1( 0L );
        value.setValue2( Long.MIN_VALUE );
        value.setValue3( null );

        baos = new ByteArrayOutputStream();
        serializer.serialize( value, baos );

        bais = new ByteArrayInputStream( baos.toByteArray() );
        result = serializer.deserialize( bais );

        assertNotNull( result );
        assertEquals( value, result );

        value = new WrapperHolder();
        value.setValue1( null );
        value.setValue2( -1L );
        value.setValue3( Long.MAX_VALUE );

        baos = new ByteArrayOutputStream();
        serializer.serialize( value, baos );

        bais = new ByteArrayInputStream( baos.toByteArray() );
        result = serializer.deserialize( bais );

        assertNotNull( result );
        assertEquals( value, result );
    }

    public static class PrimitiveHolder
    {

        @Attribute
        private long value1;

        @Attribute
        private long value2;

        public long isValue1()
        {
            return value1;
        }

        public void setValue1( long value1 )
        {
            this.value1 = value1;
        }

        public long isValue2()
        {
            return value2;
        }

        public void setValue2( long value2 )
        {
            this.value2 = value2;
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) ( value1 ^ ( value1 >>> 32 ) );
            result = prime * result + (int) ( value2 ^ ( value2 >>> 32 ) );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj )
                return true;
            if ( obj == null )
                return false;
            if ( getClass() != obj.getClass() )
                return false;
            PrimitiveHolder other = (PrimitiveHolder) obj;
            if ( value1 != other.value1 )
                return false;
            if ( value2 != other.value2 )
                return false;
            return true;
        }

        @Override
        public String toString()
        {
            return "PrimitiveHolder [value1=" + value1 + ", value2=" + value2 + "]";
        }
    }

    public static class WrapperHolder
    {

        @Attribute
        private Long value1;

        @Attribute
        private Long value2;

        @Attribute
        private Long value3;

        public Long getValue1()
        {
            return value1;
        }

        public void setValue1( Long value1 )
        {
            this.value1 = value1;
        }

        public Long getValue2()
        {
            return value2;
        }

        public void setValue2( Long value2 )
        {
            this.value2 = value2;
        }

        public Long getValue3()
        {
            return value3;
        }

        public void setValue3( Long value3 )
        {
            this.value3 = value3;
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( value1 == null ) ? 0 : value1.hashCode() );
            result = prime * result + ( ( value2 == null ) ? 0 : value2.hashCode() );
            result = prime * result + ( ( value3 == null ) ? 0 : value3.hashCode() );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj )
                return true;
            if ( obj == null )
                return false;
            if ( getClass() != obj.getClass() )
                return false;
            WrapperHolder other = (WrapperHolder) obj;
            if ( value1 == null )
            {
                if ( other.value1 != null )
                    return false;
            }
            else if ( !value1.equals( other.value1 ) )
                return false;
            if ( value2 == null )
            {
                if ( other.value2 != null )
                    return false;
            }
            else if ( !value2.equals( other.value2 ) )
                return false;
            if ( value3 == null )
            {
                if ( other.value3 != null )
                    return false;
            }
            else if ( !value3.equals( other.value3 ) )
                return false;
            return true;
        }

        @Override
        public String toString()
        {
            return "WrapperHolder [value1=" + value1 + ", value2=" + value2 + ", value3=" + value3 + "]";
        }
    }
}
