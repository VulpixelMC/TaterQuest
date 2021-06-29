package dev.tehc.taterquest.util

import org.apache.logging.log4j.LogManager


private val logger = LogManager.getLogger("TaterQuest")!!

@Suppress("unused")
internal fun info(msg: String) = logger.info(msg)

@Suppress("unused")
internal fun warn(msg: String) = logger.warn(msg)

@Suppress("unused")
internal fun error(msg: String) = logger.error(msg)

@Suppress("unused")
internal fun fatal(msg: String) = logger.fatal(msg)

@Suppress("unused")
internal fun debug(msg: String) = logger.debug(msg)
