package com.github.nicomincuzzi.mqtt.subscriber

fun main() {
    val subscriber = MqttSubscriber()
    val client = subscriber.createClient()
    subscriber.connect(client)

    subscriber.retrieveMessage(subscriber.subscribe(client))
}