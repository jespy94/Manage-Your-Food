# Android App “Manage Your Food”: Detailed Guide
## About Manage Your Food:
“Manage Your Food” is an Android application with the implied goal of reducing food waste by managing a user’s food.
“Manage Your Food” has three main functions: the inventory function, the grocery list function, and the meal suggestion function. 
The inventory function allows users to create inventories, such as fridges or pantries, and add their respective food items.
For example, a user could create a fridge inventory and add eggs, bacon, and milk. 
The expiration date is recorded for each of these food items so that the user can be alerted to an item that is close to its expiration date.
The grocery list function is pretty straightforward since it allows users to create grocery lists. 
Users can add food items along with their quantity to the lists. 
The grocery list function indirectly works alongside the inventory function. 
The inventory function shows users what items they currently have, which can help users formulate more effective grocery lists.
For example, if a user was going to add tomatoes to their grocery list, but they checked their fridge inventory and saw they already have tomatoes, then that could influence their grocery list.
The meal suggestion function allows users to search for recipes based on they input into the search query.
Users can search through their existing food items to add to the query or they can manually input food items. 
The search query is a web search that searches for recipes with the selected items.
## Setup:
To download the application, download the APK file [here](app/release/app-release.apk).
Press on the file and allow it to be installed on your device.
You may need to allow the application where the file is at to allow the installation of unknown files.
After installing, it should be available for use on your device.
## How To:
### Main Screen:
	Users start at the main screen where the logo is placed. 
	Navigation buttons are available at the bottom of the screen in the following order: Inventory, Meal Suggestion, Home, Grocery List. 
	Pressing any of these buttons brings a user to its respective screen.
	It is important to note that the Home button brings users back to the main screen. The navigation is consistent throughout the app.
	If the Check Expired Items button is pressed, it will send a notification of items that have expired or are expiring within 7 days.
	If there are no expired or expiring items, then the notification will say there is no expiring items.
### Inventory Section:
	In the inventory screen, there is a large section in the middle that holds all of a user’s created inventories.
	To add an inventory, press the Add New Inventory button and a dialogue will pop up. 
	Enter the desired name, press Add, and it will be added to the main inventory screen.
	If the user does not want to finish creating the inventory, they can press Cancel and nothing will be added to the main inventory screen.
	If a user created an inventory, but they wish to delete it, they can press the trash can icon next to the inventory’s name on the main inventory screen.
	A dialogue will pop up to confirm the deletion. If the user presses Delete, then the inventory will be deleted. If the user presses Cancel, then the inventory will not be deleted.
	A user can press on a particular inventory’s name and they will be taken to the inventory’s detail page. 
	The detail page will have the inventory’s name, their food items, and an Add button. 
	To add a food item to the inventory, the user can press on the Add button and a dialogue will pop up. 
	The user can enter the food item’s name, purchase date, and expiration date. The dates should be in MM/dd/YYYY format. 
	If the user wants to finish adding the item to the inventory, they can press Add, but if they do not want to add the item, then they can press Cancel to stop the process.
	If the user has created an item but they wish to delete it, then they can press the trash can icon next to the food item’s purchase and expiry date. 
	A dialogue will pop up to confirm the deletion; if the user presses Delete, then the item will be deleted, but if the user presses Cancel, then the item will not be deleted.
### Grocery List Section:
	Like in the inventory section, the main screen for the grocery list section displays a large section in the middle for existing grocery lists. 
	To add a grocery list, press Add List and a dialogue will pop up. 
	Add the desired name and either press Add to add the list to the main screen, or press Cancel to not add anything to the main screen.
	Like with the inventory section, if the user wants to delete a list, they can press the trash can icon next to the list’s name.
	A confirmation dialogue will pop up and Delete is pressed, the list will be deleted; if Cancel is pressed, then the list will not be deleted.
	
	A user can press an individual grocery list to be taken to its details page. 
	A grocery list’s details page will display its name and a section where all the grocery list items will be displayed. 
	To add an item to the grocery list, press Add and a dialogue will pop up. 
	Enter the desired item name and quantity and either press Add to add the item to the list or Cancel to stop the process. 
	If a user wants to delete the item, they can press the trash can icon and a dialogue will pop up to confirm the deletion. 
	Press Delete to delete the item or press Cancel to stop the deletion.
### Meal Suggestion Section:
	In the meal suggestion section, the large space between the buttons will hold the items that will go toward the search query. 
	To manually add an item to the query, press Manual Add and a dialogue will pop up. 
	Enter in the desired item name and either press Add to add it to the query or Cancel to stop the process.
	To add an item from an existing inventory, press From Inventory and a dialogue will pop up with all of the existing food items within the user’s inventories. 
	Each item has a checkbox next to its name and when pressing on the item, it will make the checkbox checked or unchecked depending on its previous state.
	When Add is pressed, all items with checked checkboxes will be added; when Cancel is pressed, nothing is added.
	When the desired items are added to the query space, press Search to do a web search for recipes including the items. 
## Additional Info
## FAQs
### Q:
When I delete either a grocery list or an inventory and then create a new grocery list or inventory with the same name, it retains all of the existing items. How do I get this to not happen?
### A: 
Due to how the app is programmed, this is an unintended occurrence. The only way to fix this problem right now is to delete all the items in the grocery list or inventory before deleting the actual grocery list or inventory.

### Q:
Am I able to edit the names of lists or food items?
### A:
In this version of the application, it is not possible to edit the names of lists or food items. The next version of the app will take this into consideration.

## Contact
##### Phone Number: 770-876-7058
##### Primary E-mail: espyboys@gmail.com
##### School E-mail: je02696@georgiasouthern.edu 

