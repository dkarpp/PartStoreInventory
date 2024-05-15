package inventory

/************************************************************
 *  Name:         Dennis Karptsov
 *  Date:         5/21/2023
 *  Assignment:   Part Store Inventory
 *  Class Number: 283
 *  Description:  Write a program that implements the basics of a part store inventory system.
 ***********************************************************/
class Computer(name: String, retailPrice: Double, cost: Double, quantityInStock: Int, quantitySold: Int,
               detailDescription: String, var ramSize: Int, var hardDriveCapacity: Int, var processorSpeed: String, partCategory: String) : Part(name, retailPrice, cost, quantityInStock, quantitySold, detailDescription, partCategory) {

    override fun toString(): String {
        return super.toString() + "$ramSize, $hardDriveCapacity, $processorSpeed";
    }

    override fun updatePart(){
        super.updatePart()

        print("Please enter new GB's of RAM: ");
        ramSize = readLine()!!.toInt();

        print("Please enter new GB's of Hard Drive: ");
        hardDriveCapacity = readLine()!!.toInt();

        print("Please enter new Processor Speed: ");
        processorSpeed = readLine()!!;

    }

    override fun toTab(): String {
        return super.toTab() + "\t$ramSize\t$hardDriveCapacity\t$processorSpeed\n"
    }
}