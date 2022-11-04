# Hello Kafka!

This repository is intended to be run during a workshop about Kafka.

# Setup

The Project is set up to teach the fundamentals of Kafka itself. You will connect to it, publish messages, make changes and see it everywhere on everyone's device.
Each step becomes a littlebit more difficult than the last. If you get lost or do not know what to do you can always ask for help and we will (try) to explain why things are happening the way they are. 
Each section has its own addition to this README.md file. Each step can be found in a separate branch. You can run the command `git branch -r` to find out all the steps that exist.

To check out a separate step you can issue the following command:

```shell
git checkout -b <branch name> origin/<branch name>
```

After such a command the new branch is visible.

If you become lost and want to reset to the beginning you can always use `git reset --hard`

To continue with this workshop fill in `git checkout origin/step-1 -b step-1`


# Step 1

The first step is to set up your Java project. After that we will get access to the Kafka instance.  
For your convenience a publicly available cluster has been setup. The location of the bootstrap server is located on `kafka.cluster.dissi.me:32100`.  
To make sure that only _you_ can connect a username/password combination will be generated on the webpage. It ensures you can consume messages that are currently being produced.

The user configuration is available on https://kafka.dissi.me/?tab=me

In this workshop we will utilize the "[org.springframework.kafka](https://docs.spring.io/spring-boot/docs/2.7.4/reference/htmlsingle/#messaging.kafka)" library to connect to the server.

