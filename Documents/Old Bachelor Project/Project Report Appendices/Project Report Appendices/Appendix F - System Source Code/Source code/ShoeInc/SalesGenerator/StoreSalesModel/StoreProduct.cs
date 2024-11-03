using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace ShoeInc.SalesGenerator.StoreSalesModel
{
    // Author: Florin Bordei 
    public class StoreProduct
    {
        // StoreProduct represents the product sold physically from the store 
        public string Brand { get; set; }
        public string Model { get; set; }
        
        public string ModelCode { get; set;  }
        [BsonRepresentation(BsonType.Decimal128)]

        public decimal Price { get; set; }
        
        public int Quantity { get; set; }

     
  
    }
}