# Lab 19 — regression notes

## unit vs IT vs UI scope
- Unit tests cover pure logic (models, validators) with no Spring context or network.
- IT tests boot the real app on a random port and hit HTTP endpoints directly via TestRestTemplate
- UI tests drive the same app through a real headless browser via Selenium, using data-testid locators only, 
and should stay limited to end-to-end flows already-covered logic doesn't need re-testing here.

## Headless CI strategy
-Chrome runs headless via ChromeOptions, with WebDriverManager resolving the matching driver binary per CI image so versions don't drift. 
Waits are explicit only (implicit wait set to 0) to avoid flaky timing,

## Correlationg lab-request-001
-The X-Correlation-Id header sent by the client is expected to be echoed back by the API and to appear in server logs for that request, letting a single test/run be traced end-to-end through the logs.
