package controllers

import play.api._
import play.api.mvc._
import models.{Jobs, Employees}

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Jobs:" + Jobs.size + ", Employees: " + Employees.size))
  }

}