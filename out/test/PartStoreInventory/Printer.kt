package inventory

/************************************************************
 *  Name:         Dennis Karptsov
 *  Date:         5/21/2023
 *  Assignment:   Part Store Inventory
 *  Class Number: 283
 *  Description:  Write a program that implements the basics of a part store inventory system.
 ***********************************************************/
class Printer(name: String, retailPrice: Double, cost: Double, quantityInStock: Int, quantitySold: Int,
              detailDescription: String, var colorSupport: Boolean, var pagesPerMinute: Int, var scanner: Boolean, partCategory: String) : Part(name, retailPrice, cost, quantityInStock, quantitySold, detailDescription, partCategory) {

    override fun toString(): String {
        return super.toString() + "$colorSupport $pagesPerMinute $scanner"
    }

    override fun updatePart(): Unit{
        super.updatePart()

        print("Does this have color support? (true/false): ");
        colorSupport = readLine()!!.toBoolean();

        print("Please enter new Pages Per Minute: ");
        pagesPerMinute = readLine()!!.toInt();

        print("Does this have a scanner? (true/false): ");
        scanner = readLine()!!.toBoolean();

    }
    override fun toTab(): String {
        return super.toTab() + "\t$colorSupport\t$pagesPerMinute\t$scanner\n"
    }
}