package models

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

trait BaseSlickModel {
//    def all[T](tq: TableQuery[T]) = DB withSession { implicit session => tq.list }
//    def size[T](tq: TableQuery[T]) = DB withSession { implicit session => tq.length.run }
}