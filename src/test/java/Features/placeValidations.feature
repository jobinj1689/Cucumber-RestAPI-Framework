Feature: Validating Place API's

@AddPlace&GetPlace
Scenario Outline: Verify if Place is being succesfully added using AddPlaceAPI
Given Add Place Payload with <lat> <lng> "<name>" "<website>" "<language>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
|   lat     |  lng    | 	name   |   website    | language|
|-38.383494 |33.427362|Jobins House| www.test.com | indi    |
# |-00.001001 |99.000900|Owaiss House| www.xyz.com  | English |

@DeletePlace
Scenario: Verify if Delete Place API functionality is working
Given Delete Place Payload
When user calls "DeletePlaceAPI" with "Post" http request
Then the API call is success with status code 200
And "status" in response body is "OK"
