Feature: Validate contracts

  Background:
    * url insuranceServiceBaseUrl
    * def insuranceAnalysisEndpoint = '/v1/insurances-analysis'
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'

  Scenario: Create insurance analysis even without house
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.house = null
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 200
    And match response ==
    """
    {
      "auto": "responsible",
      "disability": "economic",
      "home": "ineligible",
      "life": "regular"
    }
    """

  Scenario: Create insurance analysis even without vehicle
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.vehicle = null
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 200
    And match response ==
    """
    {
      "auto": "ineligible",
      "disability": "economic",
      "home": "economic",
      "life": "regular"
    }
    """

  Scenario: Fail, invalid age
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.age = -1
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 400
    And match response == {"code":"ERR-001","message":"Minimal age is 0"}

  Scenario: Fail, sending null age
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.age = null
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 400
    And match response == {"code":"ERR-001","message":"Age must be informed"}

  Scenario: Fail, invalid dependents
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.dependents = -1
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 400
    And match response == {"code":"ERR-001","message":"Minimal dependents is 0"}

  Scenario: Fail, sending null dependents
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.dependents = null
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 400
    And match response == {"code":"ERR-001","message":"Dependents must be informed"}

  Scenario: Fail, invalid income
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.income = -1
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 400
    And match response == {"code":"ERR-001","message":"Minimal income is 0"}

  Scenario: Fail, sending null income
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.income = null
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 400
    And match response == {"code":"ERR-001","message":"Income must be informed"}

  Scenario: Fail, sending null marital status
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.marital_status = null
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 400
    And match response == {"code":"ERR-001","message":"Marital status must be informed"}

  Scenario: Fail, sending invalid risk questions
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.risk_questions = [true, false]
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 400
    And match response == {"code":"ERR-001","message":"Needed 3 risk questions answers"}

  Scenario: Fail, sending null risk questions
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.risk_questions = null
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 400
    And match response == {"code":"ERR-001","message":"Risk questions must be informed"}