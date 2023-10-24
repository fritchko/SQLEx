import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

fun main(args: Array<String>) {

    try {
        val url = "jdbc:mysql://localhost:3306/newdb"
        val user = "developer"
        val password = "rA9x64_2"

        val connection: Connection = DriverManager.getConnection(url, user, password)


        val query = "SELECT name, last_name FROM students;"
        val statement = connection.createStatement()

        val resultSet: ResultSet = statement.executeQuery(query)

        val surnames: ArrayList<String> = ArrayList()

        while (resultSet.next()) {
            val firstName: String = resultSet.getString("name")
            val lastName: String = resultSet.getString("last_name")

            println("Student name: $firstName")
            surnames.add(lastName)
        }

        println("Surnames: ")
        for (surname in surnames) {
            println(surname)
        }

    } catch (sqlException: SQLException) {
        println("sql exception: $sqlException")
    }
}