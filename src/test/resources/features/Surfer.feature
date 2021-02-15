Feature: Choose a suitable surfing day

  @api-test
  Scenario: Choosing top 2 spots to surf in next 16 days
    Given I like to surf in any of 2 beaches of Sydney, out of the following
      | Beach           | PostCode |
      | Bondi Beach     | 2026     |
      | Manly Beach     | 2095     |
      | Avalon Beach    | 2107     |
      | Coogee Beach    | 2034     |
      | Cronulla Beach  | 2230     |
      | Palm Beach      | 2108     |
      | Dee Why Beach   | 2099     |
      | Curl Curl Beach | 2096     |
      | Tamarama Beach  | 2026     |
      | Maroubra Beach  | 2035     |
    And I only like to surf on following days of the week in next 16 days
      | MONDAY | FRIDAY |
    When I look up the weather forecast with POSTAL CODES
    And I pick spots where temperature is between 12°C and 30°C
    And I pick spots where wind speed is between 3 and 9
    And I pick spots where UV index is less than or equal to 12
    Then I pick best suitable spot out of top two spots, based upon suitable weather forecast for the day and display it co-ordinates
