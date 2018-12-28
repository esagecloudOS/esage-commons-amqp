/**
 * Copyright (C) 2008 - Abiquo Holdings S.L. All rights reserved.
 *
 * Please see /opt/abiquo/tomcat/webapps/legal/ on Abiquo server
 * or contact contact@abiquo.com for licensing information.
 */
package com.abiquo.commons.amqp.serialization;

import java.io.IOException;
import java.io.Serializable;

public interface AMQPDeserializer<T extends Serializable>
{
    public T deserialize(final byte[] bytes, final Class<T> type) throws IOException;
}
