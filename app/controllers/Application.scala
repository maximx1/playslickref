package controllers

import play.api.libs.json._
import play.api.mvc._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import com.github.tototoshi.play2.json4s.jackson._
import models._
import scala.util.{Try, Success, Failure}
import java.sql.SQLException

case class BatchEmployees(employees: Seq[Employee])

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
      val result = Employees += newEmployee
      Ok(Extraction.decompose(result match {
        case Success(id) => BaseResponse("ok", "New id: " + id)
        case Failure(e: SQLException) => BaseResponse("fail", "SQL Error: " + e.getMessage)
        case _ => BaseResponse("fail", "Unspecified error")
      }))
    }
    
    def createEmployees = Action(json) { implicit request =>
      val contents = request.body.extract[BatchEmployees]
      val result = Employees ++= contents.employees
      Ok(Extraction.decompose(result match {
        case Success(id) => BaseResponse("ok", id.get.toString)
        case Failure(e: SQLException) => BaseResponse("fail", "SQL Error: " + e.getMessage)
        case _ => BaseResponse("fail", "Unspecified error")
      }))
    }
}