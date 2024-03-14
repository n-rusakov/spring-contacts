## Spring Contacts
#### Practical work on Spring, console contacts manager
***
#### Available commands:
+ **LIST** - output all contacts in format "Name|Phone|Email"
+ **ADD contact** - add new contact. Format: "Name;Phone;Email"
+ **DELETE email** - delete all contacts with the specified email
+ **SAVE filename** - save all contacts to file in format "Name;Phone;Email"

#### Available configuration:
+ **spring.profiles.active** - current profile
+ **app.contacts.init-file** - load contacts from specified file at start, if profile = init
+ **app.contacts.save-folder** - folder to files, used by **SAVE** command