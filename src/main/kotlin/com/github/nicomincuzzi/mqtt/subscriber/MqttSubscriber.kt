package com.github.nicomincuzzi.mqtt.subscriber

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient
import com.hivemq.client.mqtt.mqtt3.Mqtt3BlockingClient
import com.hivemq.client.mqtt.mqtt3.Mqtt3Client
import java.nio.charset.StandardCharsets

class MqttSubscriber {

    fun createClient(): Mqtt3BlockingClient {
        return MqttClient.builder()
            .useMqttVersion3()
            .identifier("my-mqtt-client-id")
            .serverHost("192.168.1.43")
            .serverPort(1883)
            .buildBlocking()
    }

    fun connect(client: Mqtt3BlockingClient) {
        client.connectWith()
            .simpleAuth()
            .username("vegator")
            .password("F5x1jpMDtsFIMP5a37lyng==".toByteArray())
            .applySimpleAuth()
            .send()
    }

    fun subscribe(client: Mqtt3Client): Mqtt3AsyncClient.Mqtt3SubscribeAndCallbackBuilder.Start.Complete {
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

