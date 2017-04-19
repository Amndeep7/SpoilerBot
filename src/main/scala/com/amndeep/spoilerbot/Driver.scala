package com.amndeep.spoilerbot

import scala.io.Source

import sx.blah.discord.api.ClientBuilder

object Driver {
  def login(token: String): Option[SpoilerBot] = {
    val builder = new ClientBuilder
    builder.withToken(token)
    val client = builder.login
    Option(new SpoilerBot(client))
  }

  def main(args: Array[String]): Unit = {
    val token: String = Source.fromFile("token").getLines.next()
    println(token)
    val bot = login(token)
    println(bot)
  }
}