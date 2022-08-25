Feature: Result feature

  Background:
    * url insuranceServiceBaseUrl
    * def insuranceAnalysisEndpoint = '/v1/insurances-analysis'
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'

  Scenario: Create insurance analysis, without income
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.income = 0
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

  Scenario: Create insurance analysis, without vehicle
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

  Scenario: Create insurance analysis, without house
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

  Scenario: Create insurance analysis, with over 60 year old
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.age = 61
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 200
    And match response ==
    """
    {
      "auto": "responsible",
      "disability": "ineligible",
      "home": "regular",
      "life": "ineligible"
    }
    """

  Scenario: Create insurance analysis, with over 29 year old
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.age = 29
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 200
    And match response ==
    """
    {
      "auto": "regular",
      "disability": "economic",
      "home": "economic",
      "life": "regular"
    }
    """

  Scenario: Create insurance analysis, with over 31 year old
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.age = 31
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 200
    And match response ==
    """
    {
      "auto": "responsible",
      "disability": "economic",
      "home": "economic",
      "life": "regular"
    }
    """

  Scenario: Create insurance analysis, with income over 200k
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.income = 200001
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 200
    And match response ==
    """
    {
      "auto": "regular",
      "disability": "economic",
      "home": "economic",
      "life": "regular"
    }
    """

  Scenario: Create insurance analysis, with mortgaged house
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.house.ownership_status = 'MORTGAGED'
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 200
    And match response ==
    """
    {
      "auto": "responsible",
      "disability": "regular",
      "home": "regular",
      "life": "regular"
    }
    """

  Scenario: Create insurance analysis, with vehicle older than 5 years ago
    * def createInsuranceAnalysisRequestBody = read('resources/createInsuranceAnalysisRequest.json')
    * set createInsuranceAnalysisRequestBody.vehicle.year = 1990
    Given path insuranceAnalysisEndpoint
    And request createInsuranceAnalysisRequestBody
    When method POST
    Then status 200
    And match response ==
    """
    {
      "auto": "economic",
      "disability": "economic",
      "home": "economic",
      "life": "regular"
    }
    """