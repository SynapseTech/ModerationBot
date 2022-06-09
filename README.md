<img src="./asset/logo.png" align="right" width="128px" alt="Logo"/>

# Synapse Moderation Bot
A bot for managing and moderating our community Discord server.

> **Note**
> This bot is not public. While you can host it on your own, be aware that it is
> not designed to run without Discord's [privileged intents][privileged_intents],
> specifically the message content and user presence intents. Discord is *very*
> strict about granting these permissions to applicants, even with verified bots.
> This also means we offer no warranty if you attempt to do self-host this 
> software anyway. *Self-host at your own risk!*

## Project motivation
We need a bot to help us automatically moderate and log user actions in our
Discord server, that also empowers our staff to take action where needed.

## Project goals
Our primary goal for this project is to enable our staff to take action and
better prepare them for handling our community.

Our secondary goal for this project is to enable open feedback channels between
users and staff about our moderation policies.

Our tertiary goal for this project is to provide a seamlessly integrated
moderator dashboard that also looks beautiful and feels good to work with.

# Contributing
All the essential bot code for the project can be found under the root directory
and is written in Kotlin. [Ktor](https://ktor.io) is used for HTTP handling in
the dashboard.

Before you contribute, we ask that you have a working knowledge of Discord bot
development and Kotlin as well as Web technologies for the dashboard. We also
ask that you respect our users, employees and fellow contributors when
contributing to our software.

# License
This software is licenced under the [MIT License](LICENSE), &copy; 2022-CURRENT
Synapse Technologies, LLC.

You are free to reuse our work commercially, but we ask that if you find our
software useful, please reach out to us so that we can showcase how our software
is being used in the community.


[privileged_intents]: https://discord.com/developers/docs/topics/gateway#privileged-intents