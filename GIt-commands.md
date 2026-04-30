Git - April 30, 2026

Three stages of a file
- Commited  - saved locally 
- Modified - altered from the last commited version 
- Staged  - Marked for commit 

Three states of a git project
- Working Directory - works locally
- Staging Area (Index) - To be prepped to be staged or stashed 
- .git directory (Repository) - VCS repository

Installing Git and Configuration 

- Command Line 
pwd - print working directory 
cd - change working direcory (cd .. or cd ~)
ls  or dir (windows)- list files 
touch or copy con (windows)- create file 
mkdir - create new empty folder 

- Installing git and basic commands

sudo apt-get install git - debian 
https://git-scm.com/download/mac or windows 
brew install git 


git --version 

git config --global global user.name ""
git config --global user.email "" 


git config --list (list down configurations)

git config user.name
git config user.email

man git (git manual)
git help

git init (initialize git repo)



- Git Code Hosting Providers
Github
Gitlab
Bitbucket



- Basic Commands - Porcelain Commands 
git add . 

git commit -m ""


git remote add origin https://.../...git

git push -u origin master

git status (provides the status)
git status -s (provides the status but in a short way)
M - Modified
A - Added to staging area
?? - new file untracked by Git 


git reset HEAD <file> (to unstage)

git add . (stage all files)
git add <file>

git diff

git diff --staged

git diff --staged --no-renames

git commit -a -m "message" (automatically stages all files in the working directory -a)

git push origin master (push upstream)

git log (see commit history)

git log -1 (limit only commit number)

git log --oneline (displays the list of commits in a oneline message)

git log --stat (displays the files changed )

git log --patch (shows full diff)


git rm <file> (remove file and untrack it)
git rm --cached <file> (untrack it but does not remove the file from the project)

git mv <file> <name> (rename file)

git branch 
git checkout -b <name> (move to and create a new branch)
git checkout <name> (move branches)

git stash (saved working directory)
git stash list (list git stashed changes)
git stash show (show changes of stashed files)
git pop (fetch stashed changes)


git rebase 
git merge branch <branch-name> (fetch changes from another branch)

git reset --hard <hash> (reverts and deletes files)
git reset --soft <hash>  (move back to staging area)
git reset <hash>  (moves back to the working directory but not stashed)


git pull origin main 
