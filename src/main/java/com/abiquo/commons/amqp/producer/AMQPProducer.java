/**
 * Copyright (C) 2008 - Abiquo Holdings S.L. All rights reserved.
 *
 * Please see /opt/abiquo/tomcat/webapps/legal/ on Abiquo server
 * or contact contact@abiquo.com for licensing information.
 */
package com.abiquo.commons.amqp.producer;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abiquo.commons.amqp.AMQPConfiguration;
import com.abiquo.commons.amqp.serialization.AMQPSerializer;
import com.abiquo.commons.amqp.serialization.DefaultSerializer;
import com.google.common.base.Objects;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * The base producer, it handles the creation and configuration of AMQP entities and the connection
 * and disconnection to RabbitMQ.
 * 
 * @param <T> the type of the objects to publish
 * @author Enric Ruiz
 */
public class AMQPProducer<T extends Serializable> implements Closeable
{
    private final static Logger log = LoggerFactory.getLogger(AMQPProducer.class);

    protected final AMQPConfiguration configuration;

    protected final Channel channel;

    protected final AMQPSerializer<T> serializer;

    protected boolean declareExchanges = true;

    public AMQPProducer(final AMQPConfiguration configuration, final Channel channel)
    {
        this(configuration, channel, new DefaultSerializer<T>());
    }

    public AMQPProducer(final AMQPConfiguration configuration, final Channel channel,
        final AMQPSerializer<T> serializer)
    {
        checkNotNull(configuration, "AMQPConfiguration for an AMQPProducer cannot be null");
        checkNotNull(channel, "Channel for an AMQPProducer cannot be null");
        checkNotNull(serializer, "Message serializer cannot be null");

        this.configuration = configuration;
        this.channel = channel;
        this.serializer = serializer;
    }

    public void publish(final T message) throws IOException
    {
        checkNotNull(message, "Message to publish can not be null");

        if (declareExchanges)
        {
            log.trace("Declaring exchanges for {}", this);
            configuration.declareExchanges(channel);
            declareExchanges = false;
        }

        channel.basicPublish(configuration.getExchange(), configuration.getRoutingKey(),
            MessageProperties.PERSISTENT_TEXT_PLAIN, serializer.serialize(message));
    }

    @Override
    public void close() throws IOException
    {
        try
        {
            log.trace("Trying to close {}", this);
            channel.close();
            log.trace("{} closed", this);
        }
        catch (TimeoutException e)
        {
            log.error("Timeout while closing " + this, e);
            throw new ShutdownSignalException(true, true, null, channel);
        }
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this.getClass()).omitNullValues() //
            .addValue(configuration.toString()) //
            .add("Channel", channel.getChannelNumber()) //
            .toString();
    }
}
