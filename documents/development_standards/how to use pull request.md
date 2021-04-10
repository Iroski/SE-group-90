# How to use Pull Request for team collaboration
Github provides the Pull Request function, through which team members can easily collaborate and conduct Code Review to ensure the stability and robustness of the code.

the term:
-Main warehouse: refers to the remote warehouse maintained under the dssc-group organization
-Your own Fork warehouse: refers to the warehouse you fork to your own directory through Github's Fork function
-Local warehouse: refers to clone from own Fork warehouse to local warehouse through `git clone`

The specific process is as follows:
## Fork the main warehouse to its own directory
Enter the main warehouse and click the Fork button in the upper right corner of the page to fork the main warehouse to your account. At this time, you will have a warehouse that is fully synchronized with the current status of the main warehouse.
## Clone your Fork warehouse to the local
Use Git Bash or other similar software to clone the remote warehouse under your account to the local. Take my account as an example:
```
git clone https://github.com/Iroski/SE-group-90.git
```
Later, when developing, our workflow is:
1. Create a local branch for a certain function and write content

2. Commit the changes of the branch to the local warehouse

3. Push the locally coded branch to the remote warehouse of your fork

4. Pull Request to a branch of the main warehouse (usually master) under this branch of your remote warehouse. If there is a conflict, you need to resolve the conflict

5. The team member is responsible for reviewing the relevant code. If there is no problem, you can approve the modification. At least one non-member ^1^ can merge the Pull Request into the main warehouse after approval. If you find a problem (such as BUG, ​​code style, etc.), you can comment on the corresponding code and communicate with the author. The PR initiator can make changes in the local warehouse based on the comments, and push again to the corresponding branch of the remote warehouse of his Fork. After github detects this modification, it will automatically synchronize the changes in the PR, and refresh the page to see the update.

   ^1^Note: When you modify the GUI and code a lot, you need to find a random person from another group to view the pr. For example, if Chichi and Yaoyao are developing together, you need to find one of Lu Yuhang or Yifei to be the reviewer


## The complete Pull Request process
Let's take adding this document to the main warehouse as an example to show a complete Pull Request process.

### 1. Create a new local branch
We usually want the master branch of our local warehouse to be synchronized with the master branch of the main warehouse as much as possible. Therefore, we should not directly develop on the master branch. If you are going to develop a feature, please create a new branch and develop on that branch.

Create a new branch and switch to it
```
git checkout -b doc-how-to-use-pr
```
Note that this command needs to be used in the local directory of the project. The choice of the branch name should keep a certain meaning as much as possible. For example, if the branch is added with a function, you can use `dev-xxx` as the branch name, if it is a document update, you can use `doc-xxx` as the branch name.

### 2. Write content and commit to the local warehouse
In this branch, the content I want to add is this document. I can open the project in vscode or other IDEs and add this file for related editing.
After editing and saving, if you type the following command
```
git status
```
You will find the following prompt
```
On branch doc-how-to-use-pr
Untracked files:
  (use "git add <file>..." to include in what will be committed)
        How_To_Cooperate_with_Pull_Request.md

nothing added to commit but untracked files present (use "git add" to track)
```
At this time, the file name is red, which means that although the file has been modified, it has not been added to the temporary storage area and cannot be tracked by git
Therefore, we need to type the following command
```
git add.
```
This command represents adding all untracked files in the current directory to the temporary storage area. Of course, you can also specify a file, for example
```
git add How_To_Cooperate_with_Pull_Request.md
```
Execute git status again, and you will find that the file name has turned green, indicating that this is a new file, and the modification should be committed in the next step
```
On branch doc-how-to-use-pr
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file: How_To_Cooperate_with_Pull_Request.md
```
Therefore, in the next step we commit the file to the local warehouse
```
git commit -m'add doc about how to create PR'
```
The `-m` parameter is followed by the comment of this Pull Request, this part should try to show the function completed by this commit. If you are committing locally for the first time, you may need to set up a git user and email, follow the prompts to set up and commit again.

Next, push the branch to your Fork warehouse. If you create a new branch locally and the remote warehouse does not have this branch at this time, the submission may fail. For example using
```
git push
```
Will prompt
```
fatal: The current branch doc-how-to-use-pr has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin doc-how-to-use-pr
```
Modify the corresponding command according to the prompt
```
git push --set-upstream origin doc-how-to-use-pr
```
This command will create a branch with the same name as the local branch in the remote warehouse of your Fork, and push the local branch to this branch. Note that when pushing, try to specify the corresponding host name and branch name. For example, if you want to modify the branch later, use
```
git push origin doc-how-to-use-pr
```
Instead of
```
git push
```
Where origin represents the host name (ie, remote warehouse), and `doc-how-to-use-pr` is the branch name of the remote warehouse. Although the effects of the two are the same, try to specify the host name and branch name when pushing to avoid possible problems.

### 3. Initiate a Pull Request
After pushing your own changes to the remote warehouse, you can initiate a Pull Request to the main warehouse

First enter your Fork warehouse, the branch in the upper left corner selects the branch you just modified through push, and then click the Pull Request button on the right to initiate a Pull Request to the main warehouse. Note that you must specify the branch of your own warehouse and the corresponding branch of the main warehouse. The title and description of the pull request should reflect the work or modification made by the PR, and try to provide more information to help the reviewer make a judgment.

### 4. Code Review
After the PR is proposed, a reviewer should be designated to review the code of this part of the modification to ensure that there is no major problem with the code that is finally merged to the main warehouse. The reviewer can annotate these changes if there are no problems after the code review is completed, otherwise the author should be required to modify the code. After the specified number of reviewers have approved the modification, this PR can be merged into the main warehouse. If the author finds that there is a problem in the code after submitting the PR, he can also change the status of the PR to Draft, and reset it to the status of Ready for review after the modification is completed.

### 5. Merge
The project manager should be responsible for the merge of the PR into the main warehouse after the Code Review is passed

## Synchronization of local warehouse/own remote warehouse and main warehouse
Due to multi-person collaborative development, after others modify the main warehouse, it may cause the local warehouse and the Fork warehouse under the account to lag behind the main warehouse in progress. Therefore, the main warehouse needs to be synchronized regularly.

First set up upstream for the local warehouse
```
git remote add upstream https://github.com/Iroski/SE-group-90.git
```
This command adds another remote warehouse named upstream tracked besides origin to the local warehouse, pointing to the main warehouse.

Then pull the code from the main warehouse and merge it into the local warehouse. In most cases, we only need to ensure that the master branch is synchronized with the main warehouse, so execute the following command
```
git checkout master # switch to master
git fetch upstream master # Pull the code of the master branch of the main warehouse
git merge upstream/master # Merge the master branch of the pulled main warehouse to the master branch of the local warehouse
git push origin master # Push the merged master branch to your Fork warehouse for synchronization
```
## Pull request request to merge
After we submit the pr, if the reviewer issues a modification suggestion that needs to be modified, it should be modified in the local file and submitted again, but there is no need to resubmit the pr. The specific method is as follows:
First pull the remote code from the directory and merge it:

```
git pull
```
If you need to merge later, proceed
```
git merge
```
(Here I forgot whether the merge command is needed, maybe it is not needed)
Re-add the modified file after the code is modified
```
git add.
```
Enter the following command:
```
git commit --amend --no-edit
```
After finishing
```
git push
```
Yes, github will automatically merge the files into the previous pr
## Reference
For more reference materials, please refer to [here](https://git-scm.com/book/zh/v2/)