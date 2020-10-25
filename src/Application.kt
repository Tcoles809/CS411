package cpsc411.Assign1

import com.google.gson.Gson
import cpsc411.Assign1.RestDB.Claim.Claim
import cpsc411.Assign1.RestDB.Claim.ClaimDB
import cpsc411.Assign1.RestDB.Database
import io.ktor.application.*
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    routing {
        get("/ClaimService/getAll") {
            val claimlist = ClaimDB().getAll()
            //JSON Serialization/Deserialization
            val response = Gson().toJson(claimlist)
            //converts kotlin string into a JSON format
            call.respondText(response, status = HttpStatusCode.OK, contentType = ContentType.Application.Json)
        }
        post("/ClaimService/add") {
            val id = UUID.randomUUID()
            val title: String? = call.request.queryParameters["Title"]
            var date : String? = call.request.queryParameters["date"]
            var isSolved : Boolean = false
            val response = String.format("ID%s, Title %s, date %s, and if solved %d", id, title, date, isSolved)

            val cObj = Claim(id, title, date, isSolved)
            val claim_entry = ClaimDB().addClaim(cObj)
            call.respondText(response, status = HttpStatusCode.OK, contentType = ContentType.Text.Plain)

        }

    }

}
