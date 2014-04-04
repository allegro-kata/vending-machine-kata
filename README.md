------------
# 4Developers Spock Workshop - 07.04.2014

### Who we are:

+ Bartosz Walacik - Grupa Allegro
+ Pawel Szymczyk - Grupa Allegro

### Agenda
<pre>
Slot              What
18:00 -  18:30    Spock Intro:
                  -  BddStyleSpockTest
                  -  AssertionsSpockTest
                  -  DataDrivenSpockTest

                  .. CustomAssertionSpockTest

18:30 -  19:00    Story 1. Accept Coins, write tests in AcceptCoinsTest

19:00 -  19:30    Story 2. Accept Coins - corner case, write src and tests

19:30 -  20:00    Code Review for teams 1..10
</pre>

------------
# vending-machine SPOCK kata

1. clone vending-machine project
   <p><code>git clone https://spocky-all@github.com/spocky-all/vending-machine-kata.git
   </code></p>
1. Checkout proper branch (team01..10)
1. If you use IntelliJ, import Gradle project and run tests normally
1. Or start playing with tests from command-line:
    <p> <code>gradle test</code></p>
    <p> <code>gradle cleanTest test</code> - to run again when nothing changed (gradle works incrementally) </p>
1. Kata - implement 4 user stories

------------
# User stories

Based on
http://github.com/guyroyse/vending-machine-kata

------------
### 1. Accept Coins

_As a vendor_
_I want a vending machine that accepts coins_
_So that I can collect money from the customer_

The vending machine will accept valid coins (nickels, dimes, and quarters) and reject invalid one (pennies).When a
valid coin is inserted the amount of the coin will be added to the current amount and the display will be
updated (use pattern: CREDIT 9.99).
When there are no coins inserted, the machine displays INSERT COIN. Rejected coins are placed in the coin return.

------------
### 2. Accept Coins - corner case

_As a vendor_
_I want a vending machine to reject a coin_
_when internal Coin Cassette is full_

The vending machine has internal Coin Cassette with
dedicated tubes for each coin type. When machine is not serviced properly, tubes can be full.
In such case, when client inserts a valid coin, it should be rejected
and machine should display CASSETTE IS FULL, SORRY.

If the display is
checked again, it will show INSERT COINS or CREDIT, depending on current state.

--------------
### 3. Select Product

_As a vendor_
_I want customers to select products_
_So that I can give them an incentive to put money in the machine_

There are three products: cola for $1.00, chips for $0.50, and candy for $0.65.  When the respective button is pressed
and enough money has been inserted, the product is dispensed and the machine displays THANK YOU.

If the display is
checked again, it will show INSERT COINS and the current amount will be set to $0.00.  If there is not enough money
inserted then the machine displays PRICE and the price of the item and subsequent checks of the display will display
either INSERT COINS or the current amount as appropriate.

------------
# See
1. http://docs.spockframework.org
1. http://groovy.codehaus.org/Groovy+style+and+language+feature+guidelines+for+Java+developers