# Bug Report Template

Use this format in Jira (or any tracker) for every defect.

| Field | Details |
|---|---|
| Bug ID | BUG-001 |
| Title | Short, specific summary of the problem |
| Module | Login / Inventory / Cart / Checkout |
| Environment | Browser + version, OS, build/URL |
| Severity | Critical / High / Medium / Low |
| Priority | P1 / P2 / P3 |
| Status | New / Open / Fixed / Retest / Closed |
| Reported by / date | Your Name, DD-MMM-YYYY |

**Preconditions:**

**Steps to reproduce:**
1.
2.
3.

**Expected result:**

**Actual result:**

**Attachments:** screenshot / screen recording / console log

---

## Worked example (format only: replace with defects you personally find and verify)

| Field | Details |
|---|---|
| Bug ID | BUG-001 |
| Title | Same image shown for every product when logged in as problem_user |
| Module | Inventory |
| Severity / Priority | Medium / P2 |

**Steps to reproduce:**
1. Open https://www.saucedemo.com/
2. Log in with `problem_user` / `secret_sauce`
3. Look at the product images on the Products page

**Expected result:** Each product shows its own image.
**Actual result:** All products show the same image.
