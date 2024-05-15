package inventory

/************************************************************
 *  Name:         Dennis Karptsov
 *  Date:         5/21/2023
 *  Assignment:   Part Store Inventory
 *  Class Number: 283
 *  Description:  Write a program that implements the basics of a part store inventory system.
 ***********************************************************/
class Tablet(name: String, retailPrice: Double, cost: Double, quantityInStock: Int, quantitySold: Int,
             detailDescription: String, var screenSize: String, var ramSize: Int, var sdSlots: Boolean, partCategory: String) : Part(name, retailPrice, cost, quantityInStock, quantitySold, detailDescription, partCategory) {

    override fun toString(): String {
        return super.toString() + "$screenSize, $ramSize, $sdSlots";
    }

    override fun updatePart(): Unit{
        super.updatePart()

        print("Please enter new Screen Size: ");
        screenSize = readLine()!!.toString();

        print("Please enter new MB's of RAM: ");
        ramSize = readLine()!!.toInt();

        print("Does this have an SD Card Slot? (true/false): ");
        sdSlots = readLine()!!.toBoolean();

    }
    override fun toTab(): String {
        return super.toTab() + "\t$screenSize\t$ramSize\t$sdSlots\n"
    }
}