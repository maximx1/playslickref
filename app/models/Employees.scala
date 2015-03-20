package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

case class Employee(
  id: Option[Int] = None,
  firstName: String,
  lastName: String,
  email: String,
  jobId: Int
)

class Employees(tag: Tag) extends Table[Employee](tag, "EMPLOYEES") with Model {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("FIRST_NAME", O.NotNull)
  def lastName = column[String]("LAST_NAME", O.NotNull)
  def email = column[String]("EMAIL")
  def jobId = column[Int]("JOB_ID")
  def * =  (id.?, firstName, lastName, email, jobId) <> (Employee.tupled, Employee.unapply)
  
  def job = foreignKey("JOBS", jobId, TableQuery[Jobs])(_.id)
}

object Employees extends BaseSlickModel(TableQuery[Employees])