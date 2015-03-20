package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

trait Model

abstract class BaseSlickModel(val model: TableQuery[_ <: Model]) {
  def all = DB withSession { implicit session => model.list }
  def size = DB withSession { implicit session => model.length.run }
}