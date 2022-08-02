# acceptance test

```
create user

create project

    create sprint
    
        create task 1
            set status DONE
        create task 2
            set status TODO
        add tasks to sprint (1, 2)

create task 3

show board
assert status TODO = list of task 2
assert status DONE = list of task 1

show backlog
assert user created
assert project created
assert sprint created
assert 3 tasks
assert sprint has 2 tasks
assert project has task 3
```
