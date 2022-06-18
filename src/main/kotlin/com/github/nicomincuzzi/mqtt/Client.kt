package com.github.nicomincuzzi.mqtt

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt3.Mqtt3BlockingClient

class Client(
    private val id: String,
    private val host: String,
    private val port: Int,
    private val auth: Authentication
) {
    private lateinit var client: Mqtt3BlockingClient

    fun init() {
        client = MqttClient.builder().useMqttVersion3()
            .identifier(id)
            .serverHost(host)
            .serverPort(port)
            .buildBlocking()

        auth.apply(client.connectWith()).send()
    }

    fun client(): Mqtt3BlockingClient {
        return client
    }
}