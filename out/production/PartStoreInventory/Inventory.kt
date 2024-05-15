package inventory
import java.io.File
import java.io.FileWriter

/************************************************************
 *  Name:         Dennis Karptsov
 *  Date:         5/21/2023
 *  Assignment:   Part Store Inventory
 *  Class Number: 283
 *  Description:  Write a program that implements the basics of a part store inventory system.
 ***********************************************************/
class Inventory() {
    private val sizeList: MutableList<String> = mutableListOf();
    private val nameList: MutableList<String> = mutableListOf();
    private val parts: MutableList<Part> = mutableListOf();
    private val detailedListNames: MutableList<String> = mutableListOf(
        "Part Category",
        "Name",
        "Retail Price",
        "Cost",
        "Qty In Stock",
        "Qty Sold",
        "Profit Each",
        "Grand Total",
        "",
        ""
    );
    private val detailedListSize: MutableList<String> =
        mutableListOf("15", "25", "15", "15", "15", "10", "20", "20", "0", "0");

    fun listAllParts(isSell: Boolean) {

        if (isSell) //isSell means print with numbers
        {
            header(sizeList, nameList); //print saved header
            var count = 0;
            parts.forEach {
                count++;
                println(count.toString() + ". " + checkPrint(it, sizeList));
            }
        } else {
            header(sizeList, nameList); //print saved header.
            parts.forEach { println(checkPrint(it, sizeList)) }
        }

    }

    fun listCategoryPart(category: String) {

        header(sizeList, nameList);
        parts.forEach { part ->
            if (part.partCategory == category) {
                println(checkPrint(part, sizeList));
            }
        }
        println();
    }

    fun checkSell(partID: Int): Boolean {
        var rowCount = 1;
        for (part in parts) {
            if (rowCount === partID) {
                return part.quantityInStock > 0
            }
            rowCount++;
        }
        return true;
    }
    fun saveProgram(fileName: String) {
        val fileWriter = FileWriter(fileName)

        for(size in sizeList){
            fileWriter.write(size + "\t");
        }

        fileWriter.write("\n");
        for(name in nameList){
            fileWriter.write(name + "\t");
        }
        fileWriter.write("\n");
        for (part in parts) {
            fileWriter.write(part.toTab());
        }
        fileWriter.close()

        println("Saved!")
    }

    fun showDetailedInfo() {
        listAllParts(true);

        print("Please choose an item to view full details: ")
        var idChosen = readLine()!!.toInt()

        var rowCount = 1;
        for (part in parts) {
            if (idChosen == rowCount) {
                println(
                    """
                        
                    Part Category: ${part.partCategory}
                    Part Name: ${part.name}
                    Retail Price: ${part.retailPrice}
                    Cost: ${part.cost}
                    QTY In Stock: ${part.quantityInStock}
                    QTY Sold: ${part.quantitySold}
                    Detailed Description: ${part.detailDescription}            
                """.trimIndent()
                )

                when (part.partCategory) {
                    "COMPUTER" -> {
                        val computer = part as Computer
                        val ramSize = computer.ramSize.toString()
                        val hardDriveCapacity = computer.hardDriveCapacity.toString()
                        val processorSpeed = computer.processorSpeed
                        println(
                            """
                             |Ram Size: $ramSize 
                             |Hard Drive Capacity: $hardDriveCapacity 
                             |Processor Speed: $processorSpeed""".trimMargin() + "\n"
                        )
                    }
                    "PRINTER" -> {
                        val printer = part as Printer
                        val colorSupport = printer.colorSupport.toString()
                        val pagesPerMinute = printer.pagesPerMinute.toString()
                        val scanner = printer.scanner.toString()

                        println(
                            """
                            Color Support: $colorSupport 
                            Pages Per Minute: $pagesPerMinute 
                            Has Scanner: $scanner""".trimIndent() + "\n"
                        )
                    }
                    "TABLET" -> {
                        val tablet = part as Tablet
                        val screenSize = tablet.screenSize
                        val ramSize = tablet.ramSize.toString()
                        val sdSlot = tablet.sdSlots.toString()
                        println(
                            """
                            |Screen Size: $screenSize 
                            |Ram Size: $ramSize 
                            |Has SD Slot: $sdSlot""".trimMargin() + "\n"
                        )
                    }

                }

            }
            rowCount++;
        }
    }

    fun showTotalInventory() {
        header(detailedListSize, detailedListNames)

        var grandTotal = 0.0
        for (part in parts) {
            println(buildDetailedInfo(part));
            grandTotal += part.quantitySold * (part.retailPrice - part.cost)
        }
        println("--------------------".padStart(135, ' '))
        println("$$grandTotal".padStart(126, ' '))

    }

    private fun buildDetailedInfo(part: Part): String {
        val partCategory =
            checkLength(part.partCategory, detailedListSize[0].toInt() - 1).padEnd(detailedListSize[0].toInt() - 1, ' ')
        val name = checkLength(part.name, detailedListSize[1].toInt() - 1).padEnd(detailedListSize[1].toInt() - 1, ' ')
        val retailPrice = part.retailPrice.toString().padEnd(detailedListSize[2].toInt() - 1, ' ')
        val cost = part.cost.toString().padEnd(detailedListSize[3].toInt() - 1, ' ')
        val quantityInStock = part.quantityInStock.toString().padEnd(detailedListSize[4].toInt() - 1, ' ')
        val quantitySold = part.quantitySold.toString().padEnd(detailedListSize[5].toInt() - 1, ' ')
        val profitEach = (part.retailPrice - part.cost).toString().padEnd(detailedListSize[6].toInt() - 1, ' ')
        val grandTotal =
            (part.quantitySold * (part.retailPrice - part.cost)).toString().padEnd(detailedListSize[7].toInt() - 1, ' ')

        return "$partCategory $name $$retailPrice $$cost $quantityInStock $quantitySold $$profitEach $$grandTotal"
    }

    fun removePart() {
        listAllParts(true);
        print("Please choose an item to remove from inventory: ");
        var id = readLine()!!.toInt();

        if (id >= 1 && id <= parts.size) {
            parts.removeAt(id - 1)
        }
    }

    fun addNewPart() {
        print("What category of part would you like to add (COMPUTER, PRINTER, TABLET): ");
        var category = readLine()!!.toString().uppercase();
        print("Please enter new part name: ");
        var newPartName = readLine()!!.toString();
        print("Please enter new Retail Price: ");
        var newRetailPrice = readLine()!!.toString();
        print("Please enter new Cost: ");
        var newCost = readLine()!!.toString();
        print("Please enter new QTY In Stock: ");
        var newQuantityInStock = readLine()!!.toString();
        print("Please enter new QTY Sold: ");
        var newQuantitySold = readLine()!!.toString();
        print("Please enter new Detailed Description: ");
        var newDescription = readLine()!!.toString();

        val part = when (category.uppercase()) {
            "COMPUTER" -> {
                print("Please enter new Ram Size: ");
                val ramSize = readLine()!!.toInt();
                print("Please enter new Hard Drive Capacity: ");
                val hardDriveCapacity = readLine()!!.toInt();
                print("Please enter new Processor Speed: ");
                val processorSpeed = readLine()!!;
                Computer(
                    newPartName,
                    newRetailPrice.toDouble(),
                    newCost.toDouble(),
                    newQuantityInStock.toInt(),
                    newQuantitySold.toInt(),
                    newDescription,
                    ramSize,
                    hardDriveCapacity,
                    processorSpeed,
                    category
                );
            }
            "PRINTER" -> {
                print("Does it have color support? (true/false): ");
                val colorSupport = readLine()!!.toBoolean();
                print("Please enter new Pages Per Minute: ");
                val pagesPerMinute = readLine()!!.toInt();
                print("Does it have a scanner? (true/false): ");
                val scanner = readLine()!!.toBoolean();
                Printer(
                    newPartName,
                    newRetailPrice.toDouble(),
                    newCost.toDouble(),
                    newQuantityInStock.toInt(),
                    newQuantitySold.toInt(),
                    newDescription,
                    colorSupport,
                    pagesPerMinute,
                    scanner,
                    category
                );
            }
            "TABLET" -> {
                print("Please enter new Screen Size: ");
                val screenSize = readLine()!!;
                print("Please enter new Ram Size: ");
                val ramSize = readLine()!!.toInt();
                print("Does it have an SD Slot? (true/false): ");
                val sdSlots = readLine()!!.toBoolean();
                Tablet(
                    newPartName,
                    newRetailPrice.toDouble(),
                    newCost.toDouble(),
                    newQuantityInStock.toInt(),
                    newQuantitySold.toInt(),
                    newDescription,
                    screenSize,
                    ramSize,
                    sdSlots,
                    category
                );
            }
            else -> Part(
                newPartName,
                newRetailPrice.toDouble(),
                newCost.toDouble(),
                newQuantityInStock.toInt(),
                newQuantitySold.toInt(),
                newDescription,
                category
            )
        }
        parts.add(part);
    }

    fun updateItem(item: Int) {
        val part = parts[item]
        part.updatePart();
    }

    fun updateQuantity(partID: Int, quantity: Int) {
        var rowCount = 1;
        for (part in parts) {
            if (rowCount === partID) {
                part.quantityInStock += quantity;
            }
            rowCount++;
        }
        listAllParts(false);
    }

    fun sellPart(partID: Int) {

        var rowCount = 1;
        for (part in parts) {

            if (rowCount === partID) {
                if (part.quantityInStock > 0) {
                    part.quantitySold++;
                    part.quantityInStock--;

                } else {

                }
            }
            rowCount++;
        }
        listAllParts(false);
    }

    private fun checkPrint(part: Part, size: MutableList<String>): String {

        val partCategory = checkLength(part.partCategory, size[0].toInt() - 1).padEnd(size[0].toInt() - 1, ' ');
        val name = checkLength(part.name, size[1].toInt() - 1).padEnd(size[1].toInt() - 1, ' ');
        val retailPrice = part.retailPrice.toString().padEnd(size[2].toInt() - 1, ' ');
        val cost = part.cost.toString().padEnd(size[3].toInt() - 1, ' ');
        val quantityInStock = part.quantityInStock.toString().padEnd(size[4].toInt() - 1, ' ');
        val quantitySold = part.quantitySold.toString().padEnd(size[5].toInt() - 1, ' ');
        val detailDescription =
            checkLength(part.detailDescription, size[6].toInt() - 1).padEnd(size[6].toInt() - 1, ' ');

        when (part.partCategory) {
            "COMPUTER" -> {
                val computer = part as Computer
                val ramSize = computer.ramSize.toString().padEnd(size[7].toInt(), ' ')
                val hardDriveCapacity = computer.hardDriveCapacity.toString().padEnd(size[8].toInt(), ' ')
                val processorSpeed = computer.processorSpeed.padEnd(size[9].toInt(), ' ')
                return """$partCategory $name $retailPrice $cost $quantityInStock $quantitySold $detailDescription $ramSize $hardDriveCapacity $processorSpeed"""
            }
            "PRINTER" -> {
                val printer = part as Printer
                val colorSupport = printer.colorSupport.toString().padEnd(size[7].toInt(), ' ')
                val pagesPerMinute = printer.pagesPerMinute.toString().padEnd(size[8].toInt(), ' ')
                val scanner = printer.scanner.toString().padEnd(size[9].toInt(), ' ')

                return """$partCategory $name $retailPrice $cost $quantityInStock $quantitySold $detailDescription $colorSupport $pagesPerMinute $scanner"""
            }
            "TABLET" -> {
                val tablet = part as Tablet
                val screenSize = tablet.screenSize.padEnd(size[7].toInt(), ' ')
                val ramSize = tablet.ramSize.toString().padEnd(size[8].toInt(), ' ')
                val sdSlot = tablet.sdSlots.toString().padEnd(size[9].toInt(), ' ')
                return """$partCategory $name $retailPrice $cost $quantityInStock $quantitySold $detailDescription $screenSize $ramSize $sdSlot"""
            }
        }
        return "Invalid Selection";
    }

    private fun header(size: MutableList<String>, name: MutableList<String>) { //if category then ?? or do switch
        if (!size.isNullOrEmpty() && !name.isNullOrEmpty()) {
            println("""Parts Inventory""".padStart(((size[0].toInt() + size[1].toInt() + size[2].toInt() + size[3].toInt() + size[4].toInt() + size[5].toInt() + size[6].toInt() + size[7].toInt() + size[8].toInt() + size[9].toInt()) / 2) + 10));

            //---------------------------------------------**********check all the lengths to make sure they dont go past limit.

            println(
                """${
                    checkLength(name[0], size[0].toInt()).padEnd(
                        size[0].toInt(),
                        ' '
                    )
                }${name[1].padEnd(size[1].toInt(), ' ')}${
                    name[2].padEnd(
                        size[2].toInt(),
                        ' '
                    )
                }${name[3].padEnd(size[3].toInt(), ' ')}${
                    name[4].padEnd(
                        size[4].toInt(),
                        ' '
                    )
                }${name[5].padEnd(size[5].toInt(), ' ')}${
                    name[6].padEnd(
                        size[6].toInt(),
                        ' '
                    )
                }${name[7].padEnd(size[7].toInt(), ' ')}${
                    name[8].padEnd(
                        size[8].toInt(),
                        ' '
                    )
                }${name[9].padEnd(size[9].toInt(), ' ')}""".trimMargin()
            );

            size.forEachIndexed { index, size ->
                repeat(size.toInt() - 1) {
                    print("-")
                }
                print(" ");

            }
            println();

        }

    }

    private fun checkLength(title: String, size: Int): String {
        if (title.length >= size) {
            return title.take(size)
        } else {
            return title.padEnd(size, ' ')
        }
    }

    fun loadData(partsFile: String) {

        val readPartsFile = File(partsFile).readLines();

        var lineCount = 0;
        for (line in readPartsFile) {
            lineCount++;
            val fields = line.split("\t")
            if (lineCount > 2) { //Skip first two lines because I dont need to add that to the list of parts

                val partCategory = fields[0];
                val name = fields[1];
                val retailPrice = fields[2].toDouble();
                val cost = fields[3].toDouble();
                val quantityInStock = fields[4].toInt();
                val quantitySold = fields[5].toInt();
                val detailDescription = fields[6];

                val part = when (partCategory) {
                    "COMPUTER" -> {
                        val ramSize = fields[7].toInt();
                        val hardDriveCapacity = fields[8].toInt();
                        val processorSpeed = fields[9];
                        Computer(
                            name,
                            retailPrice,
                            cost,
                            quantityInStock,
                            quantitySold,
                            detailDescription,
                            ramSize,
                            hardDriveCapacity,
                            processorSpeed,
                            partCategory
                        );
                    }
                    "PRINTER" -> {
                        val colorSupport = fields[7].toBoolean()
                        val pagesPerMinute = fields[8].toInt()
                        val scanner = fields[9].toBoolean();
                        Printer(
                            name, retailPrice, cost, quantityInStock, quantitySold, detailDescription,
                            colorSupport, pagesPerMinute, scanner, partCategory
                        );
                    }
                    "TABLET" -> {
                        val screenSize = fields[7]
                        val ramSize = fields[8].toInt()
                        val hasSdSlot = fields[9].toBoolean()
                        Tablet(
                            name, retailPrice, cost, quantityInStock, quantitySold, detailDescription,
                            screenSize, ramSize, hasSdSlot, partCategory
                        )
                    }
                    else -> Part(
                        name,
                        retailPrice,
                        cost,
                        quantityInStock,
                        quantitySold,
                        detailDescription,
                        partCategory
                    )
                }
                parts.add(part);
            } else {

                if (lineCount === 1) {
                    sizeList.addAll(
                        listOf(
                            fields[0],
                            fields[1],
                            fields[2],
                            fields[3],
                            fields[4],
                            fields[5],
                            fields[6],
                            fields[7],
                            fields[8],
                            fields[9]
                        )
                    );
                } else if (lineCount === 2) {
                    nameList.addAll(
                        listOf(
                            fields[0],
                            fields[1],
                            fields[2],
                            fields[3],
                            fields[4],
                            fields[5],
                            fields[6],
                            fields[7],
                            fields[8],
                            fields[9]
                        )
                    );
                }
            }
        }
    }

    fun readParts() {
        listAllParts(false);
    }
}