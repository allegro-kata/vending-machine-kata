# User stories

Based on
http://github.com/guyroyse/vending-machine-kata

...

--------------
### 3. Select Product

_As a vendor_
_I want customers to select products_
_So that I can give them an incentive to put money in the machine_

There are three products: cola for $1.00, chips for $0.50, and candy for $0.65.  When the respective button is pressed
and enough money has been inserted, the product is dispensed and the machine displays THANK YOU.  If the display is
checked again, it will display INSERT COINS and the current amount will be set to $0.00.  If there is not enough money
inserted then the machine displays PRICE and the price of the item and subsequent checks of the display will display
either INSERT COINS or the current amount as appropriate.

-----------
### 4. Make Change

_As a vendor_
_I want customers to receive correct change_
_So that they will use the vending machine again_

When a product is selected that costs less than the amount of money in the machine, then the remaining amount is placed
in the coin return.

-----------
### 5. Storage & eMails
_As a venodr_
_I want to be notified via email when machine runs out of product_

Vending machine has one shelve per product, each with 10 items capacity. Full machine contains 10x cola, 10x chips and 10x candy.

Each time when user selects sold out product, machine should do two things:

1. display NO ${productName}, SORRY
1. send email to vendor with supply request

# All day Spock Workshop Schedule
<pre>
Slot            Co                          Prowadzacy
9:00 -  9:30    Spock Intro
9:40 -  11:00   Story 1 accept coins
11:10 - 12:30   Story 2 select product
12:30 - 13:30   Obiadek, placi GA
13:30 - 14:50   Story 3 make change
15:00 - 16:30   Story 4 storage and emails
</pre>