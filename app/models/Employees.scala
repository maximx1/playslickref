package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import scala.util.Try

case class Employee(
  id: Option[Int],
  firstName: String,
  lastName: String,
  email: String,
  jobId: Int
) extends Model

class Employees(tag: Tag) extends Table[Employee](tag, "EMPLOYEES") {
  def id = column[Option[Int]]("ID", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("FIRST_NAME", O.NotNull)
  def lastName = column[String]("LAST_NAME", O.NotNull)
  def email = column[String]("EMAIL")
  def jobId = column[Int]("JOB_ID")
  def * =  (id, firstName, lastName, email, jobId) <> (Employee.tupled, Employee.unapply)
}

object Employees extends BaseSlickTrait[Employee] {
  def model = TableQuery[Employees]
  
  def byId(id: Int) = DB withSession { implicit session => model.filter{_.id === id}.list}
  
  def getJobsForEmployee(id: Int) = DB withSession { implicit session =>
    model.filter(_.id === id)
      .flatMap(x => 
        Jobs.model.filter(_.id === x.jobId)
      ).list
  }
  
  override def +=(e: Employee): Try[Int] = Try { DB withSession { implicit session => 
    (model returning model.map(_.id.get)) += e
  }}
}