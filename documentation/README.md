# Documentation

## Necessary Features
### User-Management
#### Register Functionality
* UI: A register form with an email-field and two password fields that have to match each other. 
A button to finish the process. A redirect to the login page.
*(E-Mail Activation/Notification not really necessary...)*
* Logic: Click listener on the register button. Once clicked, validate the input (valid email? do passwords match?) and then call the UserService::register() function and pass the given email and password.
* Backend: The passed email and password will be saved to the MySQL database using the JPA. _(Do some research here, not clear yet)_
* After that: redirect to the login page or display an error if invalid data is entered.

#### Login Functionality
* UI: final TextField email = new TextField(), final PasswordField password = new PasswordField(), final Button loginButton, final Button registerButton
* Logic: ClickListener on registerButton to redirect user without an account to the register page.
ClickListener on the loginButton -> once clicked, the UserService::login(email, password) method is called and the login credentials are passed.
* Backend: The UserService::login(email, passwordHASH) selects the user with the given email from the Database/JPA and compares their password (hash). In either case, we have to somehow inform the MyUI class, whether it succeeded or failed so the appropriate action can be taken (take user to the restricted area or show him an "invalid credentials" error)

### Session Handling
* After the user logged in, his session should represent that state. Sessions are automatically created by Vaadin when a user hits the init() method, we just have to enhance it a bit.
* We have to somehow secure the "restricted" areas of our webApp, so that one can't simply enter the *correct* URL and see all the DocBook Stuff....
@TODO: Think about an authorization mechanism.

## UI Classes
### Login Panel
class myLogin extends UI ...
filled with Login Functionality described in 1-1-1-2
### Register Panel
class myRegister extends UI ...
filled with Register Functionality described in 1-1-1-1
### Overview Panel (Lists all Documents)
class myOverview extends UI ...
Has to provide the following UI elements:
A TableView with search functionality that lists a document instance's MetaData (author, ...)
In addition, this table view shall be searchable by either the document's or the author's name.
### Detailed View Panel (Lists all available information about one Document)
class myDetails extends UI ...
This view shall list all detailed information about a single document instance.
This includes all MetaData about the Document and the author and several interaction mechanisms such as DELETE, DOWNLOAD (and CONVERT)

## Services
### UserService
* UserService::getInstance(); - returns a singleton instance of this service
* UserService::register(email, password); - saves the user to JPA
* UserService::login(email, password); - checks if user exists with given credentials

### DocService
* DocService::getInstance(); - returns a singleton instance of this service
* DocService::parse(document); - returns a parsed document instance as return value
* DocService::getMetaData(parsedDocument); - Extracts the Meta Information out of the Docbook5 document instance (and stores them? or build extra method for this?)
* DocService::convert(file); - converts a DocBook5 document instance to a PDF file using the XSL-FO engine.

### StorageService
* StorageService::getInstance();
* StorageService::save(file); - save an uploaded file to the database
* StorageService::delete(file); - delete a saved file from the database

Comment: Passwords should be stored as some kind of hash, not in their clear-text format. Simply calculate the hash (p.e. sha256) for the entered password and store that hash to the DB. When logging in, calculate the hash again and just compare it to the hash saved to the DB. So no cleartext passwords are exposed.

