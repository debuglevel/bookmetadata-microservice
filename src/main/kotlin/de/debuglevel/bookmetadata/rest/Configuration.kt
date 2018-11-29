package de.debuglevel.bookmetadata.rest

import com.natpryce.konfig.*
import com.natpryce.konfig.Configuration
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import java.io.File

object Configuration {
    val configuration: Configuration

    init {
        var config: Configuration = systemProperties()

        config = config overriding
                EnvironmentVariables()

        config = config overriding
                ConfigurationProperties.fromOptionalFile(File("configuration.properties"))

        val defaultsPropertiesFilename = "defaults.properties"
        if (ClassLoader.getSystemClassLoader().getResource(defaultsPropertiesFilename) != null) {
            config = config overriding
                    ConfigurationProperties.fromResource(defaultsPropertiesFilename)
        }

        configuration = config
    }

    val dnbAccesstoken = configuration.getOrNull(Key("dnb.accesstoken", stringType)) ?: ""
}