the commit message convention is project key - issue id [issue description]

- story
    - `DEV-1 view register page`

- technical task
    - `TASK-1 add logback support`

- bug
    - `BUG-1 fix registration flow`

- no issue
    - `code review - update docs`
    - `code review - update docs`

# create local repository

- create repository, create .git hidden folder

      git init

- add files to staging area

      git add *

- save the state of files

      git commit -m "initial commit"

# push to a remote repository

- link local repo with remote repo

      git remote add origin https://github.com/username/new_repo

- push changes to remote repo

      git push -u origin master

# get remote changes

- brings all branches with changes

      git fetch

- downloads changes

      git pull
