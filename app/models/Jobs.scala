package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

case class Job(
    id: Option[Int],
    title: String,
    lowerPayLevel: Double,
    upperPayLevel: Double,
    isOpen: Boolean
) extends Model

class Jobs(tag: Tag) extends Table[Job](tag, "JOBS") {
  def id = column[Option[Int]]("ID", O.PrimaryKey, O.AutoInc)
  def title = column[String]("TITLE", O.NotNull)
  def lowerPayLevel = column[Double]("LOWER_PAY_LEVEL")
  def upperPayLevel = column[Double]("UPPER_PAY_LEVEL")
  def isOpen = column[Boolean]("IS_OPEN", O.NotNull, O.Default(false))
  
  def * = (id, title, lowerPayLevel, upperPayLevel, isOpen) <> (Job.tupled, Job.unapply)
}

object Jobs extends BaseSlickTrait[Job] {
  def model = TableQuery[Jobs]
  def byId(id: Int) = DB withSession { implicit session => model.filter(_.id === id).list }
}