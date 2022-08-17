# Requirements:
Create an API card module for displaying 
1. an image
2. an item description
3. an add button 
4. an options menu 

## Component
1. Image: displays the image of the item
2. Description: displays the text description of the item
3. Button: submits an action
4. Option menu: selects an option from an item list 

## View
1. itemCard: displays the item card

## API Schema
1. getTheItemCard() :: ItemCard
2. addTheItemCard(itemCard theItemCard) :: reqBody :: int
3. delTheItemCard(int theItemId) :: int :: int
4. updateTheItemCard(itemCard theItemCard) :: reqBody :: int
