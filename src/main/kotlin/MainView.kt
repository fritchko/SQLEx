import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

fun main(args: Array<String>) {
    data class Student(val name: String, val lastName: String)

    try {
        val url = "jdbc:mysql://localhost:3306/newdb"
        val user = "developer"
        val password = "rA9x64_2"

        val connection: Connection = DriverManager.getConnection(url, user, password)

        val createItalianView = "CREATE OR REPLACE VIEW italian_students " +
                "AS SELECT name, last_name FROM students WHERE country = 'Italy';"

        val statement: Statement = connection.createStatement()
        statement.execute(createItalianView)
        println("Created 'italian_students' view.")

        val createPolishView =
            "CREATE OR REPLACE VIEW polish_students AS SELECT name, last_name FROM students WHERE country = 'Poland';"
        statement.execute(createPolishView)
        println("Created 'polish_students' view.")

        val selectItalian = "SELECT name, last_name FROM italian_students;"
        val italianResultSet = statement.executeQuery(selectItalian)

        val italianStudents = ArrayList<Student>()

        while (italianResultSet.next()) {

            val name = italianResultSet.getString("name")
            val lastName = italianResultSet.getString("last_name")

            italianStudents.add(Student(name, lastName))

        }

        val selectPolish = "SELECT name, last_name FROM polish_students;"
        val polishResultSet: ResultSet = statement.executeQuery(selectPolish)

        val polishStudents = ArrayList<Student>()

        while (polishResultSet.next()) {

            val name = polishResultSet.getString("name")
            val lastName = polishResultSet.getString("last_name")

            polishStudents.add(Student(name, lastName))

        }

        for (italian in italianStudents){
            println(italian)
        }

        for (polish in polishStudents){
            println(polish)
        }
    } catch (error: SQLException){
        println("Error: $error")
    }


}
