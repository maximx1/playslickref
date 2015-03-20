package controllers

import play.api.libs.json._
import play.api.mvc._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import com.github.tototoshi.play2.json4s.jackson._
import models._

object Application extends Controller with Json4s {
    implicit val format = DefaultFormats
    
    def index = TODO
    
    def allJobs = Action { implicit request =>
      Ok(Extraction.decompose(Jobs.all))
    }
    
    def allEmployees = Action { implicit request => 
      Ok(Extraction.decompose(Employees.all))
    }
    
    def jobsForEmployee(id: Int) = Action { implicit request =>
      val x = Employees.getJobsForEmployee(id)
      Ok(Extraction.decompose(x))  
    }
    
    def employeeById(id: Int) = Action { implicit request => 
      Ok(Extraction.decompose(Employees.byId(id)))
    }
    
    def createEmployee = Action(json) { implicit request =>
      val newEmployee = request.body.extract[Employee]
      Employees += newEmployee
      Ok(Extraction.decompose(BaseResponse("ok", "")))
    }
}