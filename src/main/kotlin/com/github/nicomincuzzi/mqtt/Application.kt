package com.github.nicomincuzzi.mqtt

fun main() {
    val subscriber = Subscriber(
        Client("my-mqtt-client-id", "192.168.1.43", 1883, Authentication("vegator", "F5x1jpMDtsFIMP5a37lyng=="))
    )
    subscriber.init()

    subscriber.retrieveMessage(subscriber.to(listOf()))
}