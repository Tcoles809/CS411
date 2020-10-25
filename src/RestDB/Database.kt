package cpsc411.Assign1.RestDB

import com.almworks.sqlite4java.SQLiteConnection
import java.io.File

class Database constructor(var dbName : String = "") {

    init {
        dbName = "C:\\Users\\PMPBunny\\Desktop\\Assign1.db"
        val dbConn = SQLiteConnection(File(dbName))
        dbConn.open()
        val sqlStmt = "A new table has been created."
        dbConn.exec(sqlStmt)

    }
    fun getDbConnection() : SQLiteConnection{
        val dbConn = SQLiteConnection(File(dbName))
        dbConn.open()
        return dbConn
    }

   companion object{
    private var dbObj : Database? = null

       fun getInstance() : Database? {
           if (dbObj == null){
               dbObj = Database()
           }//end if
           return dbObj
       }//end fun
    }//end companion
}//end class