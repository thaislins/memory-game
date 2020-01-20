# Memory Game

🃏:iphone: A memory game made for Android using the Shopify Store Products API

<p align="center"> <img width="30%" src="https://github.com/thaislins/memory-game/blob/app_images/images/memorygame.gif"> </p>

## Description

  A card game in which all of the cards are mixed up, laid face dows and are flipped face up over each turn. The goal of this game is to turn over pairs of matching cards. After all cards are matched the game will be over and the user can choose to play again or quit the game. This application was made with an MVVM Architecture, using Kotlin coroutines to allow API Requests to [Shopify Store Products API](https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6) to get product Images and display them on the game cards.


### Features
* Made use of Navigation Architecture Component to make a Single Activity App
* Repository and datasource were used as part of the model in the MVVM architechture,
as a way to keep the app modularized and easier to maintain
* Used Room to save product data and game scores in database
* Used Material Design Components
* Kotlin Coroutines were used to make API requests and to access app database
* Made use of Viewmodel, Livedata and DataBinding to make interaction with view easier and retain data
* Used a chronometer to keep track of how much time went by in the game
* Added of a few game settings to allow the player to define the amount of sets in the game
and the amount of equal cards that have to be matched
* Added a list of scores according to player name
* Used Custom Views as a way to make the cards and the grid view adaptable

### Built With

* [Kotlin](http://kotlinlang.org/) - Programming Language
* [Koin](https://insert-koin.io) - Dependency Injection Framework
  * Used to help decouple code and to reduce boilerplate code by injecting 
  repositories, viewmodel and retrofit interfaces
* [Glide](https://bumptech.github.io/glide/) - Image Loading Framework
  * This library was used to load the images from the src link received from the
  shopify products API
* [Retrofit](https://square.github.io/retrofit/) - REST Client Library
  * Retrofit was used as a way to make the GET Request to the API used quicker
  and more efficient
* [Jackson](https://github.com/FasterXML/jackson-core) - JSON Parser
  * This parser was used to parse the shopify products's JSON into object
  
### Screenshots

| No Matches | Some Matches | Win Game |
| ---------- | ------------ | -------- |
| <p align="center"> <img src="https://github.com/thaislins/memory-game/blob/app_images/images/start.png" width="80%"> </p> |<p align="center"> <img src="https://github.com/thaislins/memory-game/blob/app_images/images/matches.png" width="80%"> </p> |<p align="center"> <img src="https://github.com/thaislins/memory-game/blob/app_images/images/end.png" width="80%"> </p> |

## Author

[![Thais Lins](https://avatars.githubusercontent.com/thaislins?s=100)<br /><sub>Thais Lins</sub>](https://github.com/thaislins) 
