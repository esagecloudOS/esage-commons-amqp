/**
 * Copyright (C) 2008 - Abiquo Holdings S.L. All rights reserved.
 *
 * Please see /opt/abiquo/tomcat/webapps/legal/ on Abiquo server
 * or contact contact@abiquo.com for licensing information.
 */
package com.abiquo.commons.amqp.serialization;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public class DefaultSerializer<T extends Serializable> implements AMQPSerializer<T>
{
    @Override
    public byte[] serialize(final T value) throws IOException
    {
        try
        {
            return createObjectMapper().writeValueAsBytes(value);
        }
        catch (JsonProcessingException e)
        {
            throw new IOException(e);
        }
    }

    private static ObjectMapper createObjectMapper()
    {
        return new ObjectMapper().setAnnotationIntrospector(
            new AnnotationIntrospectorPair(new JacksonAnnotationIntrospector(),
                new JaxbAnnotationIntrospector(TypeFactory.defaultInstance())));
    }
}
