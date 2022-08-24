Feature: Result feature

  Background:
    * url insuranceServiceBaseUrl
    * def insuranceAnalysisEndpoint = '/v1/insurances-analysis'
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'

  Scenario: Create insurance analysis
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 200
    And match response ==
    """
    {
      "auto": "responsible",
      "disability": "ineligible",
      "home": "economic",
      "life": "regular"
    }
    """