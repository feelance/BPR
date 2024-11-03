using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json.Serialization;

namespace ShoeInc.Data
{
    public class Product
    {
        // Author: Florin Bordei 
        [Key] public int Id { get; set; }
        public string Brand { get; set; }
        public string Model { get; set; }
        
        public string ModelCode { get; set;  }
        public decimal Price { get; set; }
        
        public decimal Discount { get; set; }
        public string Description { get; set; }
        
        public byte[] Image { get; set;  }
        
        public string ImageUrl { get; set; }
        public int StockLevel { get; set; }
        [NotMapped]
        public int Quantity { get; set; }

    }

    
}