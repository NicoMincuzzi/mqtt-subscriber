package com.github.nicomincuzzi.mqtt

import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient.Mqtt3SubscribeAndCallbackBuilder.Start
import java.nio.charset.StandardCharsets.UTF_8

class Subscriber(private val client: Client) {
    fun init() {
        client.init()
    }

    fun to(topics: List<String>): Start.Complete {
        val subscribeWith = client.client().toAsync().subscribeWith()
        val topicFilter = subscribeWith.topicFilter("/home/sensors/temp/kitchen")
        topicFilter.send()
        return topicFilter
    }

    fun retrieveMessage(topicFilter: Start.Complete) {
        topicFilter.callback { publish ->
            println(
                "Received on topic ${publish.topic} the message: ${String(publish.payloadAsBytes, UTF_8)}"
            )
        }.send()
    }
}

