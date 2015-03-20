package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import java.sql.SQLException
import scala.util.Try
import scala.slick.lifted.ProvenShape

trait Model
abstract class ModelTable[T <: Model](tag: Tag, tableName: String) extends Table[T](tag, tableName) 

abstract class BaseSlickModel[E <: Model, T <: ModelTable[E]] {
  var model: TableQuery[T] = null
  
  def all = DB withSession { implicit session => model.list }
  def size = DB withSession { implicit session => model.length.run }
  def +=(e: E): Try[E] = Try { DB withSession { implicit session =>
      model.+=(e)
      e
  }}
}

trait BaseSlickTrait[E <: Model] {
  def model: TableQuery[_ <: ModelTable[E]]
  def all = DB withSession { implicit session => model.list }
  def size = DB withSession { implicit session => model.length.run }
  def +=(e: E): Try[E] = Try { DB withSession { implicit session =>
      (model returning model.map(c => c)) += e
  }}
}