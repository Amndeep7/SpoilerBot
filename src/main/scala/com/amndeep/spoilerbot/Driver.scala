package com.amndeep.spoilerbot

import scala.io.Source

import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.util.DiscordException

object Driver {
  def login(token: String): Option[SpoilerBot] = {
    val builder = new ClientBuilder
    builder.withToken(token)
    try {
      val client = builder.login
      Some(new SpoilerBot(client))
    }
    catch {
      case fail: DiscordException => {
        println("Login failed because of " + fail)
        None
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val token: String = Source.fromFile("token").getLines.next()
    println(token)
    val bot = login(token)
    println(bot)
  }
}