# SkyRewards
Mock server responses using retrofit 2.

The summary:
This project to show case how to mock server responses with retrofit2 and RxAndroid.
The SkyRewards application contains a simple screen which asks the user to enter their accountId and lets the user to press the get rewards button to query the available rearwards for the given account.

As i have mocked the server responses, the expected scenarios are:
For given accountId 123 the user will get the full list of rewards
For given accountId 111 the user will be shown the ineligible message
For given accountId 222 the user will be shown the invalid account message
For any other accountIds the server error message will be shown

The Architecture:
The project follows the Cleaner Architecture which lets me to apply SOLID principle as well as to write clean code.

Model View Presenter
I am using the MVP as a pattern to keep the separation of Core (business logic) from App (Android specific classes), so that i can manage a clean pure core domain - which doesn't depended on any other outside dependencies.
This lets me to unit test the business logic easily.
 
RxAndroid
I have chosen Rx as the framework to give the project a good concurrency management, which lets me to compute accessing the rewards from either network or local cache in a separate worker thread and switch back to main thread to show results with the updated UI.

Inversion Of Control (IOC)
I am using Dependency Injection by means of Dagger to give the IOC in the layers code.

Retrofit
I am using retrofit library, in order to make HTTP calls to fetch rewards data for user from server.
As the internal services are not yet implemented, Retrofit lets me to mock the server response - in a way i want to manage all possible acceptance criterias were asked.

Caching
I have implemented an in memory caching mechanism for storing the rewards that are returned for a given account. This way i can reload the cached rewards for the same user accounrt, on configuration changes and avoid calling network to get the rewards multiple times.

TDD
I have followed the Test Driven Development, and have covered all classes that handle logic in the Core module.
I have used Fest to write meaningful statements and Mockito for running the tests.

Further Improvements:

1. Improved Dagger Scope 
As this is a simple app which has a screen (Activity) I didn't need to introduce ActivityScope for Dagger to provide the dependencies just for the activity. 
I could improve this - then it is possible to save the dagger graph to persist dependencies on configuration changes. Instead, currently all the dependencies are Application Scoped and kept through out the whole application life cycle, which is not great in memory allocation.

2. Improved UI
- The UI to show the rewards are very basic and i can improve the UI with showing the rewards in a seperate screen with more descriptive information of the rewards for the user. 
- The if the cases are for invalid account or ineligible or server error, i could improve handling those with Dialogs/SnackBars appropriately with better handling of those user scenarios with what calls to action to make.

3. Integration tests
Currently there are no integration tests in the project, where as I would use MockWebServer to mock the server response which will allow me to write integration tests to check more units (presenter, interactor and providers) working together correctly.

4. Acceptance Tests 
I could have used Espresso and Cucumber to write BDD statements that will add acceptance tests to check the "happy path" in the project.
