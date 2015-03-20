package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

case class Job(
    id: Option[Int] = None,
    title: String,
    lowerPayLevel: Double,
    upperPayLevel: Double,
    isOpen: Boolean
)

class Jobs(tag: Tag) extends Table[Job](tag, "JOBS") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def title = column[String]("TITLE", O.NotNull)
  def lowerPayLevel = column[Double]("LOWER_PAY_LEVEL")
  def upperPayLevel = column[Double]("UPPER_PAY_LEVEL")
  def isOpen = column[Boolean]("IS_OPEN")
  
  def * = (id.?, title, lowerPayLevel, upperPayLevel, isOpen) <> (Job.tupled, Job.unapply)
}

object Jobs extends BaseSlickModel {
  lazy val jobs = TableQuery[Jobs]
  def all = DB withSession { implicit session => jobs.list }
  def size = DB withSession { implicit session => jobs.length.run }
}