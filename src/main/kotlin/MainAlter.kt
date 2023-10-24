import java.sql.*

fun main(args: Array<String>) {

    try {
        val url = "jdbc:mysql://localhost:3306/newdb"
        val user = "developer"
        val password = "rA9x64_2"

        val connection: Connection = DriverManager.getConnection(url, user, password)

        val addColumn = "ALTER TABLE students ADD COLUMN country VARCHAR(30);"
        val statement: Statement = connection.createStatement()
//        statement.executeUpdate(addColumn)

        val updateData = "UPDATE students SET country = ? WHERE id = ?;"
        val preparedStatement: PreparedStatement = connection.prepareStatement(updateData)

        try {
            preparedStatement.setString(1, "Italy")
            preparedStatement.setInt(2, 1)
            preparedStatement.executeUpdate()

            preparedStatement.setString(1, "Poland")
            preparedStatement.setInt(2, 2)
            preparedStatement.executeUpdate()

            preparedStatement.setString(1, "USA")
            preparedStatement.setInt(2, 3)
            preparedStatement.executeUpdate()

            preparedStatement.setString(1, "UK")
            preparedStatement.setInt(2, 4)
            preparedStatement.executeUpdate()

            println("Update successful.")


        } catch (updateError: SQLException) {
            println("Update failed with error $updateError")
        }
    } catch (sqlException: SQLException){
        println("sql exception: $sqlException")
    }
}