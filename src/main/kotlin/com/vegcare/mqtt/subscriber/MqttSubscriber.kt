package com.vegcare.mqtt.subscriber

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient
import com.hivemq.client.mqtt.mqtt3.Mqtt3BlockingClient
import java.nio.charset.StandardCharsets

class MqttSubscriber {

    fun createClient(): Mqtt3BlockingClient {
        val client = MqttClient.builder()
            .useMqttVersion3()
            .identifier("my-mqtt-client-id")
            .serverHost("192.168.1.43")
            .serverPort(1883)
            .buildBlocking()


        client.connectWith()
            .simpleAuth()
            .username("vegator")
            .password("F5x1jpMDtsFIMP5a37lyng==".toByteArray())
            .applySimpleAuth()
            .send()
        return client
    }

    fun subscribe(client: Mqtt3BlockingClient): Mqtt3AsyncClient.Mqtt3SubscribeAndCallbackBuilder.Start.Complete {
        val subscribeWith = client.toAsync().subscribeWith()
        val topicFilter = subscribeWith.topicFilter("/home/sensors/temp/kitchen")
        topicFilter.send()
        return topicFilter
    }

    fun retrieveMessage(topicFilter: Mqtt3AsyncClient.Mqtt3SubscribeAndCallbackBuilder.Start.Complete) {
        topicFilter
            .callback { publish ->
                println(
                    "Received message on topic " + publish.topic.toString() + ": " +
                            String(publish.payloadAsBytes, StandardCharsets.UTF_8)
                )
            }
            .send()
    }
}

fun main() {
    val subscriber = MqttSubscriber()
    val client = subscriber.createClient()

    subscriber.retrieveMessage(subscriber.subscribe(client))
}