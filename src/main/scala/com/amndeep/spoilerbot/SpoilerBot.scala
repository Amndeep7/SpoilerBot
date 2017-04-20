package com.amndeep.spoilerbot

import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.api.events.EventSubscriber
import sx.blah.discord.handle.impl.events.ReadyEvent
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent

class SpoilerBot(client: IDiscordClient) {
  client.getDispatcher.registerListener(this)

  @EventSubscriber
  def onReady(event: ReadyEvent): Unit = {
    println("Logged in")
  }

  // syntax: @bot replacement text == spoiler text
  @EventSubscriber
  def onMention(event: MentionEvent): Unit = {
    println("Mentioned:\n\tby: " + event.getAuthor + "\n\tcontents: " + event.getMessage.getContent)

    if(event.getAuthor.getLongID == client.getOurUser.getLongID) {
      println("Don't do recursion")
      return
    }

    val pattern = """(?s)^<@!?\d+> (.*?) == (.*)""".r

    val messages = pattern.findAllIn(event.getMessage.getContent).matchData.next.subgroups

    event.getMessage.delete

    event.getMessage.getChannel.sendMessage("I was mentioned by " + event.getAuthor.mention() + ", who wanted \"" + messages(1) + "\" replaced by \"" + messages(0) + "\"")
  }
}