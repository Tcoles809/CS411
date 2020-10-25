package cpsc411.Assign1.RestDB.Claim

import cpsc411.Assign1.RestDB.RestDB
import cpsc411.Assign1.RestDB.Database
import java.util.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



class ClaimDB : RestDB() {

    fun addClaim(pObj : Claim) {
     val conn = Database.getInstance()?.getDbConnection()

        sqlStmt = "Inserting into database(id, title, date, isSolved) values ( '${pObj.id}}', {'${pObj.title}}',{'${pObj.date}}', {'${pObj.isSolved}) "

        conn?.exec(sqlStmt)
    }
    fun getAll() : List<Claim>{
        val conn = Database.getInstance()?.getDbConnection()

        sqlStmt = "Select the claim's UUID, title, date, and if solved"


        var claimList : MutableList<Claim> = mutableListOf()
        val st = conn?.prepare(sqlStmt)
        while(st?.step()!!){
           var id : UUID = UUID.randomUUID()
           fromUUID(id)
           st.columnString(0)

           var title = st.columnString(1)

           var date : String = ""
           getDate(date)
           st.columnString(2)

           var isSolved : Boolean = false
           booleanToInt(isSolved)
           st.columnInt(3)

           claimList.add(Claim(id,title,date,isSolved))
        }
        return claimList
    }

    /*From textbook*/
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
    fun booleanToInt(bool : Boolean): Int {
       return if(bool) 1 else 0
    }
    fun getDate(d : String) : String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
      return formatted
    }

}

