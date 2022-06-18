package com.github.nicomincuzzi.mqtt

import com.hivemq.client.mqtt.mqtt3.message.connect.Mqtt3ConnectBuilder
import com.hivemq.client.mqtt.mqtt3.message.connect.connack.Mqtt3ConnAck

class Authentication(private val username: String, private val password: String) {

    fun apply(connAckSend: Mqtt3ConnectBuilder.Send<Mqtt3ConnAck>): Mqtt3ConnectBuilder.Send<Mqtt3ConnAck> {
        return connAckSend
            .simpleAuth()
            .username(username)
            .password(password.toByteArray())
            .applySimpleAuth()
    }
}
