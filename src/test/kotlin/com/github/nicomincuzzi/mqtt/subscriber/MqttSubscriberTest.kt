package com.github.nicomincuzzi.mqtt.subscriber

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient
import com.hivemq.client.mqtt.mqtt3.Mqtt3Client
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*


class MqttSubscriberTest {

    private val subscriber = MqttSubscriber()

    @Test
    fun createClientWithServerHostAndPort() {
        val client = subscriber.createClient()
        assertThat(client.config.serverHost, `is`("192.168.1.43"))
        assertThat(client.config.serverPort, `is`(1883))
    }

    @Test
    fun connectClientWithAuth() {
        val client = MqttClient.builder()
            .useMqttVersion3()
            .buildBlocking()
        subscriber.connect(client)
        assertThat(client.config.simpleAuth.get().username, `is`("vegator"))
    }

    @Test
    fun subscribe() {
        val client = mock(Mqtt3Client::class.java)
        val asyncClient = mock(Mqtt3AsyncClient::class.java)
        val subscribeWith = mock(Mqtt3AsyncClient.Mqtt3SubscribeAndCallbackBuilder.Start::class.java)

        `when`(client.toAsync()).thenReturn(asyncClient)
        `when`(asyncClient.subscribeWith()).thenReturn(subscribeWith)

        subscriber.subscribe(client)

        val captor = ArgumentCaptor.forClass(String::class.java)
        verify(subscribeWith).topicFilter(captor.capture())
        assertThat(captor.capture(), `is`("/home/sensors/temp/kitchen"))
    }

}