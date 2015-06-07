package controllers

import java.util.Date

import play.api._
import play.api.mvc._

class Application extends Controller {

  def index = Action { implicit request =>
    val sessionInfo = request.session.get("info").getOrElse("")
    Ok(views.html.index(sessionInfo))
  }

  def setCookie = Action {
    Redirect(routes.Application.index)
      .withSession("info" -> s"Hello world - ${new Date().toString}")
  }

  def esiSnippet = Action { implicit request =>
    val sessionInfo = request.session.get("info").getOrElse("")
    Ok(views.html.snippet(sessionInfo))
  }
}
