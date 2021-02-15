# Tech Challenge


## How to Run tests

###API tests
mvn clean verify -Dcucumber.filter.tags="@api-test"

###UI tests
mvn clean verify -Dcucumber.filter.tags="@ui-test"

###Both API and UI
mvn clean verify