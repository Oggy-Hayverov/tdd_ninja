package videoRental.printer

interface IReceiptPrinter {
    fun printReceipt(movie: String, price: String)
}
