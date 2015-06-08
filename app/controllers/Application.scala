package controllers

import java.util.Date
import javax.inject.Inject

import play.api._
import play.api.libs.ws.WSClient
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._


class Application @Inject() (ws: WSClient) extends Controller {

  def index = Action.async { implicit request =>
    val sessionInfo = request.session.get("info").getOrElse("")
    // val headers = request.cookies.map(c => "Cookie" -> c.value)
    val headers = request.headers.headers
    val snippet = ws.url(routes.Application.esiSnippet().absoluteURL()).withHeaders(headers:_*)
    snippet.get().map { resp =>
      Ok(views.html.index(request.headers.headers.toMap, sessionInfo, resp.body))
    }
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
