# Requirements:
Create an API card module for displaying 
1. an image url
2. an item description

## Component
1. Image: displays the image of the item
2. Description: displays the text description of the item
3. Button: submits an action
4. Option menu: selects an option from an item list 

## View
1. item: displays the item as a card

## API Schema
1. getItems() :: item
2. addAnItem(Item item) :: reqBody :: item
3. deleteAnItem(int theItemId) :: int :: int
4. updateAnItem(Item item) :: reqBody :: item
