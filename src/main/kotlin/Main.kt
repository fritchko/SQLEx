import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

fun main(args: Array<String>) {

    try {
        val url = "jdbc:mysql://localhost:3306/newdb"
        val user = "developer"
        val password = "rA9x64_2"

        val connection: Connection = DriverManager.getConnection(url, user, password)

        val statement = connection.createStatement()

        val createTable = "CREATE TABLE IF NOT EXISTS STUDENTS(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(100)," +
                "last_name VARCHAR(100));"

        statement.execute(createTable)

        insertStudent(statement, "Davide", "Riccio")
        insertStudent(statement,"Giovanni","Arsoni")
        insertStudent(statement,"Como","Estas")
        insertStudent(statement,"Clarissa","Von Sarovich")
        selectAndPrint(statement)

    } catch (sqlException: SQLException) {
        println("sql exception: $sqlException")
    }
}

private fun insertStudent(statement: Statement, name: String, lastName: String) {
    val query = "INSERT INTO students(name, last_name)" +
            "VALUES('$name', '$lastName');"
    statement.execute(query)
}

private fun selectAndPrint(statement: Statement) {
    val result = statement.executeQuery("SELECT * FROM students")
    while (result.next()) {
        val id = result.getInt("id")
        val name = result.getString("name")
        val lastName = result.getString("last_name")

        println("$id\t $name\t $lastName")
    }
}
