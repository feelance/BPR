namespace ShoeInc.SalesGenerator.StoreSalesModel
{
    // Author: Florin Bordei 
    public class SalesStore
    {
        // SalesStore class contains Date, StoreProduct and the Store 
        // SalesStore object is stored in the MongoDb database 
        public string Date { get; set; }
        
        public StoreProduct Product  { get; set; }
        
        public Store Store { get; set; }
    }
}