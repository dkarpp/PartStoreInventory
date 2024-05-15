package inventory

/************************************************************
 *  Name:         Dennis Karptsov
 *  Date:         5/21/2023
 *  Assignment:   Part Store Inventory
 *  Class Number: 283
 *  Description:  Write a program that implements the basics of a part store inventory system.
 ***********************************************************/
const val inventoryDBFileName = "src/inventory/parts_database.tsv";
private val inventory = Inventory();

fun main(){
inventory.loadData(inventoryDBFileName);

    val menuItems = arrayOf(
        "List All Parts",
        "Show All of a particular Category",
        "Sell a Part",
        "Increase inventory of a particular part",
        "Update elements of a single part",
        "Add a brand new part to inventory",
        "Completely remove a part from inventory",
        "Show the detail information about a part",
        "Show Total Inventory with totals of Cost, Retail Price Quantity Sold, Profit\n",
        "Quit"
    )

    val quitOption = menuItems.size;
    var userSelection = 0;

    while(userSelection != quitOption) {
        userSelection = menu(menuItems, "Please enter your selection: ");

        when (userSelection) {

            1 -> inventory.readParts();
            2 -> {
                print("Please choose a category of parts to print: ");
                var part = readLine()!!.uppercase();
                inventory.listCategoryPart(part);
            }
            3 -> {
                inventory.listAllParts(true);
                print("Please choose an item to sell: ");
                var part = readLine()!!.toInt();

                if(inventory.checkSell(part)) {
                    inventory.sellPart(part);
                }
                else{
                    print("Selected item is out of stock. Please choose a different one!\n");
                }
            }
            4 -> {
                print("Please choose an item to update: ");
                var part = readLine()!!.toInt();
                print("Please enter how many items to add to inventory: ");
                var quantity = readLine()!!.toInt();

                inventory.updateQuantity(part, quantity);

            };
            5 -> {
                print("Please choose an item to update: ");
                var part = readLine()!!.toInt() - 1;

                inventory.updateItem(part);
            }
            6 -> inventory.addNewPart();
            7 -> inventory.removePart();
            8 -> inventory.showDetailedInfo();
            9 -> inventory.showTotalInventory();
            10 -> inventory.saveProgram(inventoryDBFileName);
            else -> println("Please select a valid number and try again." + "\n");
        }
    }
}
fun menu(items : Array<String>, prompt : String) : Int {
    for ((index, item) in items.withIndex()) {
        println("${index + 1}. ${item}");
    }
    print(prompt);
    return readLine()!!.toInt();
}