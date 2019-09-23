# Memory Game

♠️♦️ A memory game made for Android using the Shopify Store Products API

## Description

  A card game in which all of the cards are mixed up, laid face dows and are flipped face up over each turn. The goal of this game is to turn over pairs of matching cards. After all cards are matched the game will be over and the user can choose to play again or quit the game. This application was made in an MVVM Architecture, using Kotlin coroutines to make an API Request to [Shopify Store Products API](https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6) to get the product Images and show them on the game cards.


### Features
* Used Custom Views as a way to make the card grid view adaptable
* Made use of Navigation Architecture Component to make a Single Activity App
* Repository and datasource were used as part of the model in the MVVM architechture,
as a way to keep the app modularized and easier to maintain
* Use of Viewmodel and Livedata to make interaction with view easier and retain data
* Added a counter to show how many moves the player made
* Used a chronometer to keep track of how much time went by in the game


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
  * This parser was used to parse the shopify products JSON into objects

## Author

[![Thais Lins](https://avatars.githubusercontent.com/thaislins?s=100)<br /><sub>Thais Lins</sub>](https://github.com/thaislins) 
