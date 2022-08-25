package insurance

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

class InsuranceAnalysisSimulation extends Simulation {

  val noOfUsers = 50
  val rampUpTimeSecs = 60 seconds
  val minWaitMs = 1000 milliseconds
  val maxWaitMs = 5000 milliseconds
  val baseURL = "http://localhost:8080"

  val httpConfiguration = http
    .baseUrl(baseURL)
    .acceptHeader("application/json")

  val scn = scenario("Create Insurance Analysis and Get Results")
    .exec(http(s"Create insurance analysis request")
      .post(s"/v1/insurances-analysis")
      .body(StringBody("""{"age":35,"dependents":2,"house":{"ownership_status":"OWNED"},"income":1,"vehicle":{"year":2018},"marital_status":"MARRIED","risk_questions":[false,true,false]}""".stripMargin)).asJson
      .check(status.in(200))
    ).pause(minWaitMs, maxWaitMs)

  setUp(
    scn.inject(rampUsers(noOfUsers) during (rampUpTimeSecs))
  ).protocols(httpConfiguration)


}