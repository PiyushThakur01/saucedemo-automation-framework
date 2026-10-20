# Test Plan: SauceDemo E-commerce Application

| | |
|---|---|
| Application | SauceDemo (https://www.saucedemo.com/) |
| Prepared by | Your Name |
| Version | 1.0 |

## 1. Objective
Verify that the core shopping flow (login, browse products, manage cart, checkout) works correctly and
that invalid input is handled with proper error messages.

## 2. Scope
**In scope:** Login/logout, product listing and sorting, add/remove from cart, checkout form and order completion.
**Out of scope:** Performance, security and load testing, payment gateway (not present in the demo).

## 3. Test approach
- Manual functional testing using equivalence partitioning, boundary value analysis and negative testing
- UI automation (Selenium + Java + TestNG, Page Object Model) for smoke and regression scenarios
- Defects logged with steps, expected/actual result, severity, priority and screenshot

## 4. Test environment
| Item | Detail |
|---|---|
| Browsers | Chrome (primary), Firefox, Edge |
| OS | Windows 10/11, Ubuntu (CI) |
| Test data | standard_user, locked_out_user, problem_user, performance_glitch_user / secret_sauce |

## 5. Entry criteria
- Application is accessible and test data is available
- Test cases are reviewed

## 6. Exit criteria
- 100% of planned test cases executed
- All critical and high severity defects fixed or accepted
- Test summary report prepared

## 7. Risks and mitigation
| Risk | Mitigation |
|---|---|
| Public demo site may be slow or down | Retry, use explicit waits, schedule runs off-peak |
| UI locator changes | Keep locators in page objects only |

## 8. Deliverables
Test plan, test cases (docs/Test_Cases.xlsx), automation suite, Extent report, defect reports, test summary.
