/**
 * Copyright (C) 2008 - Abiquo Holdings S.L. All rights reserved.
 *
 * Please see /opt/abiquo/tomcat/webapps/legal/ on Abiquo server
 * or contact contact@abiquo.com for licensing information.
 */
package com.abiquo.commons.amqp.config;

import com.abiquo.commons.amqp.AMQPConfiguration;
import com.abiquo.commons.amqp.AMQPFlags;

public class HighAvailabilityConfiguration extends AMQPConfiguration
{
    private static final String HA_EXCHANGE = "abiquo.ha";

    private static final String HA_ROUTING_KEY = "abiquo.ha.tasks";

    private static final String HA_QUEUE = HA_ROUTING_KEY;

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
        return HA_EXCHANGE;
    }

    @Override
    public String getRoutingKey()
    {
        return HA_ROUTING_KEY;
    }

    @Override
    public String getQueue()
    {
        return HA_QUEUE;
    }
}
