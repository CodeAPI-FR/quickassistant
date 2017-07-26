Netbeans QuickAssistant
===================

This plugin provide quick open of projects or quick create file/folder


How To Setup
-------------------

- Go to Settings > Miscellaneous > QuickAssistant
- Add a new prefix
- Fill the form
	- Open project :
		- Argument : Path of the folder who contains projects
	- Create file/folder :
		- Argument : Path of the template
		- Parent : Name of the folder who contains the file by default

#### Example 1

	Name : Open local project
	Type : Open project
	Prefix : o
	Argument : /Users/CodeAPI/Projects/

#### Example 2

	Name : Create json file
	Type : Create file
	Prefix : json
	Argument : Templates/ClientSide/json.json
	Parent : Empty


How To Use
-------------------

Press the command

** MacOS **

	Command + Shift + L

** Windows **

	Ctrl + Shift + L

Write your quick command and press enter

#### Example 1

	o:siteweb

#### Example 2

	json:composer


Contributing / Get Support!
-------------------
This code need optimization so : Got issues? Have an idea ? Please tell us!
* [GitHub Issues](https://github.com/cakephp/cakephp/issues)
