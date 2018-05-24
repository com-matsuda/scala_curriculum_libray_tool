import scalikejdbc._
import com.typesafe.config._

case class book_data(id: Int,title: String )

object book_data_map {

  def apply(rs: WrappedResultSet) = new book_data(rs.int("id"), rs.string("title"))
}

object booka_lib {

  def showbooklist(db_path: String , db_user: String ,db_pass: String): List[book_data] = {
    Class.forName("org.hsqldb.jdbc.JDBCDriver")
    ConnectionPool.singleton(db_path,db_user,db_pass)
    DB readOnly { implicit session =>
      var book_list: List[book_data] = sql"select id,title from book_list".map {rs => book_data_map(rs)}.list.apply()
      book_list
    }
  }

  def main(args: Array[String]): Unit = {
    //check args
    if (args.length < 1) {
      println("[ERROR] please provide least one argument")
    }
    else if (args(2) == "LISTBOOKS") {

      //load_conf
      val config = ConfigFactory.load()
      //load DB setting
      val db_path = config.getString("db_setting.db_path")
      val db_user = config.getString("db_setting.db_user")
      val db_pass = config.getString("db_setting.db_pass")

      //main execute
      println("[Execute] List all books")
      var results_list = showbooklist(db_path,db_user,db_pass)
      results_list.foreach(lib_book => println(lib_book.title))
    }
    else {
      //unexpected argument
      println("[ERROR]Something wrong with argument")
    }
  }
}