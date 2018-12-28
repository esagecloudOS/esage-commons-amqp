/**
 * Copyright (C) 2008 - Abiquo Holdings S.L. All rights reserved.
 *
 * Please see /opt/abiquo/tomcat/webapps/legal/ on Abiquo server
 * or contact contact@abiquo.com for licensing information.
 */
package com.abiquo.commons.amqp;

import static com.abiquo.commons.amqp.AMQPConfiguration.DirectExchange;
import static com.abiquo.commons.amqp.AMQPConfiguration.FanoutExchange;
import static com.abiquo.commons.amqp.AMQPConfiguration.TopicExchange;

import java.util.Map;

/**
 * Flags to configure the AMQP queues and exchanges.
 * 
 * @author Ignasi Barrera
 */
public class AMQPFlags
{
    private final String exchangeType;

    private final boolean exchangeDurable;

    private final boolean queueDurable;

    private final boolean queueExclusive;

    private final boolean queueAutoDelete;

    private final Map<String, Object> queueArguments;

    private AMQPFlags(final String exchangeType, final boolean exchangeDurable,
        final boolean queueDurable, final boolean queueExclusive, final boolean queueAutoDelete,
        final Map<String, Object> queueArguments)
    {
        this.exchangeType = exchangeType;
        this.exchangeDurable = exchangeDurable;
        this.queueDurable = queueDurable;
        this.queueExclusive = queueExclusive;
        this.queueAutoDelete = queueAutoDelete;
        this.queueArguments = queueArguments;
    }

    public static Builder direct()
    {
        return new Builder(DirectExchange);
    }

    public static Builder topic()
    {
        return new Builder(TopicExchange);
    }

    public static Builder fanout()
    {
        return new Builder(FanoutExchange);
    }

    public static Builder noExchange()
    {
        return new Builder(null);
    }

    public String exchangeType()
    {
        return exchangeType;
    }

    public boolean exchangeDurable()
    {
        return exchangeDurable;
    }

    public boolean queueDurable()
    {
        return queueDurable;
    }

    public boolean queueExclusive()
    {
        return queueExclusive;
    }

    public boolean queueAutoDelete()
    {
        return queueAutoDelete;
    }

    public Map<String, Object> queueArguments()
    {
        return queueArguments;
    }

    public static class Builder
    {
        private String exchangeType;

        private boolean exchangeDurable;

        private boolean queueDurable;

        private boolean queueExclusive;

        private boolean queueAutoDelete;

        private Map<String, Object> queueArguments;

        private Builder(final String exchangeType)
        {
            this.exchangeType = exchangeType;
        }

        public AMQPFlags.Builder exchangeDurable(final boolean exchangeDurable)
        {
            this.exchangeDurable = exchangeDurable;
            return this;
        }

        public AMQPFlags.Builder queueDurable(final boolean queueDurable)
        {
            this.queueDurable = queueDurable;
            return this;
        }

        public AMQPFlags.Builder queueExclusive(final boolean queueExclusive)
        {
            this.queueExclusive = queueExclusive;
            return this;
        }

        public AMQPFlags.Builder queueAutoDelete(final boolean queueAutoDelete)
        {
            this.queueAutoDelete = queueAutoDelete;
            return this;
        }

        public AMQPFlags.Builder queueArguments(final Map<String, Object> queueArguments)
        {
            this.queueArguments = queueArguments;
            return this;
        }

        public AMQPFlags build()
        {
            return new AMQPFlags(this.exchangeType, this.exchangeDurable, this.queueDurable,
                this.queueExclusive, this.queueAutoDelete, this.queueArguments);
        }
    }
}
