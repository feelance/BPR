using System.ComponentModel.DataAnnotations;

namespace ShoeInc.Data
{
    // Author: Florin Bordei 
    
    public class Orderline
    {
        [Key] public int OrderlineId { get; set; }
        public int  ProductId { get; set; }
        public string OrderNumber { get; set; }
        public int Quantity { get; set; }
        public decimal Price { get; set; }
        
    }
}