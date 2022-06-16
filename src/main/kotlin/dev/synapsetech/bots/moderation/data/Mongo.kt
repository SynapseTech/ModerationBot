package dev.synapsetech.bots.moderation.data

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

object Mongo {
    lateinit var client: MongoClient
    lateinit var database: MongoDatabase

    fun connect(config: MongoConfiguration) {
        client = KMongo.createClient(config.connectionString)
        database = client.getDatabase(config.database)
    }

    inline fun <reified T : Any> collection(name: String): MongoCollection<T> = database.getCollection<T>(name)
}