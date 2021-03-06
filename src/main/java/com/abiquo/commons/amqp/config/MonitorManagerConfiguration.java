/**
 * Copyright (C) 2008 - Abiquo Holdings S.L. All rights reserved.
 *
 * Please see /opt/abiquo/tomcat/webapps/legal/ on Abiquo server
 * or contact contact@abiquo.com for licensing information.
 */
package com.abiquo.commons.amqp.config;

import com.abiquo.commons.amqp.AMQPConfiguration;
import com.abiquo.commons.amqp.AMQPFlags;

/**
 * Common RabbitMQ Broker configuration for VSM consumer and producer.
 * 
 * @author eruiz@abiquo.com
 */
public class MonitorManagerConfiguration extends AMQPConfiguration
{
    private static final String VSM_EXCHANGE = "abiquo.vsm";

    private static final String EVENT_SYNK_QUEUE = "abiquo.vsm.eventsynk";

    private static final String VSM_ROUTING_KEY = EVENT_SYNK_QUEUE;

    @Override
    public AMQPFlags getFlags()
    {
        return AMQPFlags.direct() //
            .exchangeDurable(true) //
            .queueDurable(true) //
            .queueExclusive(false) //
            .queueAutoDelete(false) //
            .build();
    }

    @Override
    public String getExchange()
    {
        return VSM_EXCHANGE;
    }

    @Override
    public String getRoutingKey()
    {
        return VSM_ROUTING_KEY;
    }

    @Override
    public String getQueue()
    {
        return EVENT_SYNK_QUEUE;
    }
}
