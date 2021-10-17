package com.vegcare.mqtt.subscriber

import com.hivemq.client.internal.mqtt.message.subscribe.mqtt3.Mqtt3SubscribeViewBuilder
import com.hivemq.client.internal.mqtt.mqtt3.Mqtt3BlockingClientView
import com.hivemq.client.mqtt.mqtt3.Mqtt3BlockingClient
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.test.Ignore

@Ignore
class MqttSubscriberTest {

    private val subscriber = MqttSubscriber()

    @Test
    fun createClient() {
    }

    @Test
    fun subscribe() {
        val client = mock(Mqtt3BlockingClient::class.java)
        subscriber.subscribe(client)
        //`when`(client.subscribeWith()).thenReturn(Mqtt3SubscribeViewBuilder.Send())
    }

    @Test
    fun retrieveMessage() {
    }
}