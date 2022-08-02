# feature

    as a user 
    I want to register into the application
    so I can track my tasks

# info

    summary: DEV-1 view register page
    estimation: 3
    reporter: trainer
    assignee: student

# design

- see attachment

# acceptance criteria

- user click `Register` and navigates to register form
- user inputs data
    - email input (email format *)
    - password input
    - first name input
    - last name input
- user clicks the `Create account` button, and the user should be saved in db
- input validation *

# frontend

- form template
    - email input
    - password input
    - first name input
    - last name input
    - gdpr checkbox *
    - submit button "Register"

# backend

- entity
- repository
- service
- controller
- unit test
- integration test

# test

- go to landing page
- click `Register`
- input data
- click `Create account`
