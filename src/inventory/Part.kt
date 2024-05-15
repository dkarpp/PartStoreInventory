package inventory

/************************************************************
 *  Name:         Dennis Karptsov
 *  Date:         5/21/2023
 *  Assignment:   Part Store Inventory
 *  Class Number: 283
 *  Description:  Write a program that implements the basics of a part store inventory system.
 ***********************************************************/
open class Part(var name: String, var retailPrice: Double, var cost: Double, var quantityInStock: Int, var quantitySold: Int, var detailDescription: String, var partCategory: String) {
    //name retail price, csot, qunatity in stock, sold, description

    override fun toString(): String {
        return """$name, $retailPrice, $cost, $quantityInStock, $quantitySold, $detailDescription, $partCategory""".trimIndent()
    }
    open fun toTab(): String{
        return "$partCategory\t$name\t$retailPrice\t$cost\t$quantityInStock\t$quantitySold\t$detailDescription";
    }
    open fun updatePart(){
  //      print("Please choose an item to update: ");
     //   var partID = readLine()!!.toInt();

        print("Please enter new part name: ");
        name = readLine()!!.toString();

        print("Please enter new Retail Price: ");
        retailPrice = readLine()!!.toDouble();

        print("Please enter new Cost: ");
        cost = readLine()!!.toDouble();

        print("Please enter new QTY In Stock: ");
        quantityInStock = readLine()!!.toInt();

        print("Please enter new QTY Sold: ");
        quantitySold = readLine()!!.toInt();

        print("Please enter new Detailed Description: ");
        detailDescription = readLine()!!.toString();



       /* print("Please enter new Screen Size: ");
        var screenSize = readLine()!!.toString();

        print("Please enter new MB's of RAM: ");
        var mbRAM = readLine()!!.toInt();

        print("Does this have an SD Card Slot? (true/false): ");
        var sdSlot = readLine()!!.toBoolean();*/

        /*var rowCount = 1;
        for(part in parts)
        {
            if(rowCount === partID)
            {
                //same row now
                part.name = partName;
                part.retailPrice = retailPrice;
                part.cost = cost;
                part.quantityInStock = quantityInStock;
                part.quantitySold = quantitySold;
                part.detailDescription = detailedDescription;

                //last 3

            }
            rowCount++;
        }*/

    }
}