package cpsc411.Assign1

import com.google.gson.Gson
import cpsc411.Assign1.RestDB.Claim.Claim
import cpsc411.Assign1.RestDB.Claim.ClaimDB
import cpsc411.Assign1.RestDB.Database
import io.ktor.application.*
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.utils.io.readAvailable

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    routing{
        get("/ClaimService/getAll"){
            val claimlist = ClaimDB().getAll()
            println("The number of students: ${claimlist}")
            //JSON Serialization/Deserialization
            val response = Gson().toJson(claimlist)
            //converts kotlin string into a JSON format
            call.respondText(response, status=HttpStatusCode.OK, contentType = ContentType.Application.Json)
        }
        post("/ClaimService/add"){
            val contType = call.request.contentType()
            val data = call.request.receiveChannel()
            val dataLength = data.availableForRead
            var output = ByteArray(dataLength)
            data.readAvailable(output)
            val str = String(output)
            call.respondText("Post Request success", status = HttpStatusCode.OK, contentType = ContentType.Text.Plain)

        }
      
    }

}

