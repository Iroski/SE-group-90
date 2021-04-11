# Project Git Submit Submission Specification

## Background
-Every git submission, a commit message will be written to describe the content of the submission. The more accurate the description, the easier it is to manage the project.
-If you enter the commit content at will, the disadvantages are:
  -A rollback is not easy to control
  -If the submission record is not standardized, it will be more difficult to view the git log of a project
  -Unable to fully grasp the submission status

## How to do it
Reference https://segmentfault.com/a/1190000017205604?utm_source=tag-newest

### Formulate our development team's git commit submission specifications
```
Format [service] type:description
```
1. service
   <br> This field indicates that our submission is for the front and back ends, and it is also for the convenience of filtering in the git records later.
   The abbreviations for the front and back ends are as follows
   
   ```
   frontend: [F]
   backend: [B]
   If you start something else later, you can add it here
   ```
2. type
   <br> The category of logo submission, we stipulate the following logos
   ```
   1. ADD: New features have been added to the logo
   2. FIX: Fix bugs
   3. MOD: Update/iteration of some existing functions
   4. STYLE: code refactoring
   5. DEL: delete code
   ```
3. description
   <br> A short description of the submission

### Example
```
After adding the password modification interface, the submitted information can be as follows
git commit -m'[B] ADD: change passwd'
The B here stands for the back end


For example, after functional iteration, the login interface needs to be adjusted:
git commit -m'[F] MOD: login interface'


For example, after adding the function of public folders, the submission information can be as follows:
git commit -m'[F] ADD: common directory'

If multiple adjustments are made at the same time, the submission information can be:
git commit -m'[F] ADD/FIX: xxxx'

Of course, when uploading documents to our document warehouse, there is no need to mark the service, such as directly
git commit -m'ADD: commit specification_doc'
```

The description does not need to be in English, but Chinese is also acceptable.