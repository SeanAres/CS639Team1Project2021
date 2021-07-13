
# CS639Team1Project2021
CS639 Team 1's Project 2021

1. We want to create a way for people to shop faster and healthier. Usually caloric counters are in health apps where you have to input what you eat. Instead we want to make a food shopping app where this feature is integrated; This way when you are getting ingredients or food at the store you can make sure your meals are tailored for your diet.
2. A typical persona that our solution is targeting is as follows: Fred is very health conscious and likes to watch what he eats. He works long hours and would like an easy way to keep track of what food items he is getting low on so that he can make a shopping list for the week. Fred is intimated by many fitness apps that include calorie counters and just wants a easy way to find the nutritional value of his weekly groceries so that he can be more informed about what food he is buying each week for groceries. 
3. Major features include: Caloric counter based on food data from API, shopping list with calorie counter built in, and diet options based on your weight goals.
4. Many calorie counters already exist on the google play store. However, most of these solutions are attached to fitness apps and require setting weight loss goals and exercise regiments. For many people, these extras would be extraneous and not necessary. Our application will provide a simple way to find the nutritional content of your weekly groceries without having to set a weight loss goal. Some applications that will be similar to our solution include: "Calorie Counter- MyFitnessPal", "Calorie Counter by Lose It!", and "Cronometer - Nutrition Tracker."
   "Calorie Counter- MyFitnessPal" is a calorie counter/weight loss app associated with the fitness website www.myfitnesspal.com. Use of the application requires a membership to the website. The application tracks your fitness goals and recommends a certain caloric count to reach those goals. You can look up what food items you consume every day and this is added to your total calorie count. The app will also try to direct you to other resources under the MyFitnessPal platform.    
   "Calorie Counter by Lose It!" is a weight loss/calorie counter application under Loseit inc. Much like the previous application, use of the app requires registering under the website www.loseit.com. Like MyFitnessPal calorie counter, the app sets a caloric goal for each day to assist with meeting a weight loss goal. The app also tries to direct you to other resources under the "Lose it!" umbrella. Here are a couple screenshots of both calorie counters.  
   "Cronometer - Nutrition Tracker is a similar app to the two above, you must register to their site by using an email, and enter your current statistics such as height and weight, and your goals(whether to gain or lose weight). After these have been entered, the app makes a plan customized to your needs, detailing macros and kcal left for the day. You are able to look up food on a different tab, and the app automatically adds it to the main tab along with its basic nutritional information.
 ![](https://github.com/SeanAres/CS639Team1Project2021/blob/master/Screenshot_2021-06-28-00-57-29.png?raw=true) 
 ![](https://github.com/SeanAres/CS639Team1Project2021/blob/master/Screenshot_2021-06-28-01-29-34.png?raw=true)
 ![](https://github.com/SeanAres/CS639Team1Project2021/blob/master/Screenshot_20210629-220915.png?raw=true)

5. We will be using the ESHA Research Nutrition API portal. This API will give us information about each food item's nutritional information with a straightforward JSON format. Link: https://nutrition-api-dev.esha.com/  
   We will also be using the Firebase database to collect and store information for the user such that the user can view their app on multiple devices at their convenience.  
   Finally, we are planning to use a cache to store information locally on a phone such that we don't have to access the nutrition API too often.

Below is a design of our application mapping out the different activities:
![Design](https://user-images.githubusercontent.com/60992141/124345816-20223200-dba9-11eb-9761-20f6ce0e1c53.jpg)

Presentation slide link:
https://docs.google.com/presentation/d/1SU0KUjHsQJB_KTNCrygFi5-tVXl6FozmwFvhNdZnOaY/edit#slide=id.p


Screenshots of the Healthy Shopper app:
![HealthyShopper1](https://user-images.githubusercontent.com/60992141/125526224-085bf5dd-555e-4165-953e-ef74f7f22398.png)
![HealthyShopper2](https://user-images.githubusercontent.com/60992141/125526248-922dd40f-2075-4348-b1cf-954c63724632.png)
![HealthyShopper3](https://user-images.githubusercontent.com/60992141/125526254-13aa574c-af9e-4fc3-b345-3d1bc9899089.png)
![HealthyShopper4](https://user-images.githubusercontent.com/60992141/125526278-6215ffb0-627c-4b22-a191-e06e67f80ac3.png)
![HealthyShopper5](https://user-images.githubusercontent.com/60992141/125526297-d898822d-7168-4ff1-8239-1817405cbfbb.png)


